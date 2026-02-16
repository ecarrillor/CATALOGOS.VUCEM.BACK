package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatScian;
import com.example.vucem_catalogos_service.persistence.repo.ICatScianRepository;
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
public class CatScianServiceImpl extends AbstractCatalogService<CatScian, String>{
    @Autowired
    private ICatScianRepository catScianRepository;

    @Override
    public String getCatalogKey() {
        return "cat-scian";
    }

    @Override
    public Class<CatScian> getEntityClass() {
        return CatScian.class;
    }

    @Override
    protected JpaRepository<CatScian, String> getRepository() {
        return catScianRepository;
    }

    @Override
    protected Class<String> getIdClass() {
        return String.class;
    }


    @Override
    public Page<CatScian> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatScian> spec =
                GenericSearchSpecification.<CatScian>searchInFields(
                        search,
                        List.of("cveScian","descScian")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );



        return catScianRepository.findAll(spec, pageable);
    }
}
