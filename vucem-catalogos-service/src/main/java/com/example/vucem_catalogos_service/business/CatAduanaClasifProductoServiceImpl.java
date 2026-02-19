package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatAduanaClasifProductoService;
import com.example.vucem_catalogos_service.model.dto.AduanaClasifProducto.CatAduanaClasifProdRequestDTO;
import com.example.vucem_catalogos_service.model.dto.AduanaClasifProducto.CatAduanaClasifProdResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.*;
import com.example.vucem_catalogos_service.persistence.repo.ICatAduanaClasifProductoRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatAduanaRepository;
import com.example.vucem_catalogos_service.persistence.repo.IClasificacionProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

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
    public PageResponseDTO<CatAduanaClasifProdResponseDTO> catAduanaListAll(String search, Pageable pageable) {
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

        Page<CatAduanaClasifProdResponseDTO> page =
                repository.search(texto, activo, pageable);

        return PageResponseDTO.<CatAduanaClasifProdResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatAduanaClasifProdResponseDTO crearAduanaClasifProducto(CatAduanaClasifProdRequestDTO dto) {

        if (repository.existsById(dto.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }

        CatAduanaClasifProd entity = new CatAduanaClasifProd();

        entity.setId(dto.getId());

        entity.setAduana(
                aduanaRepository
                        .findById(dto.getCveAduana())
                        .orElseThrow(() -> new RuntimeException("Aduana no encontrada"))
        );

        entity.setIdClasifProducto(
                clasificacionProductoRepository
                        .findById(dto.getIdClasifProducto())
                        .orElseThrow(() -> new RuntimeException("Clasificacion no encontrada"))
        );


        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatAduanaClasifProd saved = repository.save(entity);

        return CatAduanaClasifProdResponseDTO.builder()
                .cveAduana(saved.getAduana().getCveAduana())
                .nombreAduana(saved.getAduana().getNombre())
                .idClasifProducto(saved.getIdClasifProducto().getIdClasifProduct())
                .nombreClasifProducto(saved.getIdClasifProducto().getNombre())
                .fecIniVigencia(saved.getFecIniVigencia())
                .fecFinVigencia(saved.getFecFinVigencia())
                .blnActivo(saved.getBlnActivo())
                .build();
    }

    @Override
    public CatAduanaClasifProdResponseDTO findById(Long id) {
        CatAduanaClasifProd entity = repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Registro no encontrado con id: " + id
                        )
                );

        return CatAduanaClasifProdResponseDTO.builder()
                .idAduanaClasifProduct(entity.getId())
                .cveAduana(entity.getAduana().getCveAduana())
                .nombreAduana(entity.getAduana().getNombre())
                .idClasifProducto(entity.getIdClasifProducto().getIdClasifProduct())
                .nombreClasifProducto(entity.getIdClasifProducto().getNombre())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }

    @Override
        public CatAduanaClasifProdResponseDTO update(Long id, CatAduanaClasifProdRequestDTO dto) {
        CatAduanaClasifProd entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        entity.setAduana(
                aduanaRepository
                        .findById(dto.getCveAduana())
                        .orElseThrow(() -> new RuntimeException("Aduana no encontrada"))
        );

        entity.setIdClasifProducto(
                clasificacionProductoRepository
                        .findById(dto.getIdClasifProducto())
                        .orElseThrow(() -> new RuntimeException("Clasificacion no encontrada"))
        );

        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatAduanaClasifProd updated = repository.save(entity);

        return  CatAduanaClasifProdResponseDTO.builder()
                .idClasifProducto(updated.getId())
                .cveAduana(updated.getAduana().getCveAduana())
                .nombreAduana(updated.getAduana().getNombre())
                .idClasifProducto(updated.getIdClasifProducto().getIdClasifProduct())
                .nombreClasifProducto(updated.getIdClasifProducto().getNombre())
                .fecIniVigencia(updated.getFecIniVigencia())
                .fecFinVigencia(updated.getFecFinVigencia())
                .blnActivo(updated.getBlnActivo())
                .build();
    }

    @Override
    public List<SelectDTO> listadoAduana() {
        List<CatAduana> productos = aduanaRepository.findAll();
        List<SelectDTO> resultado = new ArrayList<>();

        for (CatAduana producto : productos) {
            SelectDTO dto = new SelectDTO();
            dto.setCve(producto.getCveAduana());
            dto.setNombre(producto.getNombre());
            resultado.add(dto);
        }
        return resultado;
    }

    @Override
    public List<SelectDTO> listadoClasificacionProducto() {
        List<CatClasifProducto> productos = clasificacionProductoRepository.findAll();
        List<SelectDTO> resultado = new ArrayList<>();

        for (CatClasifProducto producto : productos) {
            SelectDTO dto = new SelectDTO();
            dto.setId(producto.getIdClasifProduct());
            dto.setNombre(producto.getNombre());
            resultado.add(dto);
        }
        return resultado;
    }


}
