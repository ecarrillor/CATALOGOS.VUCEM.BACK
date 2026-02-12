package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatAduana;
import com.example.vucem_catalogos_service.model.entity.CatAduanaClasifProd;
import com.example.vucem_catalogos_service.model.entity.CatAga;
import com.example.vucem_catalogos_service.persistence.repo.ICatAduanaClasifProdRepository;
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
public class CatAduanaClassifyProdServiceImpl extends AbstractCatalogService<CatAduanaClasifProd, Long> {
    @Autowired
    private ICatAduanaClasifProdRepository iCatAduanaClasifProdRepository;

    @Override
    public String getCatalogKey() {
        return "cat-aduana-clasif-prod";
    }

    @Override
    public Page<CatAduanaClasifProd> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatAduanaClasifProd> spec =
                GenericSearchSpecification.<CatAduanaClasifProd>searchInFields(
                        search,
                        List.of("clave", "nombre", "descripcion")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );



        return iCatAduanaClasifProdRepository.findAll(spec, pageable);
    }



    @Override
    public Class<CatAduanaClasifProd> getEntityClass() {
        return CatAduanaClasifProd.class;
    }

    @Override
    protected JpaRepository<CatAduanaClasifProd, Long> getRepository() {
        return iCatAduanaClasifProdRepository;
    }




}
