package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatAduanaClasifProductoService;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.dto.AduanaClasifProducto.CatAduanaClasifProdRequestDTO;
import com.example.vucem_catalogos_service.model.dto.AduanaClasifProducto.CatAduanaClasifProdResponseDTO;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.*;
import com.example.vucem_catalogos_service.persistence.repo.ICatAduanaClasifProductoRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatAduanaRepository;
import com.example.vucem_catalogos_service.persistence.repo.IClasificacionProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CatAduanaClasifProductoServiceImpl implements ICatAduanaClasifProductoService {

    @Autowired
    private ICatAduanaClasifProductoRepository repository;

    @Autowired
    private ICatAduanaRepository aduanaRepository;

    @Autowired
    private IClasificacionProductoRepository clasificacionProductoRepository;

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idAduanaClasifProduct", "a.id",
            "cveAduana", "a.aduana.cveAduana",
            "nombreAduana", "a.aduana.nombre",
            "nombreClasifProducto", "a.idClasifProducto.nombre"
    );

    @Override
    public PageResponseDTO<CatAduanaClasifProdResponseDTO> catAduanaListAll(String search, Long idTipoTramite, String sortBy, String sortDir, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if (search != null && !search.isBlank()) {

            String s = search.trim().toLowerCase();
            if (s.equals("activo")) {
                activo = true;
            } else if (s.equals("inactivo")) {
                activo = false;
            } else {
                texto = "%" + search.trim().toLowerCase() + "%";
            }
        }

        Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        Pageable pageableWithSort = sort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort)
                : pageable;

        Page<CatAduanaClasifProdResponseDTO> page =
                repository.search(texto, activo, idTipoTramite, pageableWithSort);

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
        entity.setBlnActivo(true);

        CatAduanaClasifProd saved = repository.save(entity);

        return CatAduanaClasifProdResponseDTO.builder()
                .idAduanaClasifProduct(saved.getId())
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

        if (dto.getCveAduana() != null) {
            entity.setAduana(
                    aduanaRepository
                            .findById(dto.getCveAduana())
                            .orElseThrow(() -> new RuntimeException("Aduana no encontrada"))
            );
        }

        if (dto.getIdClasifProducto() != null) {
            entity.setIdClasifProducto(
                    clasificacionProductoRepository
                            .findById(dto.getIdClasifProducto())
                            .orElseThrow(() -> new RuntimeException("Clasificacion no encontrada"))
            );
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

        CatAduanaClasifProd updated = repository.save(entity);

        return CatAduanaClasifProdResponseDTO.builder()
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
        List<CatAduana> productos = aduanaRepository.findAllByBlnActivoTrueOrderByNombreAsc();
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
    public List<ClasifProductoTraDTO> listadoClasificacionProducto(Long idTipoTramite) {
        return clasificacionProductoRepository.listadoClasificacionProducto(idTipoTramite);
    }

    @Override
    public List<ClasifProductoTraDTO> listadoClasifTram() {
        return repository.listadoClasifPrR();
    }

    @Override
    public ClasifProductoTraDTO lastAduanaClasifProd() {
        return repository.findTopByOrderByIdDesc()
                .map(e -> new ClasifProductoTraDTO(e.getId(), e.getAduana().getNombre()))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existen registros en Aduana Clasificación Producto"));
    }

}
