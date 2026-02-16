package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatSectorProsec;
import com.example.vucem_catalogos_service.persistence.repo.ICatSectorProsecRepository;
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
public class ICatSectorProsecServiceImpl extends AbstractCatalogService<CatSectorProsec, String> {
    @Autowired
    private ICatSectorProsecRepository catSectorProsecRepository;

    @Override
    public String getCatalogKey() {
        return "cat_sector_prosec";
    }

    @Override
    public Class<CatSectorProsec> getEntityClass() {
        return CatSectorProsec.class;
    }

    @Override
    protected JpaRepository<CatSectorProsec, String> getRepository() {
        return catSectorProsecRepository;
    }

    @Override
    protected Class<String> getIdClass() {
        return String.class;
    }


    @Override
    public Page<CatSectorProsec> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatSectorProsec> spec =
                GenericSearchSpecification.<CatSectorProsec>searchInFields(
                        search,
                        List.of("cveSectorProsec", "nombre")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );


        return catSectorProsecRepository.findAll(spec, pageable);
    }
}