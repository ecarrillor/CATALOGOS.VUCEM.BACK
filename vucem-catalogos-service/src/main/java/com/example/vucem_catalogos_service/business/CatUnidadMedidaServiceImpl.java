package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatPuntoVerificacion;
import com.example.vucem_catalogos_service.model.entity.CatUnidadMedida;
import com.example.vucem_catalogos_service.persistence.repo.ICatUnidadMedidaRepository;
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
public class CatUnidadMedidaServiceImpl extends AbstractCatalogService<CatUnidadMedida,String>{
    @Autowired
    private ICatUnidadMedidaRepository catUnidadMedidaRepository;

    @Override
    public String getCatalogKey() {
        return "cat_unidad_medida";
    }

    @Override
    public Class<CatUnidadMedida> getEntityClass() {
        return CatUnidadMedida.class;
    }

    @Override
    protected JpaRepository<CatUnidadMedida, String> getRepository() {
        return catUnidadMedidaRepository;
    }

    @Override
    protected Class<String> getIdClass() {
        return String.class;
    }


    @Override
    public Page<CatUnidadMedida> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatUnidadMedida> spec =
                GenericSearchSpecification.<CatUnidadMedida>searchInFields(
                        search,
                        List.of("cveUnidadMedida","descripcion")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );



        return catUnidadMedidaRepository.findAll(spec, pageable);
    }
}
