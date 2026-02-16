package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatTipoAduana;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoAduanaRepository;
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
public class CatTipoAduanaServiceImpl extends AbstractCatalogService<CatTipoAduana, String>{
    @Autowired
    private ICatTipoAduanaRepository catTipoAduanaRepository;

    @Override
    public String getCatalogKey() {
        return "cat_tipo_aduana";
    }

    @Override
    public Class<CatTipoAduana> getEntityClass() {
        return CatTipoAduana.class;
    }

    @Override
    protected JpaRepository<CatTipoAduana, String> getRepository() {
        return catTipoAduanaRepository;
    }

    @Override
    protected Class<String> getIdClass() {
        return String.class;
    }


    @Override
    public Page<CatTipoAduana> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatTipoAduana> spec =
                GenericSearchSpecification.<CatTipoAduana>searchInFields(
                        search,
                        List.of("nombre")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );


        return catTipoAduanaRepository.findAll(spec, pageable);
    }
}
