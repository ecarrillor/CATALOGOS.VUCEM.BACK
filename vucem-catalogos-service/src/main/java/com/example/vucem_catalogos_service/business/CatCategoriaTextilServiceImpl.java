package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatCategoriaTextilService;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.dto.CategoriaTextil.CatCategoriaTextilRequestDTO;
import com.example.vucem_catalogos_service.model.dto.CategoriaTextil.CatCategoriaTextilResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatCategoriaTextil;
import com.example.vucem_catalogos_service.model.entity.CatUnidadMedida;
import com.example.vucem_catalogos_service.persistence.repo.ICatCategoriaTextilRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatUnidadMedidaRepository;
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
import java.util.Set;

@Service
@Transactional
public class CatCategoriaTextilServiceImpl implements ICatCategoriaTextilService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idCategoriaTextil",       "a.id",
            "descripcion",             "a.descripcion",
            "codigoCategoriaTextil",   "a.codCategoriaTextil",
            "factorConversion",        "a.factConversion",
            "unidadMedida",            "b.descripcion",
            "unidadMedidaEquivalente", "c.descripcion"
    );

    private static final Set<String> TEXT_COLUMNS = Set.of(
            "descripcion", "unidadMedida", "unidadMedidaEquivalente"
    );

    private static final String DEFAULT_SORT_KEY = "idCategoriaTextil";

    @Autowired
    private ICatCategoriaTextilRepository iCatCategoriaTextilRepository;

    @Autowired
    private ICatUnidadMedidaRepository iCatUnidadMedidaRepository;

    @Override
    public PageResponseDTO<CatCategoriaTextilResponseDTO> listarCategoriaTextil(String search, String sortBy, String sortDir, Pageable pageable) {
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

        Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS, TEXT_COLUMNS, DEFAULT_SORT_KEY);
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        Page<CatCategoriaTextilResponseDTO> page =
                iCatCategoriaTextilRepository.search(texto, activo, sortedPageable);

        return PageResponseDTO.<CatCategoriaTextilResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatCategoriaTextilResponseDTO crearCategoriaTextil(CatCategoriaTextilRequestDTO dto) {
        if (iCatCategoriaTextilRepository.existsById(dto.getIdCategoriaTextil())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }

        CatCategoriaTextil entity = new CatCategoriaTextil();

        entity.setId(dto.getIdCategoriaTextil());

        entity.setCveUnidadMedida(
                iCatUnidadMedidaRepository
                        .findById(dto.getCveUnidadMedida())
                        .orElseThrow(() -> new RuntimeException("Unidad de Medida no encontrada"))
        );

        entity.setCveUnidadMedidaEquivalente(
                iCatUnidadMedidaRepository
                        .findById(dto.getCveUnidadMedidaEquivalente())
                        .orElseThrow(() -> new RuntimeException("Unidad de Medida Equivalente no encontrada"))
        );


        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setDescripcion(dto.getDescripcion());
        entity.setFactConversion(dto.getFactorConversion());
        entity.setBlnNpa(dto.getBlnPr());
        entity.setCodCategoriaTextil(dto.getCodigoCategoria());
        entity.setBlnActivo(dto.getBlnActivo());

        CatCategoriaTextil saved = iCatCategoriaTextilRepository.save(entity);

        return CatCategoriaTextilResponseDTO.builder()
                .codigoCategoriaTextil(saved.getCodCategoriaTextil())
                .idCategoriaTextil(saved.getId())
                .descripcion(saved.getDescripcion())
                .factorConversion(saved.getFactConversion())
                .unidadMedidaEquivalente(saved.getCveUnidadMedidaEquivalente().getCveUnidadMedida())
                .unidadMedida(saved.getCveUnidadMedida().getCveUnidadMedida())
                .fecIniVigencia(saved.getFecIniVigencia())
                .fecFinVigencia(saved.getFecFinVigencia())
                .blnActivo(saved.getBlnActivo())
                .build();
    }

    @Override
    public CatCategoriaTextilResponseDTO findById(Long id) {
        CatCategoriaTextil entity = iCatCategoriaTextilRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Registro no encontrado con id: " + id
                        )
                );

        return CatCategoriaTextilResponseDTO.builder()
                .codigoCategoriaTextil(entity.getCodCategoriaTextil())
                .idCategoriaTextil(entity.getId())
                .descripcion(entity.getDescripcion())
                .factorConversion(entity.getFactConversion())
                .unidadMedidaEquivalente(entity.getCveUnidadMedidaEquivalente().getCveUnidadMedida())
                .unidadMedida(entity.getCveUnidadMedida().getCveUnidadMedida())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }

    @Override
    public CatCategoriaTextilResponseDTO update(Long id, CatCategoriaTextilRequestDTO dto) {
        CatCategoriaTextil entity = iCatCategoriaTextilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        entity.setCveUnidadMedida(
                iCatUnidadMedidaRepository
                        .findById(dto.getCveUnidadMedida())
                        .orElseThrow(() -> new RuntimeException("Unidad de Medida no encontrada"))
        );

        entity.setCveUnidadMedidaEquivalente(
                iCatUnidadMedidaRepository
                        .findById(dto.getCveUnidadMedidaEquivalente())
                        .orElseThrow(() -> new RuntimeException("Unidad de Medida Equivalente no encontrada"))
        );

        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setDescripcion(dto.getDescripcion());
        entity.setFactConversion(dto.getFactorConversion());
        entity.setBlnNpa(dto.getBlnPr());
        entity.setBlnActivo(dto.getBlnActivo());
        entity.setCodCategoriaTextil(dto.getCodigoCategoria());

        CatCategoriaTextil updated = iCatCategoriaTextilRepository.save(entity);

        return CatCategoriaTextilResponseDTO.builder()
                .codigoCategoriaTextil(updated.getCodCategoriaTextil())
                .idCategoriaTextil(updated.getId())
                .descripcion(updated.getDescripcion())
                .factorConversion(updated.getFactConversion())
                .unidadMedidaEquivalente(updated.getCveUnidadMedidaEquivalente().getCveUnidadMedida())
                .unidadMedida(updated.getCveUnidadMedida().getCveUnidadMedida())
                .fecIniVigencia(updated.getFecIniVigencia())
                .fecFinVigencia(updated.getFecFinVigencia())
                .blnActivo(updated.getBlnActivo())
                .build();
    }

    @Override
    public List<SelectDTO> listadoUnidadMedida() {
        List<CatUnidadMedida> productos = iCatUnidadMedidaRepository.findAll();
        List<SelectDTO> resultado = new ArrayList<>();

        for (CatUnidadMedida producto : productos) {
            SelectDTO dto = new SelectDTO();
            dto.setCve(producto.getCveUnidadMedida());
            dto.setNombre(producto.getDescripcion());
            resultado.add(dto);
        }
        return resultado;
    }

}
