package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatDescripcionProdService;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.DescripcionProd.CatDescripcionProdRequestDTO;
import com.example.vucem_catalogos_service.model.dto.DescripcionProd.CatDescripcionProdResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatDescripcionProd;
import com.example.vucem_catalogos_service.model.entity.CatFraccionTtra;
import com.example.vucem_catalogos_service.model.entity.CatFraccionTtraDescProd;
import com.example.vucem_catalogos_service.model.entity.CatTipoTramite;
import com.example.vucem_catalogos_service.persistence.repo.ICatDescripcionProdRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatFraccionTtraDescProdRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatFraccionTtraRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CatDescripcionProdServiceImpl implements ICatDescripcionProdService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ICatDescripcionProdRepository repository;

    @Autowired
    private ICatTipoTramiteRepository catTipoTramiteRepository;

    @Autowired
    private ICatFraccionTtraRepository catFraccionTtraRepository;

    @Autowired
    private ICatFraccionTtraDescProdRepository catFraccionTtraDescProdRepository;

    @Override
    public PageResponseDTO<CatDescripcionProdResponseDTO> listAll(String search, Long idTipoTramite, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if (search != null && !search.isBlank()) {

            String s = search.trim().toLowerCase();

            if (s.equals("activo")) {
                activo = true;
            }
            else if (s.equals("inactivo")) {
                activo = false;
            }
            else {
                texto = "%" + search.trim().toLowerCase() + "%";
            }
        }

        Page<CatDescripcionProdResponseDTO> page = repository.search(texto, activo,idTipoTramite, pageable);

        return PageResponseDTO.<CatDescripcionProdResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatDescripcionProdResponseDTO crear(CatDescripcionProdRequestDTO dto, Long idTipoTramite) {

        CatTipoTramite tipoTramite = catTipoTramiteRepository.findById(idTipoTramite)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No existe TipoTramite con id: " + idTipoTramite
                ));

        if (repository.existsById(dto.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ya existe un registro con ese id: "+ dto.getId()
            );
        }

        CatDescripcionProd descripcion = new CatDescripcionProd();
        descripcion.setId(dto.getId());
        descripcion.setDescripcionProducto(dto.getDescripcionProducto());
        descripcion.setFecCaptura(LocalDate.now());
        descripcion.setFecIniVigencia(dto.getFecIniVigencia());
        descripcion.setFecFinVigencia(dto.getFecFinVigencia());
        descripcion.setBlnActivo(true);

        entityManager.persist(descripcion);

        List<CatFraccionTtra> fracciones =
                catFraccionTtraRepository.findByIdTipoTramiteId(tipoTramite.getId());

        if (fracciones.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El TipoTramite no tiene fracciones asociadas"
            );
        }

        Long maxId = catFraccionTtraRepository.findMaxId();
        Long nuevoId = (maxId == null ? 1 : maxId + 1);

        for (CatFraccionTtra fraccion : fracciones) {

            CatFraccionTtraDescProd relacion = new CatFraccionTtraDescProd();

            relacion.setId(nuevoId++);
            relacion.setIdFraccionGob(fraccion);
            relacion.setIdDescripcionProd(descripcion);
            relacion.setBlnActivo(true);
            relacion.setFecIniVigencia(LocalDate.now());

            entityManager.persist(relacion);
        }

        entityManager.flush();

        return toResponseDTO(descripcion);
    }

    @Override
    public CatDescripcionProdResponseDTO findById(Integer id, Long idTipoTramite) {
        CatDescripcionProdResponseDTO entity = repository.findByIdTipoTramite(id, idTipoTramite);

        if (entity == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Registro no encontrado con id: " + id
            );
        }
        CatDescripcionProd catDescripcionProd = new CatDescripcionProd();
        catDescripcionProd.setDescripcionProducto(entity.getDescripcionProducto());
        catDescripcionProd.setId(entity.getIdDescripcionProd());
        catDescripcionProd.setFecIniVigencia(entity.getFecIniVigencia());
        catDescripcionProd.setFecFinVigencia(entity.getFecFinVigencia());
        catDescripcionProd.setBlnActivo(entity.getBlnActivo());
        catDescripcionProd.setFecCaptura(entity.getFecCaptura());

        return toResponseDTO(catDescripcionProd);
    }

    @Override
    public CatDescripcionProdResponseDTO update(Integer id, CatDescripcionProdRequestDTO dto) {

        CatDescripcionProd entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Registro no encontrado con id: " + id
                ));

        if (dto.getDescripcionProducto() != null) {
            entity.setDescripcionProducto(dto.getDescripcionProducto());
        }

        if (dto.getFecCaptura() != null) {
            entity.setFecCaptura(dto.getFecCaptura());
        }

        if (dto.getFecIniVigencia() != null) {
            entity.setFecIniVigencia(dto.getFecIniVigencia());
        }

        if (dto.getFecFinVigencia() != null) {
            entity.setFecFinVigencia(dto.getFecFinVigencia());
        }

        if (dto.getBlnActivo() != null) {
            entity.setBlnActivo(dto.getBlnActivo());
        }

        CatDescripcionProd updated = repository.save(entity);

        return toResponseDTO(updated);
    }

    @Override
    public List<ClasifProductoTraDTO> listadoTipoTramite() {
        return repository.listadoTipoTramite();
    }

    private CatDescripcionProdResponseDTO toResponseDTO(CatDescripcionProd entity) {
        return CatDescripcionProdResponseDTO.builder()
                .idDescripcionProd(entity.getId())
                .descripcionProducto(entity.getDescripcionProducto())
                .fecCaptura(entity.getFecCaptura())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }

    @Override
    public ClasifProductoTraDTO lastDescripcionProd() {
        return repository.findTopByOrderByIdDesc()
                .map(e -> new ClasifProductoTraDTO(e.getId().longValue(), e.getDescripcionProducto()))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existen registros en Descripción Producto"));
    }
}
