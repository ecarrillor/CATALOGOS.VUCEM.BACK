package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatPaisTratadoAcuerdoService;
import com.example.vucem_catalogos_service.model.dto.CatPaisTratadoAcuerdoRequestDTO;
import com.example.vucem_catalogos_service.model.dto.CatPaisTratadoAcuerdoResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatPais;
import com.example.vucem_catalogos_service.model.entity.CatPaisTratadoAcuerdo;
import com.example.vucem_catalogos_service.model.entity.CatPaisTratadoAcuerdoId;
import com.example.vucem_catalogos_service.model.entity.CatTratadoAcuerdo;
import com.example.vucem_catalogos_service.persistence.repo.ICatPaisRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatPaisTratadoAcuerdoIdRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTratadoAcuerdoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CatPaisTratadoAcuerdoServiceImpl implements ICatPaisTratadoAcuerdoService {

    @Autowired
    private ICatPaisTratadoAcuerdoIdRepository repository;

    @Autowired
    private ICatTratadoAcuerdoRepository catTratadoAcuerdoRepository;

    @Autowired
    private ICatPaisRepository catPaisRepository;

    @Override
    public PageResponseDTO<CatPaisTratadoAcuerdoResponseDTO> list(
            String cvePais, Short idTratadoAcuerdo, Boolean blnActivo, String search, Pageable pageable) {

        Page<CatPaisTratadoAcuerdoResponseDTO> page =
                repository.search(cvePais, idTratadoAcuerdo, blnActivo, search, pageable);

        return PageResponseDTO.<CatPaisTratadoAcuerdoResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatPaisTratadoAcuerdoResponseDTO findById(String cvePais, Short idTratadoAcuerdo) {
        return repository.findByPaisTratado(cvePais, idTratadoAcuerdo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Registro no encontrado: cvePais=" + cvePais + " idTratadoAcuerdo=" + idTratadoAcuerdo));
    }

    @Override
    public CatPaisTratadoAcuerdoResponseDTO create(CatPaisTratadoAcuerdoRequestDTO dto) {
        CatPaisTratadoAcuerdoId pk = new CatPaisTratadoAcuerdoId();
        pk.setCvePais(dto.getCvePais());
        pk.setIdTratadoAcuerdo(dto.getIdTratadoAcuerdo());

        if (repository.existsById(pk)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe un registro con cvePais=" + dto.getCvePais()
                    + " e idTratadoAcuerdo=" + dto.getIdTratadoAcuerdo());
        }

        CatPais pais = catPaisRepository.findById(dto.getCvePais())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "País no encontrado: " + dto.getCvePais()));

        CatTratadoAcuerdo tratado = catTratadoAcuerdoRepository.findById(dto.getIdTratadoAcuerdo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Tratado de acuerdo no encontrado: " + dto.getIdTratadoAcuerdo()));

        CatPaisTratadoAcuerdo entity = new CatPaisTratadoAcuerdo();
        entity.setId(pk);
        entity.setCvePais(pais);
        entity.setIdTratadoAcuerdo(tratado);
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setFecCaptura(dto.getFecCaptura() != null ? dto.getFecCaptura() : LocalDate.now());
        entity.setBlnActivo(dto.getBlnActivo() != null ? dto.getBlnActivo() : true);

        repository.save(entity);
        return findById(dto.getCvePais(), dto.getIdTratadoAcuerdo());
    }

    @Override
    public CatPaisTratadoAcuerdoResponseDTO update(
            String cvePais, Short idTratadoAcuerdo, CatPaisTratadoAcuerdoRequestDTO dto) {

        CatPaisTratadoAcuerdoId pk = new CatPaisTratadoAcuerdoId();
        pk.setCvePais(cvePais);
        pk.setIdTratadoAcuerdo(idTratadoAcuerdo);

        CatPaisTratadoAcuerdo entity = repository.findById(pk)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Registro no encontrado: cvePais=" + cvePais + " idTratadoAcuerdo=" + idTratadoAcuerdo));

        if (dto.getFecIniVigencia() != null) entity.setFecIniVigencia(dto.getFecIniVigencia());
        if (dto.getFecFinVigencia() != null) entity.setFecFinVigencia(dto.getFecFinVigencia());
        if (dto.getFecCaptura()     != null) entity.setFecCaptura(dto.getFecCaptura());
        if (dto.getBlnActivo()      != null) entity.setBlnActivo(dto.getBlnActivo());

        repository.save(entity);
        return findById(cvePais, idTratadoAcuerdo);
    }



    @Override
    public List<ICatPaisRepository.ComboProyeccion> listadoPaises() {
        return catPaisRepository.listadoPaisesActivos();
    }

    @Override
    public List<ICatTratadoAcuerdoRepository.ComboProyeccion> listadoTratados() {
        return catTratadoAcuerdoRepository.listadoTratadosActivos();
    }
}
