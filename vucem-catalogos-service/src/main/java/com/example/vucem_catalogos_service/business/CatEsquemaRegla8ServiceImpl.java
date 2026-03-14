package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatDeclaracion;
import com.example.vucem_catalogos_service.model.entity.CatEsquemaRegla8;
import com.example.vucem_catalogos_service.persistence.repo.ICatDeclaracionRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatEsquemaRegla8Repository;
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
public class CatEsquemaRegla8ServiceImpl extends AbstractCatalogService<CatEsquemaRegla8,Short>{
    @Autowired
    private ICatEsquemaRegla8Repository catEsquemaRegla8Repository;
    @Override
    public String getCatalogKey() {
        return "cat_esquema_regla8";
    }

    @Override
    public Class<CatEsquemaRegla8> getEntityClass() {
        return CatEsquemaRegla8.class;
    }

    @Override
    protected JpaRepository<CatEsquemaRegla8, Short> getRepository() {
        return catEsquemaRegla8Repository;
    }

    @Override
    protected Class<Short> getIdClass() {
        return Short.class;
    }


    @Override
    public Page<CatEsquemaRegla8> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatEsquemaRegla8> spec =
                GenericSearchSpecification.<CatEsquemaRegla8>searchInFields(
                        search,
                        List.of("nombre","descRequisito")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );



        return catEsquemaRegla8Repository.findAll(spec, pageable);
    }
}
