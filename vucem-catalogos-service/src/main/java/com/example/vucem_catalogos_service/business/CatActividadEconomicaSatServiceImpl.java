package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatActividadEconomicaSat;
import com.example.vucem_catalogos_service.model.entity.CatAduana;
import com.example.vucem_catalogos_service.persistence.repo.ICatActividadEconomicaSatRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatAduanaRepository;
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
public class CatActividadEconomicaSatServiceImpl extends  AbstractCatalogService<CatActividadEconomicaSat, Long> {
    @Autowired
    private ICatActividadEconomicaSatRepository catActividadEconomicaSatRepository;


    @Override
    public String getCatalogKey() {
        return "cat-actEconomicaSat";
    }

    @Override
    public Class<CatActividadEconomicaSat> getEntityClass() {
        return CatActividadEconomicaSat.class;
    }

    @Override
    protected JpaRepository<CatActividadEconomicaSat, Long> getRepository() {
        return catActividadEconomicaSatRepository;
    }

    @Override
    protected Class<Long> getIdClass() {
        return Long.class;
    }


    @Override
    public Page<CatActividadEconomicaSat> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatActividadEconomicaSat> spec =
                GenericSearchSpecification.<CatActividadEconomicaSat>searchInFields(
                        search,
                        List.of("clave", "descripcion", "descripcion")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );



        return catActividadEconomicaSatRepository.findAll(spec, pageable);
    }
}
