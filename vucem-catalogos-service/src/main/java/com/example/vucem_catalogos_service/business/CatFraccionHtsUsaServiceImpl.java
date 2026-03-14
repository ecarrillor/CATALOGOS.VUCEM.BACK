package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatFraccionHtsUsaService;
import com.example.vucem_catalogos_service.model.dto.CatFraccionHtsUsaDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatFraccionHtsUsa;
import com.example.vucem_catalogos_service.model.entity.CatUnidadMedida;
import com.example.vucem_catalogos_service.persistence.repo.ICatFraccionHtsUsaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatUnidadMedidaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CatFraccionHtsUsaServiceImpl implements ICatFraccionHtsUsaService {

    @Autowired
    private ICatFraccionHtsUsaRepository iCatFraccionHtsUsaRepository;

    @Autowired
    private ICatUnidadMedidaRepository iCatUnidadMedidaRepository;

    @Override
    public PageResponseDTO<CatFraccionHtsUsaDTO> list(String search, Pageable pageable) {
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

        Page<CatFraccionHtsUsaDTO> page =
                iCatFraccionHtsUsaRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatFraccionHtsUsaDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatFraccionHtsUsaDTO findById(Long id) {
        return iCatFraccionHtsUsaRepository.findByFraccionHtsUsaDTO(id);
    }

    @Override
    public CatFraccionHtsUsaDTO create(CatFraccionHtsUsaDTO dto) {
        CatFraccionHtsUsa entity = new CatFraccionHtsUsa();
        entity.setId(dto.getId());
        entity.setCveFraccionHtsUsa(dto.getCveFraccionHtsUsa());
        entity.setDescripcion(dto.getDescripcion());
        entity.setFecCaptura(dto.getFecCaptura());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setIdeTipoBienFraccion(dto.getIdeTipoBienFraccion());
        entity.setBlnExenta(true);
        entity.setBlnActivo(dto.getBlnActivo());

        if (dto.getCveUnidadMedida() != null) {
            entity.setCveUnidadMedida(
                    iCatUnidadMedidaRepository.findById(dto.getCveUnidadMedida())
                            .orElseThrow(() -> new RuntimeException(
                                    "Unidad de medida no encontrada: " + dto.getCveUnidadMedida()))
            );
        }

        CatFraccionHtsUsa saved = iCatFraccionHtsUsaRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatFraccionHtsUsaDTO update(Long id, CatFraccionHtsUsaDTO dto) {
        CatFraccionHtsUsa entity = iCatFraccionHtsUsaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "CatFraccionHtsUsa no encontrada con id: " + id));

        if (dto.getCveFraccionHtsUsa() != null) {
            entity.setCveFraccionHtsUsa(dto.getCveFraccionHtsUsa());
        }

        if (dto.getDescripcion() != null) {
            entity.setDescripcion(dto.getDescripcion());
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

        if (dto.getIdeTipoBienFraccion() != null) {
            entity.setIdeTipoBienFraccion(dto.getIdeTipoBienFraccion());
        }

        if (dto.getBlnExenta() != null) {
            entity.setBlnExenta(true);
        }

        if (dto.getBlnActivo() != null) {
            entity.setBlnActivo(dto.getBlnActivo());
        }

        if (dto.getCveUnidadMedida() != null) {
            entity.setCveUnidadMedida(
                    iCatUnidadMedidaRepository.findById(dto.getCveUnidadMedida())
                            .orElseThrow(() -> new RuntimeException(
                                    "Unidad de medida no encontrada: " + dto.getCveUnidadMedida()))
            );
        }

        CatFraccionHtsUsa updated = iCatFraccionHtsUsaRepository.save(entity);

        return mapToDTO(updated);
    }

    @Override
    public List<SelectDTO> listadoUnidadesMedida() {
        List<CatUnidadMedida> unidades = iCatUnidadMedidaRepository.findByBlnActivoTrue();
        List<SelectDTO> resultado = new ArrayList<>();

        for (CatUnidadMedida u : unidades) {
            SelectDTO dto = new SelectDTO();
            dto.setCve(u.getCveUnidadMedida());
            dto.setNombre(u.getDescripcion());
            resultado.add(dto);
        }
        return resultado;
    }

    private CatFraccionHtsUsaDTO mapToDTO(CatFraccionHtsUsa e) {
        return CatFraccionHtsUsaDTO.builder()
                .id(e.getId())
                .cveFraccionHtsUsa(e.getCveFraccionHtsUsa())
                .descripcion(e.getDescripcion())
                .fecCaptura(e.getFecCaptura())
                .fecIniVigencia(e.getFecIniVigencia())
                .fecFinVigencia(e.getFecFinVigencia())
                .ideTipoBienFraccion(e.getIdeTipoBienFraccion())
                .blnExenta(true)
                .blnActivo(e.getBlnActivo())
                .cveUnidadMedida(e.getCveUnidadMedida() != null
                        ? e.getCveUnidadMedida().getCveUnidadMedida() : null)
                .nombreUnidadMedida(e.getCveUnidadMedida() != null
                        ? e.getCveUnidadMedida().getDescripcion() : null)
                .build();
    }
}
