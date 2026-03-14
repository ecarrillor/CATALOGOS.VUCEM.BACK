package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatRegimen;
import com.example.vucem_catalogos_service.persistence.repo.ICatRegimenRepository;
import com.example.vucem_catalogos_service.persistence.specification.GenericFilterSpecification;
import com.example.vucem_catalogos_service.persistence.specification.GenericSearchSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CatRegimenServiceImpl extends AbstractCatalogService<CatRegimen, String>{
    @Autowired
    private ICatRegimenRepository catRegimenRepository;

    @Override
    public String getCatalogKey() {
        return "cat-regimen";
    }

    @Override
    public Class<CatRegimen> getEntityClass() {
        return CatRegimen.class;
    }

    @Override
    protected JpaRepository<CatRegimen, String> getRepository() {
        return catRegimenRepository;
    }

    @Override
    protected Class<String> getIdClass() {
        return String.class;
    }


    @Override
    public Page<CatRegimen> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatRegimen> spec =
                GenericSearchSpecification.<CatRegimen>searchInFields(
                        search,
                        List.of("cveRegimen","nombre")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );



        return catRegimenRepository.findAll(spec, pageable);
    }
}
