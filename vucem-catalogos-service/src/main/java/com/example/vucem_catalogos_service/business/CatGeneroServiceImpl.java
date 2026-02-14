package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatEsquemaRegla8;
import com.example.vucem_catalogos_service.model.entity.CatGenero;
import com.example.vucem_catalogos_service.persistence.repo.ICatGeneroRepository;
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
@Transactional
@Service
public class CatGeneroServiceImpl extends AbstractCatalogService<CatGenero,Integer>{
    @Autowired
    private ICatGeneroRepository catGeneroRepository;

    @Override
    public String getCatalogKey() {
        return "cat_genero";
    }

    @Override
    public Class<CatGenero> getEntityClass() {
        return CatGenero.class;
    }

    @Override
    protected JpaRepository<CatGenero, Integer> getRepository() {
        return catGeneroRepository;
    }

    @Override
    protected Class<Integer> getIdClass() {
        return Integer.class;
    }


    @Override
    public Page<CatGenero> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatGenero> spec =
                GenericSearchSpecification.<CatGenero>searchInFields(
                        search,
                        List.of("descGenero")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );



        return catGeneroRepository.findAll(spec, pageable);
    }
}
