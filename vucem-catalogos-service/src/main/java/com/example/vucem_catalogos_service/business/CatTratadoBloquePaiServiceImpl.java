package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatTratadoBloquePaiService;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.dto.*;
import com.example.vucem_catalogos_service.model.entity.CatTratadoBloquePai;
import com.example.vucem_catalogos_service.model.entity.CatTratadoBloquePaiId;
import com.example.vucem_catalogos_service.persistence.repo.ICatPaisRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatPaisTratadoAcuerdoIdRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTratadoAcuerdoRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTratadoBloquePaiRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class CatTratadoBloquePaiServiceImpl implements ICatTratadoBloquePaiService {

    @Autowired
    private ICatTratadoBloquePaiRepository repository;

    @Autowired
    private ICatPaisRepository catPaisRepository;

    @Autowired
    private ICatTratadoAcuerdoRepository catTratadoAcuerdoRepository;

    @Autowired
    private ICatPaisTratadoAcuerdoIdRepository catPaisTratadoAcuerdoIdRepository;

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cvePais", "tbp.id.cvePais",
            "nombrePais", "p.nombre",
            "cveTratadoAcuerdo", "t.cveTratadoAcuerdo"
    );

    @Override
    public PageResponseDTO<CatTratadoBloquePaiResponseDTO> list(
            String cvePais, Short idTratadoAcuerdo, Boolean blnActivo, String search, String sortBy, String sortDir, Pageable pageable) {

        Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        Pageable pageableWithSort = sort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort)
                : pageable;

        Page<CatTratadoBloquePaiResponseDTO> page =
                repository.search(cvePais, idTratadoAcuerdo, blnActivo, search, pageableWithSort);

        return PageResponseDTO.<CatTratadoBloquePaiResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatTratadoBloquePaiResponseDTO findById(String cvePais, Short idTratadoAcuerdo) {
        return repository.findByPaisTratado(cvePais, idTratadoAcuerdo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Registro no encontrado: cvePais=" + cvePais + " idTratadoAcuerdo=" + idTratadoAcuerdo));
    }

    @Override
    public List<CatTratadoBloquePaiResponseDTO> createMasivo(CatTratadoBloquePaiMasivoRequestDTO dto) {
        if (dto.getPaises() == null || dto.getPaises().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Se requiere al menos un país");
        }
        if (dto.getTratados() == null || dto.getTratados().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Se requiere al menos un tratado");
        }

        // Validar existencia de todos los paises y tratados antes de modificar nada
        for (String cvePais : dto.getPaises()) {
            if (!catPaisRepository.existsById(cvePais)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "País no encontrado: " + cvePais);
            }
        }
        for (Short idTratadoAcuerdo : dto.getTratados()) {
            if (!catTratadoAcuerdoRepository.existsById(idTratadoAcuerdo)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tratado no encontrado: " + idTratadoAcuerdo);
            }
        }

        Set<String> nuevoPaises = new HashSet<>(dto.getPaises());
        Set<Short> nuevoTratados = new HashSet<>(dto.getTratados());

        // Desactivar combinaciones que ya no están en la nueva selección
        List<CatTratadoBloquePai> activosAfectados =
                repository.findActivosByPaisesOrTratados(dto.getPaises(), dto.getTratados());
        for (CatTratadoBloquePai activo : activosAfectados) {
            String pais = activo.getId().getCvePais();
            Short tratado = activo.getId().getIdTratadoAcuerdo();
            if (!nuevoPaises.contains(pais) || !nuevoTratados.contains(tratado)) {
                activo.setBlnActivo(false);
                repository.save(activo);
            }
        }

        // Crear o actualizar las combinaciones de la nueva selección
        List<CatTratadoBloquePaiResponseDTO> resultado = new ArrayList<>();

        for (String cvePais : dto.getPaises()) {
            for (Short idTratadoAcuerdo : dto.getTratados()) {
                CatTratadoBloquePaiId pk = new CatTratadoBloquePaiId();
                pk.setCvePais(cvePais);
                pk.setIdTratadoAcuerdo(idTratadoAcuerdo);

                LocalDate fecIniVigencia = dto.getFecIniVigencia();

                // Si ya existe un registro activo, se reutiliza su fecIniVigencia
                List<CatTratadoBloquePai> activos = repository.findActivosByPaisAndTratado(cvePais, idTratadoAcuerdo);
                if (!activos.isEmpty()) {
                    CatTratadoBloquePai existente = activos.get(0);
                    if (fecIniVigencia == null) {
                        fecIniVigencia = existente.getFecIniVigencia();
                    }
                    existente.setBlnActivo(dto.getBlnActivo());
                    repository.save(existente);
                }

                CatTratadoBloquePai entity = new CatTratadoBloquePai();
                entity.setId(pk);
                entity.setFecCaptura(LocalDate.now());
                entity.setFecIniVigencia(fecIniVigencia != null ? fecIniVigencia : LocalDate.now());
                entity.setFecFinVigencia(dto.getFecFinVigencia());
                entity.setBlnActivo(true);
                entity.setBlnEnvioElectronico(dto.getBlnEnvioElectronico());
                entity.setBlnMuestraCertificado(dto.getBlnMuestraCertificado());

                repository.save(entity);
                repository.findByPaisTratado(cvePais, idTratadoAcuerdo).ifPresent(resultado::add);
            }
        }

        return resultado;
    }

    @Override
    public CatTratadoBloquePaiResponseDTO update(
            String cvePais, Short idTratadoAcuerdo, CatTratadoBloquePaiRequestDTO dto) {

        CatTratadoBloquePaiId pk = new CatTratadoBloquePaiId();
        pk.setCvePais(cvePais);
        pk.setIdTratadoAcuerdo(idTratadoAcuerdo);

        CatTratadoBloquePai entity = repository.findById(pk)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Registro no encontrado: cvePais=" + cvePais + " idTratadoAcuerdo=" + idTratadoAcuerdo));

        if (dto.getFecCaptura()           != null) entity.setFecCaptura(dto.getFecCaptura());
        if (dto.getFecIniVigencia()        != null) entity.setFecIniVigencia(dto.getFecIniVigencia());
        if (dto.getFecFinVigencia()        != null) entity.setFecFinVigencia(dto.getFecFinVigencia());
        if (dto.getBlnActivo()             != null) entity.setBlnActivo(dto.getBlnActivo());
        if (dto.getBlnEnvioElectronico()   != null) entity.setBlnEnvioElectronico(dto.getBlnEnvioElectronico());
        if (dto.getBlnMuestraCertificado() != null) entity.setBlnMuestraCertificado(dto.getBlnMuestraCertificado());

        repository.save(entity);
        return findById(cvePais, idTratadoAcuerdo);
    }

    @Override
    public void delete(String cvePais, Short idTratadoAcuerdo) {
        CatTratadoBloquePaiId pk = new CatTratadoBloquePaiId();
        pk.setCvePais(cvePais);
        pk.setIdTratadoAcuerdo(idTratadoAcuerdo);

        if (!repository.existsById(pk)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Registro no encontrado: cvePais=" + cvePais + " idTratadoAcuerdo=" + idTratadoAcuerdo);
        }
        repository.deleteById(pk);
    }

    @Override
    public List<ICatPaisRepository.ComboProyeccion> listadoPaises() {
        return catPaisRepository.listadoPaisesActivos();
    }

    @Override
    public List<ICatTratadoAcuerdoRepository.ComboProyeccion> listadoTratados() {
        return catPaisTratadoAcuerdoIdRepository.listadoTratadosActivos();
    }

    @Override
    public CatPaisesComboResponseDTO paisesComboByTratados(List<Short> idsTratado) {
        return CatPaisesComboResponseDTO.builder()
                .paisesGuardados(repository.findPaisesGuardadosByTratados(idsTratado))
                .paisesNoGuardados(repository.findPaisesNoGuardadosByTratados(idsTratado))
                .build();
    }

    @Override
    public CatTratadoAcuerdoComboResponseDTO tratadosGuardadosByPaises(List<String> clavePaises) {
        return CatTratadoAcuerdoComboResponseDTO.builder()
                .tratadosGuardados(repository.findTratadosGuardadosByPaises(clavePaises))
                .tratadosNoGuardados(repository.findTratadosNoGuardadosByPaises(clavePaises))
                .build();
    }
}
