package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatParametroService;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.Parametro.CatParametroRequestDTO;
import com.example.vucem_catalogos_service.model.dto.Parametro.CatParametroResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatParametro;
import com.example.vucem_catalogos_service.persistence.repo.ICatDependenciaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatParametroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class CatParametroServiceImpl implements ICatParametroService {

    @Autowired
    private ICatParametroRepository iCatParametroRepository;

    @Autowired
    private ICatDependenciaRepository iCatDependenciaRepository;

    @Override
    public PageResponseDTO<CatParametroResponseDTO> listarParametro(String search, Pageable pageable) {
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

        Page<CatParametroResponseDTO> page =
                iCatParametroRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatParametroResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatParametroResponseDTO crearParametro(CatParametroRequestDTO dto) {
        if (iCatParametroRepository.existsById(dto.getCveParametro())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }

        CatParametro entity = new CatParametro();

        entity.setIdDependencia(
                iCatDependenciaRepository
                        .findById(dto.getIdDependencia())
                        .orElseThrow(() -> new RuntimeException("Dependencia no encontrada"))
        );


        entity.setCveParametro(dto.getCveParametro());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setDescripcion(dto.getDescripcion());
        entity.setValor(dto.getValor());
        entity.setBlnActivo(dto.getBlnActivo());

        CatParametro saved = iCatParametroRepository.save(entity);

        return CatParametroResponseDTO.builder()
                .cveParametro(saved.getCveParametro())
                .descripcion(saved.getDescripcion())
                .valor(saved.getValor())
                .idDependencia(saved.getIdDependencia().getId())
                .dependencia(saved.getIdDependencia().getNombre())
                .fecIniVigencia(saved.getFecIniVigencia())
                .fecFinVigencia(saved.getFecFinVigencia())
                .blnActivo(saved.getBlnActivo())
                .build();
    }

    @Override
    public CatParametroResponseDTO findById(String id) {
        CatParametro entity = iCatParametroRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Registro no encontrado con id: " + id
                        )
                );

        return CatParametroResponseDTO.builder()
                .cveParametro(entity.getCveParametro())
                .descripcion(entity.getDescripcion())
                .valor(entity.getValor())
                .idDependencia(entity.getIdDependencia().getId())
                .dependencia(entity.getIdDependencia().getNombre())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }

    @Override
    public CatParametroResponseDTO update(String id, CatParametroRequestDTO dto) {
        CatParametro entity = iCatParametroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        entity.setIdDependencia(
                iCatDependenciaRepository
                        .findById(dto.getIdDependencia())
                        .orElseThrow(() -> new RuntimeException("Dependencia no encontrada"))
        );

        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatParametro updated = iCatParametroRepository.save(entity);

        return CatParametroResponseDTO.builder()
                .cveParametro(updated.getCveParametro())
                .descripcion(updated.getDescripcion())
                .valor(updated.getValor())
                .idDependencia(updated.getIdDependencia().getId())
                .dependencia(updated.getIdDependencia().getNombre())
                .fecIniVigencia(updated.getFecIniVigencia())
                .fecFinVigencia(updated.getFecFinVigencia())
                .blnActivo(updated.getBlnActivo())
                .build();
    }
}
