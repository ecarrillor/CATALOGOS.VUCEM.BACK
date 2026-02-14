package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatCapituloFraccion;
import com.example.vucem_catalogos_service.model.entity.CatCondicionUso;
import com.example.vucem_catalogos_service.persistence.repo.ICatCondicionUsoRepository;
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
public class CatCondicionUsoServiceImpl extends AbstractCatalogService<CatCondicionUso, Short>{
    @Autowired
    private ICatCondicionUsoRepository catCondicionUsoRepository;

    @Override
    public String getCatalogKey() {
        return "cat-condicionUso";
    }

    @Override
    public Class<CatCondicionUso> getEntityClass() {
        return CatCondicionUso.class;
    }

    @Override
    protected JpaRepository<CatCondicionUso, Short> getRepository() {
        return catCondicionUsoRepository;
    }

    @Override
    protected Class<Short> getIdClass() {
        return Short.class;
    }


    @Override
    public Page<CatCondicionUso> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatCondicionUso> spec =
                GenericSearchSpecification.<CatCondicionUso>searchInFields(
                        search,
                        List.of("descripcion", "descripcionHtml")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );



        return catCondicionUsoRepository.findAll(spec, pageable);
    }
}
