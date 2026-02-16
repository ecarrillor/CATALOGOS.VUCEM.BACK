package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatRegimen;
import com.example.vucem_catalogos_service.model.entity.CatSemanaLaboral;
import com.example.vucem_catalogos_service.persistence.repo.ICatRegimenRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatSemanaLaboralRepository;
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
public class CatSemanaLaboralServiceImpl extends AbstractCatalogService<CatSemanaLaboral,Integer>{
    @Autowired
    private ICatSemanaLaboralRepository catSemanaLaboralRepository;

    @Override
    public String getCatalogKey() {
        return "cat_semana_laboral";
    }

    @Override
    public Class<CatSemanaLaboral> getEntityClass() {
        return CatSemanaLaboral.class;
    }

    @Override
    protected JpaRepository<CatSemanaLaboral, Integer> getRepository() {
        return catSemanaLaboralRepository;
    }

    @Override
    protected Class<Integer> getIdClass() {
        return Integer.class;
    }


    @Override
    public Page<CatSemanaLaboral> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatSemanaLaboral> spec =
                GenericSearchSpecification.<CatSemanaLaboral>searchInFields(
                        search,
                        List.of("cveRegimen","nombre")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );



        return catSemanaLaboralRepository.findAll(spec, pageable);
    }
}
