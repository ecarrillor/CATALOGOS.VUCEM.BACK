package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatTipoDictamen;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoDictamanRepository;
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
public class CatTipoDictamanServiceImpl extends AbstractCatalogService<CatTipoDictamen,Short>{
    @Autowired
    private ICatTipoDictamanRepository catTipoDictamanRepository;

    @Override
    public String getCatalogKey() {
        return "cat_tipo_dictamen";
    }

    @Override
    public Class<CatTipoDictamen> getEntityClass() {
        return CatTipoDictamen.class;
    }

    @Override
    protected JpaRepository<CatTipoDictamen, Short> getRepository() {
        return catTipoDictamanRepository;
    }

    @Override
    protected Class<Short> getIdClass() {
        return Short.class;
    }


    @Override
    public Page<CatTipoDictamen> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatTipoDictamen> spec =
                GenericSearchSpecification.<CatTipoDictamen>searchInFields(
                        search,
                        List.of("nombre")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );


        return catTipoDictamanRepository.findAll(spec, pageable);
    }
}
