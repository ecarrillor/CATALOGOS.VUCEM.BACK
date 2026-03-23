package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICaTDictamenTramiteService;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.dto.DictamenTramite.CatDictamenTramiteRequestDTO;
import com.example.vucem_catalogos_service.model.dto.DictamenTramite.CatDictamenTramiteResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatDictamenTramite;
import com.example.vucem_catalogos_service.model.entity.CatDictamenTramiteId;
import com.example.vucem_catalogos_service.model.entity.CatTipoDictamen;
import com.example.vucem_catalogos_service.model.entity.CatTipoTramite;
import com.example.vucem_catalogos_service.persistence.repo.ICatDictamenTramiteRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoDictamenRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CatDictamenTramiteServiceImpl implements ICaTDictamenTramiteService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idTipoTramite", "tt.id",
            "tipoTramite", "tt.descServicio",
            "idTipoDictamen", "td.id",
            "tipoDictamen", "td.nombre"
    );

    @Autowired
    private ICatDictamenTramiteRepository iCatDictamenTramiteRepository;

    @Autowired
    private ICatTipoDictamenRepository iCatTipoDictamanRepository;

    @Autowired
    private ICatTipoTramiteRepository iCatTipoTramiteRepository;


    @Override
    public PageResponseDTO<CatDictamenTramiteResponseDTO> listarDictamenTramite(String search, String sortBy, String sortDir, Pageable pageable) {
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

        Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        Pageable sortedPageable = sort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort)
                : pageable;

        Page<CatDictamenTramiteResponseDTO> page =
                iCatDictamenTramiteRepository.search(texto, activo, sortedPageable);

        return PageResponseDTO.<CatDictamenTramiteResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatDictamenTramiteResponseDTO crearDictamenTramite(CatDictamenTramiteRequestDTO dto) {

        CatTipoTramite tramite =
                iCatTipoTramiteRepository.findById(dto.getIdTipoTramite())
                        .orElseThrow(() -> new RuntimeException("Tipo Tramite no existe"));

        CatTipoDictamen dictamen =
                iCatTipoDictamanRepository.findById(dto.getIdTipoDictamen())
                        .orElseThrow(() -> new RuntimeException("Tipo Dictamen no existe"));

        CatDictamenTramite entity = new CatDictamenTramite();

        entity.setTipoTramite(tramite);
        entity.setTipoDictamen(dictamen);

        entity.setId(new CatDictamenTramiteId());

        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatDictamenTramite saved = iCatDictamenTramiteRepository.save(entity);

        return CatDictamenTramiteResponseDTO.builder()
                .idTipoTramite(saved.getTipoTramite().getId())
                .tipoTramite(saved.getTipoTramite().getDescServicio())
                .idTipoDictamen(saved.getTipoDictamen().getId())
                .tipoDictamen(saved.getTipoDictamen().getNombre())
                .fecIniVigencia(saved.getFecIniVigencia())
                .fecFinVigencia(saved.getFecFinVigencia())
                .blnActivo(saved.getBlnActivo())
                .build();
    }

    @Override
    public CatDictamenTramiteResponseDTO findById(Long tt, Long dc) {

        CatDictamenTramiteId id = new CatDictamenTramiteId();
        id.setIdTipoTramite(tt);
        id.setIdTipoDictamen(dc);

        CatDictamenTramite entity =
                iCatDictamenTramiteRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Dictamen-Trámite no encontrado")
                        );

        return CatDictamenTramiteResponseDTO.builder()
                .idTipoTramite(entity.getTipoTramite().getId())
                .tipoTramite(entity.getTipoTramite().getDescServicio())
                .idTipoDictamen(entity.getTipoDictamen().getId())
                .tipoDictamen(entity.getTipoDictamen().getNombre())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }

    @Override
    public CatDictamenTramiteResponseDTO update(Long tt, Long dc, CatDictamenTramiteRequestDTO dto) {

        CatDictamenTramiteId id = new CatDictamenTramiteId();
        id.setIdTipoTramite(tt);
        id.setIdTipoDictamen(dc);

        CatDictamenTramite entity =
                iCatDictamenTramiteRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("No existe"));



        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());
        entity.setBlnActivo(dto.getBlnActivo());

        CatDictamenTramite updated = iCatDictamenTramiteRepository.save(entity);

        return CatDictamenTramiteResponseDTO.builder()
                .idTipoTramite(updated.getTipoTramite().getId())
                .tipoTramite(updated.getTipoTramite().getDescServicio())
                .idTipoDictamen(updated.getTipoDictamen().getId())
                .tipoDictamen(updated.getTipoDictamen().getNombre())
                .fecIniVigencia(updated.getFecIniVigencia())
                .fecFinVigencia(updated.getFecFinVigencia())
                .blnActivo(updated.getBlnActivo())
                .build();
    }

    @Override
    public List<SelectDTO> listadoTipoDictamen() {
        List<CatTipoDictamen> productos = iCatTipoDictamanRepository.findAll();
        List<SelectDTO> resultado = new ArrayList<>();

        for (CatTipoDictamen producto : productos) {
            SelectDTO dto = new SelectDTO();
            dto.setId(producto.getId());
            dto.setNombre(producto.getNombre());
            resultado.add(dto);
        }
        return resultado;
    }
}
