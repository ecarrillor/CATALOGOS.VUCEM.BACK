package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatTipoDocumento;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoDocumentoRepository;
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
public class CatTipoDocumentoServiceImpl extends AbstractCatalogService<CatTipoDocumento,Short>{
    @Autowired
    private ICatTipoDocumentoRepository catTipoDocumentoRepository;

    @Override
    public String getCatalogKey() {
        return "cat-tipoDocumento";
    }

    @Override
    public Class<CatTipoDocumento> getEntityClass() {
        return CatTipoDocumento.class;
    }

    @Override
    protected JpaRepository<CatTipoDocumento, Short> getRepository() {
        return catTipoDocumentoRepository;
    }

    @Override
    protected Class<Short> getIdClass() {
        return Short.class;
    }


    @Override
    public Page<CatTipoDocumento> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatTipoDocumento> spec =
                GenericSearchSpecification.<CatTipoDocumento>searchInFields(
                        search,
                        List.of("nombre")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );


        return catTipoDocumentoRepository.findAll(spec, pageable);
    }
}
