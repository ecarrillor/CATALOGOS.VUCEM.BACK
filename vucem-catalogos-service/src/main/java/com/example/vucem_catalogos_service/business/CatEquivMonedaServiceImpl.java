package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatEquivMonedaService;
import com.example.vucem_catalogos_service.model.dto.CatEquivMonedaDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatEquivMoneda;
import com.example.vucem_catalogos_service.model.entity.CatMoneda;
import com.example.vucem_catalogos_service.persistence.repo.ICatEquivMonedaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatMonedaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CatEquivMonedaServiceImpl implements ICatEquivMonedaService {

    @Autowired
    private ICatEquivMonedaRepository iCatEquivMonedaRepository;

    @Autowired
    private ICatMonedaRepository iCatMonedaRepository;

    @Override
    public PageResponseDTO<CatEquivMonedaDTO> list(String search, Pageable pageable) {
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

        Page<CatEquivMonedaDTO> page =
                iCatEquivMonedaRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatEquivMonedaDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatEquivMonedaDTO findById(Integer id) {
        return iCatEquivMonedaRepository.findByEquivMonedaDTO(id);
    }

    @Override
    public CatEquivMonedaDTO create(CatEquivMonedaDTO dto) {
        CatEquivMoneda entity = new CatEquivMoneda();
        entity.setId(dto.getId());
        entity.setCveMonedaOrigen(
                iCatMonedaRepository.findById(dto.getCveMonedaOrigen())
                        .orElseThrow(() -> new RuntimeException(
                                "Moneda origen no encontrada: " + dto.getCveMonedaOrigen()))
        );
        entity.setCveMonedaDestino(
                iCatMonedaRepository.findById(dto.getCveMonedaDestino())
                        .orElseThrow(() -> new RuntimeException(
                                "Moneda destino no encontrada: " + dto.getCveMonedaDestino()))
        );
        entity.setValorConversion(dto.getValorConversion());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatEquivMoneda saved = iCatEquivMonedaRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatEquivMonedaDTO update(Integer id, CatEquivMonedaDTO dto) {
        CatEquivMoneda entity = iCatEquivMonedaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "CatEquivMoneda no encontrada con id: " + id));

        if (dto.getCveMonedaOrigen() != null) {
            entity.setCveMonedaOrigen(
                    iCatMonedaRepository.findById(dto.getCveMonedaOrigen())
                            .orElseThrow(() -> new RuntimeException(
                                    "Moneda origen no encontrada: " + dto.getCveMonedaOrigen()))
            );
        }

        if (dto.getCveMonedaDestino() != null) {
            entity.setCveMonedaDestino(
                    iCatMonedaRepository.findById(dto.getCveMonedaDestino())
                            .orElseThrow(() -> new RuntimeException(
                                    "Moneda destino no encontrada: " + dto.getCveMonedaDestino()))
            );
        }

        if (dto.getValorConversion() != null) {
            entity.setValorConversion(dto.getValorConversion());
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

        CatEquivMoneda updated = iCatEquivMonedaRepository.save(entity);

        return mapToDTO(updated);
    }



    @Override
    public List<SelectDTO> buscarMonedasDest(String term) {
        List<CatMoneda> monedas = iCatMonedaRepository.buscarMonedasDest(term);
        List<SelectDTO> resultado = new ArrayList<>();

        for (CatMoneda m : monedas) {
            SelectDTO dto = new SelectDTO();
            dto.setCve(m.getCveMoneda());
            dto.setNombre(m.getNombre());
            resultado.add(dto);
        }
        return resultado;
    }



    private CatEquivMonedaDTO mapToDTO(CatEquivMoneda e) {
        return CatEquivMonedaDTO.builder()
                .id(e.getId())
                .cveMonedaOrigen(e.getCveMonedaOrigen().getCveMoneda())
                .nombreMonedaOrigen(e.getCveMonedaOrigen().getNombre())
                .cveMonedaDestino(e.getCveMonedaDestino().getCveMoneda())
                .nombreMonedaDestino(e.getCveMonedaDestino().getNombre())
                .valorConversion(e.getValorConversion())
                .fecIniVigencia(e.getFecIniVigencia())
                .fecFinVigencia(e.getFecFinVigencia())
                .blnActivo(e.getBlnActivo())
                .build();
    }
}
