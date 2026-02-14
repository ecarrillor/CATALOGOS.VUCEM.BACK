package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatCondicionUso;
import com.example.vucem_catalogos_service.model.entity.CatControlReferencia;
import com.example.vucem_catalogos_service.persistence.repo.ICatControlReferenciaRepository;
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
public class CatControlReferenciaServiceImpl extends AbstractCatalogService<CatControlReferencia,Integer>{
    @Autowired
    private ICatControlReferenciaRepository catControlReferenciaRepository;

    @Override
    public String getCatalogKey() {
        return "cat-ctrlReferencia";
    }

    @Override
    public Class<CatControlReferencia> getEntityClass() {
        return CatControlReferencia.class;
    }

    @Override
    protected JpaRepository<CatControlReferencia, Integer> getRepository() {
        return catControlReferenciaRepository;
    }

    @Override
    protected Class<Integer> getIdClass() {
        return Integer.class;
    }


    @Override
    public Page<CatControlReferencia> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatControlReferencia> spec =
                GenericSearchSpecification.<CatControlReferencia>searchInFields(
                        search,
                        List.of("descripcion", "descripcionHtml")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );



        return catControlReferenciaRepository.findAll(spec, pageable);
    }
}
