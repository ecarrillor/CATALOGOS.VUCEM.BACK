package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatPaisTratadoAcuerdoService;
import com.example.vucem_catalogos_service.model.dto.CatPaisTratadoAcuerdoRequestDTO;
import com.example.vucem_catalogos_service.model.dto.CatPaisTratadoAcuerdoResponseDTO;
import com.example.vucem_catalogos_service.model.dto.CatPlazoTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.*;
import com.example.vucem_catalogos_service.persistence.repo.ICatPaisRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatPaisTratadoAcuerdoIdRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTratadoAcuerdoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
@Transactional
public class CatPaisTratadoAcuerdoServiceImpl implements ICatPaisTratadoAcuerdoService {

    @Autowired
    private ICatPaisTratadoAcuerdoIdRepository iCatPaisTratadoAcuerdoIdRepository;

    @Autowired
    private ICatTratadoAcuerdoRepository iCatTratadoAcuerdoRepository;

    @Autowired
    private ICatPaisRepository iCatPaisRepository;

    @Override
    public PageResponseDTO<CatPaisTratadoAcuerdoResponseDTO> list(String search, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if ("activo".equalsIgnoreCase(search)) {
            activo = true;
        } else if ("inactivo".equalsIgnoreCase(search)) {
            activo = false;
        } else {
            texto = search;
        }

        Page<CatPaisTratadoAcuerdoResponseDTO> page = iCatPaisTratadoAcuerdoIdRepository.search(texto, activo, pageable);

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
    public CatPaisTratadoAcuerdoResponseDTO findById(String idPais, Short idTratado) {
        return iCatPaisTratadoAcuerdoIdRepository.findByPaisTratado(idPais, idTratado)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,"Pais no encontrado para idPais=" + idPais + ", con Tratatdo acuerdo=" + idTratado));
    }

    @Override
    public CatPaisTratadoAcuerdoResponseDTO create(CatPaisTratadoAcuerdoRequestDTO dto) {
        CatPaisTratadoAcuerdoId id = new CatPaisTratadoAcuerdoId();
        id.setIdTratadoAcuerdo(dto.getIdTratadoAcuerdo());
        id.setCvePais(dto.getCvePais());

        if (iCatPaisTratadoAcuerdoIdRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ya existe un registro con ese Pais y tipo de tratado"
            );
        }


        CatPaisTratadoAcuerdo entity = new CatPaisTratadoAcuerdo();
        entity.setId(id);

        CatTratadoAcuerdo tratadoAcuerdo =
                iCatTratadoAcuerdoRepository.findById(dto.getIdTratadoAcuerdo())
                        .orElseThrow(() -> new ResponseStatusException( HttpStatus.NOT_FOUND ,"Tratado Acuerdo no existe"));

        CatPais pais =
                iCatPaisRepository.findById(dto.getCvePais())
                        .orElseThrow(() -> new ResponseStatusException( HttpStatus.NOT_FOUND ,"Pais no existe"));

        entity.setCvePais(pais);
        entity.setIdTratadoAcuerdo(tratadoAcuerdo);
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setFecCaptura(LocalDate.now());
        entity.setBlnActivo(dto.getBlnActivo());

        CatPaisTratadoAcuerdo saved = iCatPaisTratadoAcuerdoIdRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatPaisTratadoAcuerdoResponseDTO update(String idPais, Short idTratado, CatPaisTratadoAcuerdoRequestDTO dto) {
        CatPaisTratadoAcuerdoId pk = new CatPaisTratadoAcuerdoId();
        pk.setCvePais(idPais);
        pk.setIdTratadoAcuerdo(idTratado);

        CatPaisTratadoAcuerdo entity = iCatPaisTratadoAcuerdoIdRepository.findById(pk)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Ya existe un registro con ese Pais y tipo de tratado"));


        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatPaisTratadoAcuerdo saved = iCatPaisTratadoAcuerdoIdRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatPaisTratadoAcuerdoResponseDTO mapToDTO(CatPaisTratadoAcuerdo entity) {
        return CatPaisTratadoAcuerdoResponseDTO.builder()
                .cvePais(entity.getCvePais().getCvePais())
                .NombrePais(entity.getCvePais().getNombre())
                .idTratado(entity.getIdTratadoAcuerdo().getId())
                .tratadoAcuerdo(entity.getIdTratadoAcuerdo().getCveTratadoAcuerdo())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
