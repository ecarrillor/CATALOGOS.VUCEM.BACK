package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatMoneda;
import com.example.vucem_catalogos_service.model.entity.CatMontoExportacion;
import com.example.vucem_catalogos_service.model.entity.CatMontoExportacionId;
import com.example.vucem_catalogos_service.persistence.repo.ICatMonedaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatMontoExportacionRepository;
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

@Transactional
@Service
public class CatMontoExportacionServiceImpl extends AbstractCatalogService<CatMontoExportacion, CatMontoExportacionId>{
    @Autowired
    private ICatMontoExportacionRepository catMontoExportacionRepository;

    @Override
    public String getCatalogKey() {
        return "cat_monto_exportacion";
    }

    @Override
    public Class<CatMontoExportacion> getEntityClass() {
        return CatMontoExportacion.class;
    }

    @Override
    protected JpaRepository<CatMontoExportacion, CatMontoExportacionId> getRepository() {
        return catMontoExportacionRepository;
    }

    @Override
    protected Class<CatMontoExportacionId> getIdClass() {
        return CatMontoExportacionId.class;
    }


    @Override
    public Page<CatMontoExportacion> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatMontoExportacion> spec =
                GenericSearchSpecification.<CatMontoExportacion>searchInFields(
                        search,
                        List.of("razonSocial","monto")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );



        return catMontoExportacionRepository.findAll(spec, pageable);
    }
}
