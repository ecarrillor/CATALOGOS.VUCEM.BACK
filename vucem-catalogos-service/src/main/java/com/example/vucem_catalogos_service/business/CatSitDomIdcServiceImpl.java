package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatSitDomIdc;
import com.example.vucem_catalogos_service.persistence.repo.ICatSitDomIdcRepository;
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
public class CatSitDomIdcServiceImpl extends AbstractCatalogService<CatSitDomIdc,Long>{
    @Autowired
    private ICatSitDomIdcRepository catSitDomIdcRepository;

    @Override
    public String getCatalogKey() {
        return "cat_sit_dom_idc";
    }

    @Override
    public Class<CatSitDomIdc> getEntityClass() {
        return CatSitDomIdc.class;
    }

    @Override
    protected JpaRepository<CatSitDomIdc, Long> getRepository() {
        return catSitDomIdcRepository;
    }

    @Override
    protected Class<Long> getIdClass() {
        return Long.class;
    }


    @Override
    public Page<CatSitDomIdc> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatSitDomIdc> spec =
                GenericSearchSpecification.<CatSitDomIdc>searchInFields(
                        search,
                        List.of( "descripcion")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );


        return catSitDomIdcRepository.findAll(spec, pageable);
    }
}
