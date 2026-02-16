package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatMontoExportacion;
import com.example.vucem_catalogos_service.model.entity.CatMontoExportacionId;
import com.example.vucem_catalogos_service.model.entity.CatProducto;
import com.example.vucem_catalogos_service.persistence.repo.ICatProductoRepository;
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
public class CatProductoServiceImpl extends AbstractCatalogService<CatProducto,String >{
    @Autowired
    private ICatProductoRepository catProductoRepository;

    @Override
    public String getCatalogKey() {
        return "cat_producto";
    }

    @Override
    public Class<CatProducto> getEntityClass() {
        return CatProducto.class;
    }

    @Override
    protected JpaRepository<CatProducto, String> getRepository() {
        return catProductoRepository;
    }

    @Override
    protected Class<String> getIdClass() {
        return String.class;
    }


    @Override
    public Page<CatProducto> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatProducto> spec =
                GenericSearchSpecification.<CatProducto>searchInFields(
                        search,
                        List.of("nomPuntoVerif","sigla","nombre","descripcion")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );



        return catProductoRepository.findAll(spec, pageable);
    }
}
