package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatProducto;
import com.example.vucem_catalogos_service.model.entity.CatPuntoVerificacion;
import com.example.vucem_catalogos_service.persistence.repo.ICatProductoRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatPuntoVerificacionRepository;
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
public class CatPuntoVerificacionServiceImpl extends AbstractCatalogService<CatPuntoVerificacion,Integer>{
    @Autowired
    private ICatPuntoVerificacionRepository catPuntoVerificacionRepository;

    @Override
    public String getCatalogKey() {
        return "cat_punto_verificacion";
    }

    @Override
    public Class<CatPuntoVerificacion> getEntityClass() {
        return CatPuntoVerificacion.class;
    }

    @Override
    protected JpaRepository<CatPuntoVerificacion, Integer> getRepository() {
        return catPuntoVerificacionRepository;
    }

    @Override
    protected Class<Integer> getIdClass() {
        return Integer.class;
    }


    @Override
    public Page<CatPuntoVerificacion> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatPuntoVerificacion> spec =
                GenericSearchSpecification.<CatPuntoVerificacion>searchInFields(
                        search,
                        List.of("nomPuntoVerif")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );



        return catPuntoVerificacionRepository.findAll(spec, pageable);
    }
}
