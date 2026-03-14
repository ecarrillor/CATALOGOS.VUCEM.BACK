package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatFundamentoDictamenService;
import com.example.vucem_catalogos_service.model.dto.FundamentoDictamen.CatFundamentoDictamenRequestDTO;
import com.example.vucem_catalogos_service.model.dto.FundamentoDictamen.CatFundamentoDictamenResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatFundamentoDictamen;
import com.example.vucem_catalogos_service.persistence.repo.ICatFundamentoDictamanRepository;
import com.example.vucem_catalogos_service.persistence.specification.GenericFilterSpecification;
import com.example.vucem_catalogos_service.persistence.specification.GenericSearchSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CatFundamentoDictamanServiceImpl extends AbstractCatalogService<CatFundamentoDictamen,Long> implements ICatFundamentoDictamenService {
    @Autowired
    private ICatFundamentoDictamanRepository catFundamentoDictamanRepository;

    @Override
    public String getCatalogKey() {
        return "cat_fundamento_dictamen";
    }

    @Override
    public Class<CatFundamentoDictamen> getEntityClass() {
        return CatFundamentoDictamen.class;
    }

    @Override
    protected JpaRepository<CatFundamentoDictamen, Long> getRepository() {
        return catFundamentoDictamanRepository;
    }

    @Override
    protected Class<Long> getIdClass() {
        return Long.class;
    }


    @Override
    public Page<CatFundamentoDictamen> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatFundamentoDictamen> spec =
                GenericSearchSpecification.<CatFundamentoDictamen>searchInFields(
                        search,
                        List.of("descripcion")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );



        return catFundamentoDictamanRepository.findAll(spec, pageable);
    }



    @Override
    public PageResponseDTO<CatFundamentoDictamenResponseDTO> listarFundamentoDictamen(String search, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if (search != null && !search.isBlank()) {

            String s = search.trim().toLowerCase();

            if (s.equals("activo")) {
                activo = true;
            }
            else if (s.equals("inactivo")) {
                activo = false;
            }
            else {
                texto = "%" + search.trim().toLowerCase() + "%";
            }
        }

        Page<CatFundamentoDictamenResponseDTO> page =
                catFundamentoDictamanRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatFundamentoDictamenResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatFundamentoDictamenResponseDTO crearFundamentoDictamen(CatFundamentoDictamenRequestDTO dto) {
        if (catFundamentoDictamanRepository.existsById(dto.getCveFundamentoDictamen())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }

        CatFundamentoDictamen entity = new CatFundamentoDictamen();

        entity.setId(dto.getCveFundamentoDictamen());


        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setDescripcion(dto.getDescripcion());
        entity.setBlnSentidoFundamento(dto.getBlnSentidoFundamento());
        entity.setBlnActivo(dto.getBlnActivo());

        CatFundamentoDictamen saved = catFundamentoDictamanRepository.save(entity);

        return CatFundamentoDictamenResponseDTO.builder()
                .cveFundamentoDictamen(saved.getId())
                .descripcion(saved.getDescripcion())
                .blnSentidoFundamento(saved.getBlnSentidoFundamento())
                .fecIniVigencia(saved.getFecIniVigencia())
                .fecFinVigencia(saved.getFecFinVigencia())
                .blnActivo(saved.getBlnActivo())
                .build();
    }

    @Override
    public CatFundamentoDictamenResponseDTO findById(Long id) {
        CatFundamentoDictamen entity = catFundamentoDictamanRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Registro no encontrado con id: " + id
                        )
                );

        return CatFundamentoDictamenResponseDTO.builder()
                .cveFundamentoDictamen(entity.getId())
                .descripcion(entity.getDescripcion())
                .blnSentidoFundamento(entity.getBlnSentidoFundamento())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }

    @Override
    public CatFundamentoDictamenResponseDTO update(Long id, CatFundamentoDictamenRequestDTO dto) {
        CatFundamentoDictamen entity = catFundamentoDictamanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        entity.setId(dto.getCveFundamentoDictamen());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setDescripcion(dto.getDescripcion());
        entity.setBlnSentidoFundamento(dto.getBlnSentidoFundamento());
        entity.setBlnActivo(dto.getBlnActivo());

        CatFundamentoDictamen updated = catFundamentoDictamanRepository.save(entity);

        return CatFundamentoDictamenResponseDTO.builder()
                .cveFundamentoDictamen(updated.getId())
                .descripcion(updated.getDescripcion())
                .blnSentidoFundamento(updated.getBlnSentidoFundamento())
                .fecIniVigencia(updated.getFecIniVigencia())
                .fecFinVigencia(updated.getFecFinVigencia())
                .blnActivo(updated.getBlnActivo())
                .build();
    }
}
