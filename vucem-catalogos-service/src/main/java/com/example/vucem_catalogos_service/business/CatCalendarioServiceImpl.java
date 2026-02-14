package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatCalendario;
import com.example.vucem_catalogos_service.persistence.repo.ICatCalendarioRepository;
import com.example.vucem_catalogos_service.persistence.specification.GenericFilterSpecification;
import com.example.vucem_catalogos_service.persistence.specification.GenericSearchSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CatCalendarioServiceImpl extends AbstractCatalogService<CatCalendario, String> {
    @Autowired
    private ICatCalendarioRepository catCalendarioRepository;

    @Override
    public String getCatalogKey() {
        return "cat-calendario";
    }

    @Override
    public Class<CatCalendario> getEntityClass() {
        return CatCalendario.class;
    }

    @Override
    protected JpaRepository<CatCalendario, String> getRepository() {
        return catCalendarioRepository;
    }

    @Override
    protected Class<String> getIdClass() {
        return String.class;
    }


    @Override
    public Page<CatCalendario> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatCalendario> spec =
                GenericSearchSpecification.<CatCalendario>searchInFields(
                        search,
                        List.of("nombre", "fecIniVigencia")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );


        return catCalendarioRepository.findAll(spec, pageable);
    }
}

