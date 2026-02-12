package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatAduana;
import com.example.vucem_catalogos_service.model.entity.CatAduanaClasifProd;
import com.example.vucem_catalogos_service.persistence.repo.ICatAduanaRepository;
import com.example.vucem_catalogos_service.persistence.specification.GenericFilterSpecification;
import com.example.vucem_catalogos_service.persistence.specification.GenericSearchSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CatAduanaServiceImpl extends AbstractCatalogService<CatAduana, String> {

    @Autowired
    private ICatAduanaRepository catAduanaRepository;


    @Override
    public String getCatalogKey() {
        return "cat-aduana";
    }

    @Override
    public Page<CatAduana> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatAduana> spec =
                GenericSearchSpecification.<CatAduana>searchInFields(
                        search,
                        List.of("clave", "nombre", "descripcion")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );



        return catAduanaRepository.findAll(spec, pageable);
    }





    @Override
    public Class<CatAduana> getEntityClass() {
        return CatAduana.class;
    }

    @Override
    protected JpaRepository<CatAduana, String> getRepository() {
        return catAduanaRepository;
    }


}
