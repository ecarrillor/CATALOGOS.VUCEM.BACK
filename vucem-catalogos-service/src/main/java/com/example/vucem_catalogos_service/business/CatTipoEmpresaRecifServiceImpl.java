package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatTipoEmpresaRecifService;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.dto.CatTipoEmpresaRecifDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatTipoEmpresaRecif;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoEmpresaRecifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CatTipoEmpresaRecifServiceImpl implements ICatTipoEmpresaRecifService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveTipoEmpresaRecif", "cveTipoEmpresaRecif",
            "cveTipoEmpresaRecifR.descripcion", "cveTipoEmpresaRecifR.descripcion",
            "descripcion", "descripcion"
    );

    @Autowired
    private ICatTipoEmpresaRecifRepository catTipoEmpresaRecifRepository;

    @Override
    public PageResponseDTO<CatTipoEmpresaRecifDTO> list(String search, String sortBy, String sortDir, Pageable pageable) {
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
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "cveTipoEmpresaRecif"));

        Page<CatTipoEmpresaRecifDTO> page = catTipoEmpresaRecifRepository.search(texto, activo, sortedPageable);

        return PageResponseDTO.<CatTipoEmpresaRecifDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatTipoEmpresaRecifDTO findById(String cveTipoEmpresaRecif) {
        return catTipoEmpresaRecifRepository.findByTipoEmpresaRecifDTO(cveTipoEmpresaRecif)
                .orElseThrow(() -> new RuntimeException("CatTipoEmpresaRecif no encontrado con cve: " + cveTipoEmpresaRecif));
    }

    @Override
    public CatTipoEmpresaRecifDTO create(CatTipoEmpresaRecifDTO dto) {
        CatTipoEmpresaRecif entity = new CatTipoEmpresaRecif();
        entity.setCveTipoEmpresaRecif(dto.getCveTipoEmpresaRecif());
        entity.setDescripcion(dto.getDescripcion());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        if (dto.getCveTipoEmpresaRecifR() != null) {
            entity.setCveTipoEmpresaRecifR(
                    catTipoEmpresaRecifRepository.findById(dto.getCveTipoEmpresaRecifR())
                            .orElseThrow(() -> new RuntimeException("CatTipoEmpresaRecif padre no encontrado: " + dto.getCveTipoEmpresaRecifR()))
            );
        }

        CatTipoEmpresaRecif saved = catTipoEmpresaRecifRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatTipoEmpresaRecifDTO update(String cveTipoEmpresaRecif, CatTipoEmpresaRecifDTO dto) {
        CatTipoEmpresaRecif entity = catTipoEmpresaRecifRepository.findById(cveTipoEmpresaRecif)
                .orElseThrow(() -> new RuntimeException("CatTipoEmpresaRecif no encontrado con cve: " + cveTipoEmpresaRecif));

        if (dto.getDescripcion() != null) {
            entity.setDescripcion(dto.getDescripcion());
        }
        if (dto.getFecIniVigencia() != null) {
            entity.setFecIniVigencia(dto.getFecIniVigencia());
        }
        if (dto.getFecFinVigencia() != null) {
            entity.setFecFinVigencia(dto.getFecFinVigencia());
        }
        if (dto.getBlnActivo() != null) {
            entity.setBlnActivo(dto.getBlnActivo());
        }
        if (dto.getCveTipoEmpresaRecifR() != null) {
            entity.setCveTipoEmpresaRecifR(
                    catTipoEmpresaRecifRepository.findById(dto.getCveTipoEmpresaRecifR())
                            .orElseThrow(() -> new RuntimeException("CatTipoEmpresaRecif padre no encontrado: " + dto.getCveTipoEmpresaRecifR()))
            );
        }

        CatTipoEmpresaRecif saved = catTipoEmpresaRecifRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public List<CatTipoEmpresaRecif> listTipoEmpresaRecif() {
        return catTipoEmpresaRecifRepository.findByBlnActivoTrue();
    }

    private CatTipoEmpresaRecifDTO mapToDTO(CatTipoEmpresaRecif entity) {
        return CatTipoEmpresaRecifDTO.builder()
                .cveTipoEmpresaRecif(entity.getCveTipoEmpresaRecif())
                .cveTipoEmpresaRecifR(entity.getCveTipoEmpresaRecifR() != null
                        ? entity.getCveTipoEmpresaRecifR().getCveTipoEmpresaRecif() : null)
                .descripcionPadre(entity.getCveTipoEmpresaRecifR() != null
                        ? entity.getCveTipoEmpresaRecifR().getDescripcion() : null)
                .descripcion(entity.getDescripcion())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
