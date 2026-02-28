package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatDescripcionProdService;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.DescripcionProd.CatDescripcionProdRequestDTO;
import com.example.vucem_catalogos_service.model.dto.DescripcionProd.CatDescripcionProdResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatDescripcionProd;
import com.example.vucem_catalogos_service.persistence.repo.ICatDescripcionProdRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class CatDescripcionProdServiceImpl implements ICatDescripcionProdService {

    @Autowired
    private ICatDescripcionProdRepository repository;

    @Override
    public PageResponseDTO<CatDescripcionProdResponseDTO> listAll(String search, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if (search != null && !search.isBlank()) {
            String s = search.trim().toLowerCase();
            if (s.equals("activo")) {
                activo = true;
            } else if (s.equals("inactivo")) {
                activo = false;
            } else {
                texto = search;
            }
        }

        Page<CatDescripcionProdResponseDTO> page = repository.search(texto, activo, pageable);

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
    public CatDescripcionProdResponseDTO crear(CatDescripcionProdRequestDTO dto) {

        if (repository.existsById(dto.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }

        CatDescripcionProd entity = new CatDescripcionProd();

        entity.setId(dto.getId());
        entity.setDescripcionProducto(dto.getDescripcionProducto());
        entity.setFecCaptura(dto.getFecCaptura());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(true);

        CatDescripcionProd saved = repository.save(entity);

        return toResponseDTO(saved);
    }

    @Override
    public CatDescripcionProdResponseDTO findById(Integer id) {
        CatDescripcionProd entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Registro no encontrado con id: " + id
                ));

        return toResponseDTO(entity);
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
}
