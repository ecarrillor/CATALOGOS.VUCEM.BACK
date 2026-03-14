package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatIdentificadorPrevalidador;
import com.example.vucem_catalogos_service.persistence.repo.ICatIdentificadorPrevalidadorRepository;
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
public class CatIdentificadorPrevalidadorServiceImpl extends AbstractCatalogService<CatIdentificadorPrevalidador, Long>{
    @Autowired
    private ICatIdentificadorPrevalidadorRepository catIdentificadorPrevalidadorRepository;

    @Override
    public String getCatalogKey() {
        return "cat_identificador_prevalidor";
    }

    @Override
    public Class<CatIdentificadorPrevalidador> getEntityClass() {
        return CatIdentificadorPrevalidador.class;
    }

    @Override
    protected JpaRepository<CatIdentificadorPrevalidador, Long> getRepository() {
        return catIdentificadorPrevalidadorRepository;
    }

    @Override
    protected Class<Long> getIdClass() {
        return Long.class;
    }


    @Override
    public Page<CatIdentificadorPrevalidador> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatIdentificadorPrevalidador> spec =
                GenericSearchSpecification.<CatIdentificadorPrevalidador>searchInFields(
                        search,
                        List.of("descripcion")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );



        return catIdentificadorPrevalidadorRepository.findAll(spec, pageable);
    }
}
