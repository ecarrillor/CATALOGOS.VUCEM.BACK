package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatEquivalenciaAELCService;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.dto.CatEquivalenciaAelcDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatEquivalenciaAelc;
import com.example.vucem_catalogos_service.model.entity.CatEquivalenciaAelcId;
import com.example.vucem_catalogos_service.persistence.repo.ICatEquivalenciaAelcRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatMonedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Map;

@Service
@Transactional
public class CatEquivalenciaAELCServiceImpl implements ICatEquivalenciaAELCService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveMoneda", "e.id.cveMoneda",
            "nombreMoneda", "m.nombre",
            "valor", "e.valor"
    );

    @Autowired
    private ICatEquivalenciaAelcRepository catEquivalenciaAelcRepository;

    @Autowired
    private ICatMonedaRepository iCatMonedaRepository;

    @Override
    public PageResponseDTO<CatEquivalenciaAelcDTO> list(String search, String sortBy, String sortDir, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if (search != null && !search.isBlank()) {
            String s = search.trim().toLowerCase();
            if (s.equals("activo")) {
                activo = true;
            } else if (s.equals("inactivo")) {
                activo = false;
            } else {
                texto = "%" + s + "%";
            }
        }

        Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        Pageable sortedPageable = sort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort)
                : pageable;

        Page<CatEquivalenciaAelcDTO> page = catEquivalenciaAelcRepository.search(texto, activo, sortedPageable);

        return PageResponseDTO.<CatEquivalenciaAelcDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatEquivalenciaAelcDTO findById(LocalDate fecIniVigencia, String cveMoneda) {
        return catEquivalenciaAelcRepository.findByEquivalenciaAelcDTO(fecIniVigencia, cveMoneda)
                .orElseThrow(() -> new RuntimeException(
                        "CatEquivalenciaAelc no encontrada para fecIniVigencia=" + fecIniVigencia + ", cveMoneda=" + cveMoneda));
    }

    @Override
    public CatEquivalenciaAelcDTO create(CatEquivalenciaAelcDTO dto) {
        CatEquivalenciaAelcId id = new CatEquivalenciaAelcId();
        id.setFecIniVigencia(dto.getFecIniVigencia());
        id.setCveMoneda(dto.getCveMoneda());

        if (catEquivalenciaAelcRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "El id ya existe"
            );
        }

        CatEquivalenciaAelc entity = new CatEquivalenciaAelc();

        entity.setMoneda(
                iCatMonedaRepository
                        .findById(dto.getCveMoneda())
                        .orElseThrow(() -> new RuntimeException("Moneda no encontrada"))
        );
        entity.setId(id);
        entity.setValor(dto.getValor());
        entity.setFecCaptura(LocalDate.now());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatEquivalenciaAelc saved = catEquivalenciaAelcRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatEquivalenciaAelcDTO update(LocalDate fecIniVigencia, String cveMoneda, CatEquivalenciaAelcDTO dto) {
        CatEquivalenciaAelcId pk = new CatEquivalenciaAelcId();
        pk.setFecIniVigencia(fecIniVigencia);
        pk.setCveMoneda(cveMoneda);

        CatEquivalenciaAelc entity = catEquivalenciaAelcRepository.findById(pk)
                .orElseThrow(() -> new RuntimeException(
                        "CatEquivalenciaAelc no encontrada para fecIniVigencia=" + fecIniVigencia + ", cveMoneda=" + cveMoneda));

        if (dto.getValor() != null) {
            entity.setValor(dto.getValor());
        }
        if (dto.getFecCaptura() != null) {
            entity.setFecCaptura(dto.getFecCaptura());
        }
        if (dto.getFecFinVigencia() != null) {
            entity.setFecFinVigencia(dto.getFecFinVigencia());
        }
        if (dto.getBlnActivo() != null) {
            entity.setBlnActivo(dto.getBlnActivo());
        }

        CatEquivalenciaAelc saved = catEquivalenciaAelcRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatEquivalenciaAelcDTO mapToDTO(CatEquivalenciaAelc entity) {
        return CatEquivalenciaAelcDTO.builder()
                .fecIniVigencia(entity.getId().getFecIniVigencia())
                .cveMoneda(entity.getId().getCveMoneda())
                .valor(entity.getValor())
                .fecCaptura(entity.getFecCaptura())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
