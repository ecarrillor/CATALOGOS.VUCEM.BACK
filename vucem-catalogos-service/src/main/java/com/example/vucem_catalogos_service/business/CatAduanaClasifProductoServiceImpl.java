package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatAduanaClasifProductoService;
import com.example.vucem_catalogos_service.model.entity.*;
import com.example.vucem_catalogos_service.persistence.repo.ICatAduanaClasifProductoRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatAduanaRepository;
import com.example.vucem_catalogos_service.persistence.repo.IClasificacionProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CatAduanaClasifProductoServiceImpl implements ICatAduanaClasifProductoService {

    @Autowired
    private ICatAduanaClasifProductoRepository repository;

    @Autowired
    private ICatAduanaRepository aduanaRepository;

    @Autowired
    private IClasificacionProductoRepository clasificacionProductoRepository;


    @Override
    public Page<CatAduanaClasifProd> catAduanaListAll(int page, int size, String search) {

        Pageable pageable = PageRequest.of(page, size);
        Specification<CatAduanaClasifProd> spec = (root, query, cb) -> {

            if (search == null || search.isBlank()) {
                return cb.conjunction();
            }

            query.distinct(true);
            String like = "%" + search.toUpperCase() + "%";

            if (search.matches("\\d+")) {

                return cb.or(
                        cb.equal(root.get("id"), Long.valueOf(search)),
                        cb.like(cb.upper(root.join("aduana").get("nombre")), like),
                        cb.like(cb.upper(root.join("idClasifProducto").get("nombre")), like)
                );
            }
            return cb.or(
                    cb.like(cb.upper(root.join("aduana").get("nombre")), like),
                    cb.like(cb.upper(root.join("idClasifProducto").get("nombre")), like)
            );
        };

        return repository.findAll(spec, pageable);
    }

    @Override
    public CatAduanaClasifProd crearAduanaClasifProducto(CatAduanaClasifProd aduanaClasifProd) {
        CatAduana aduana = aduanaRepository
                .findById(aduanaClasifProd.getAduana().getCveAduana())
                .orElseThrow(() -> new RuntimeException("TipoAduana no encontrado"));

        CatClasifProducto clasifProducto = clasificacionProductoRepository
                .findById(aduanaClasifProd.getIdClasifProducto().getIdClasifProduct())
                .orElseThrow(() -> new RuntimeException("Clasificación no encontrada"));


        aduanaClasifProd.setAduana(aduana);
        aduanaClasifProd.setIdClasifProducto(clasifProducto);

        return repository.save(aduanaClasifProd);
    }
}
