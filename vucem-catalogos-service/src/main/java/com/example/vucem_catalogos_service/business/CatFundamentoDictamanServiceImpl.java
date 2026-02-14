package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatEsquemaRegla8;
import com.example.vucem_catalogos_service.model.entity.CatFundamentoDictaman;
import com.example.vucem_catalogos_service.persistence.repo.ICatFundamentoDictamanRepository;
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
public class CatFundamentoDictamanServiceImpl extends AbstractCatalogService<CatFundamentoDictaman,Integer>{
    @Autowired
    private ICatFundamentoDictamanRepository catFundamentoDictamanRepository;

    @Override
    public String getCatalogKey() {
        return "cat_fundamento_dictamen";
    }

    @Override
    public Class<CatFundamentoDictaman> getEntityClass() {
        return CatFundamentoDictaman.class;
    }

    @Override
    protected JpaRepository<CatFundamentoDictaman, Integer> getRepository() {
        return catFundamentoDictamanRepository;
    }

    @Override
    protected Class<Integer> getIdClass() {
        return Integer.class;
    }


    @Override
    public Page<CatFundamentoDictaman> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatFundamentoDictaman> spec =
                GenericSearchSpecification.<CatFundamentoDictaman>searchInFields(
                        search,
                        List.of("descripcion")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );



        return catFundamentoDictamanRepository.findAll(spec, pageable);
    }
}
