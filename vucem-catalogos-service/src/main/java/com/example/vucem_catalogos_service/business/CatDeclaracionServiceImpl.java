package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatDeclaracion;
import com.example.vucem_catalogos_service.persistence.repo.ICatDeclaracionRepository;
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
public class CatDeclaracionServiceImpl extends AbstractCatalogService<CatDeclaracion,String>{
    @Autowired
    private ICatDeclaracionRepository declaracionRepository;
    @Override
    public String getCatalogKey() {
        return "cat-declaracion";
    }

    @Override
    public Class<CatDeclaracion> getEntityClass() {
        return CatDeclaracion.class;
    }

    @Override
    protected JpaRepository<CatDeclaracion, String> getRepository() {
        return declaracionRepository;
    }

    @Override
    protected Class<String> getIdClass() {
        return String.class;
    }


    @Override
    public Page<CatDeclaracion> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatDeclaracion> spec =
                GenericSearchSpecification.<CatDeclaracion>searchInFields(
                        search,
                        List.of("descDeclaracion")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );



        return declaracionRepository.findAll(spec, pageable);
    }
}
