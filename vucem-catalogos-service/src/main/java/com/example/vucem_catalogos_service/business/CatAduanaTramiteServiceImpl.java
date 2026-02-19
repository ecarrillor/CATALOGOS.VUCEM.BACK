package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatAduanaTramiteService;
import com.example.vucem_catalogos_service.model.dto.AduanaClasifProducto.CatAduanaClasifProdResponseDTO;
import com.example.vucem_catalogos_service.model.dto.AduanaTramite.CatAduanaTramiteRequestDTO;
import com.example.vucem_catalogos_service.model.dto.AduanaTramite.CatAduanaTramiteResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatAduanaClasifProd;
import com.example.vucem_catalogos_service.model.entity.CatAduanaTtra;
import com.example.vucem_catalogos_service.model.entity.CatClasifProducto;
import com.example.vucem_catalogos_service.model.entity.CatTipoTramite;
import com.example.vucem_catalogos_service.persistence.repo.ICatAduanaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatAduanaTtraRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepository;
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
public class CatAduanaTramiteServiceImpl implements ICatAduanaTramiteService {

    @Autowired
    private ICatAduanaTtraRepository iCatAduanaTtraRepository;

    @Autowired
    private ICatAduanaRepository iCatAduanaRepository;

    @Autowired
    private ICatTipoTramiteRepository iCatTipoTramiteRepository;

    @Override
    public PageResponseDTO<CatAduanaTramiteResponseDTO> listarAduanaTramite(String search, Pageable pageable) {
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

        Page<CatAduanaTramiteResponseDTO> page =
                iCatAduanaTtraRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatAduanaTramiteResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatAduanaTramiteResponseDTO crearAduanaTramite(CatAduanaTramiteRequestDTO dto) {
        if (iCatAduanaTtraRepository.existsById(dto.getIdAduanaTramite())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }

        CatAduanaTtra entity = new CatAduanaTtra();

        entity.setId(dto.getIdAduanaTramite());

        entity.setCveAduana(
                iCatAduanaRepository
                        .findById(dto.getCveAduana())
                        .orElseThrow(() -> new RuntimeException("Aduana no encontrada"))
        );

        entity.setIdTipoTramite(
                iCatTipoTramiteRepository
                        .findById(dto.getIdTipoTramite())
                        .orElseThrow(() -> new RuntimeException("Tipo tramite no encontrado"))
        );


        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());
        entity.setAliasAduana(dto.getAliasAduana());

        CatAduanaTtra saved = iCatAduanaTtraRepository.save(entity);

        return CatAduanaTramiteResponseDTO.builder()
                .idAduanaTramite(saved.getId())
                .cveAduana(saved.getCveAduana().getCveAduana())
                .aduana(saved.getCveAduana().getNombre())
                .nombreTipoTramite(saved.getIdTipoTramite().getNombre())
                .aliasAduana(saved.getAliasAduana())
                .fecIniVigencia(saved.getFecIniVigencia())
                .fecFinVigencia(saved.getFecFinVigencia())
                .blnActivo(saved.getBlnActivo())
                .build();
    }

    @Override
    public CatAduanaTramiteResponseDTO findById(Long id) {
        CatAduanaTtra entity = iCatAduanaTtraRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Registro no encontrado con id: " + id
                        )
                );

        return CatAduanaTramiteResponseDTO.builder()
                .idAduanaTramite(entity.getId())
                .cveAduana(entity.getCveAduana().getCveAduana())
                .aduana(entity.getCveAduana().getNombre())
                .nombreTipoTramite(entity.getIdTipoTramite().getDescServicio())
                .aliasAduana(entity.getAliasAduana())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }

    @Override
    public CatAduanaTramiteResponseDTO update(Long id, CatAduanaTramiteRequestDTO dto) {
        CatAduanaTtra entity = iCatAduanaTtraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        entity.setCveAduana(
                iCatAduanaRepository
                        .findById(dto.getCveAduana())
                        .orElseThrow(() -> new RuntimeException("Aduana no encontrada"))
        );

        entity.setIdTipoTramite(
                iCatTipoTramiteRepository
                        .findById(dto.getIdTipoTramite())
                        .orElseThrow(() -> new RuntimeException("Clasificacion no encontrada"))
        );

        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatAduanaTtra updated = iCatAduanaTtraRepository.save(entity);

        return CatAduanaTramiteResponseDTO.builder()
                .idAduanaTramite(updated.getId())
                .cveAduana(updated.getCveAduana().getCveAduana())
                .aduana(updated.getCveAduana().getNombre())
                .nombreTipoTramite(updated.getIdTipoTramite().getNombre())
                .aliasAduana(updated.getAliasAduana())
                .fecIniVigencia(updated.getFecIniVigencia())
                .fecFinVigencia(updated.getFecFinVigencia())
                .blnActivo(updated.getBlnActivo())
                .build();
    }

    @Override
    public List<SelectDTO> listadoTiposTramite() {
        List<CatTipoTramite> productos = iCatTipoTramiteRepository.findAll();
        List<SelectDTO> resultado = new ArrayList<>();

        for (CatTipoTramite producto : productos) {
            SelectDTO dto = new SelectDTO();
            dto.setId(producto.getId());
            dto.setNombre(producto.getDescServicio());
            resultado.add(dto);
        }
        return resultado;
    }
}
