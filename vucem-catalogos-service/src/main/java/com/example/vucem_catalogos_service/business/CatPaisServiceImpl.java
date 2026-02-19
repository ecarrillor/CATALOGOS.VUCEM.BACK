package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatPaisService;
import com.example.vucem_catalogos_service.model.dto.CatPaisDTO;
import com.example.vucem_catalogos_service.model.dto.CatPaisSaveDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatMoneda;
import com.example.vucem_catalogos_service.model.entity.CatPais;
import com.example.vucem_catalogos_service.persistence.repo.ICatMonedaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatPaisRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CatPaisServiceImpl implements ICatPaisService {

    @Autowired
    private ICatPaisRepository catPaisRepository;

    @Autowired
    private ICatMonedaRepository catMonedaRepository;

    @Override
    public PageResponseDTO<CatPaisDTO> list(String search, Pageable pageable) {
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

        Page<CatPaisDTO> page = catPaisRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatPaisDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatPaisSaveDTO findById(String cvePais) {
        return catPaisRepository.findByPaisDTO(cvePais);
    }

    @Override
    public CatPaisSaveDTO create(CatPaisSaveDTO dto) {
        CatPais entity = new CatPais();
        entity.setCvePais(dto.getCvePais());
        entity.setNombre(dto.getNombre());
        entity.setCvePaisWco(dto.getCvePaisWco());
        entity.setFecCaptura(LocalDate.now());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(true);
        entity.setBlnRestriccion(true);
        entity.setNombreAlterno(dto.getNombre());

        if (dto.getCveMoneda() != null) {
            CatMoneda moneda = catMonedaRepository.findById(dto.getCveMoneda())
                    .orElseThrow(() -> new RuntimeException("Moneda no encontrada: " + dto.getCveMoneda()));
            entity.setCveMoneda(moneda);
        }

        CatPais saved = catPaisRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatPaisSaveDTO update(String cvePais, CatPaisSaveDTO dto) {
        CatPais entity = catPaisRepository.findById(cvePais)
                .orElseThrow(() -> new RuntimeException("País no encontrado: " + cvePais));

        if (dto.getNombre() != null) {
            entity.setNombre(dto.getNombre());
        }

        if (dto.getCvePaisWco() != null) {
            entity.setCvePaisWco(dto.getCvePaisWco());
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

        if (dto.getCveMoneda() != null) {
            CatMoneda moneda = catMonedaRepository.findById(dto.getCveMoneda())
                    .orElseThrow(() -> new RuntimeException("Moneda no encontrada: " + dto.getCveMoneda()));

            entity.setCveMoneda(moneda);
        }

        CatPais updated = catPaisRepository.save(entity);

        return mapToDTO(updated);
    }



    private CatPaisSaveDTO mapToDTO(CatPais entity) {
        return CatPaisSaveDTO.builder()
                .cvePais(entity.getCvePais())
                .nombre(entity.getNombre())
                .cveMoneda(entity.getCveMoneda() != null ? entity.getCveMoneda().getCveMoneda() : null)
                .cvePaisWco(entity.getCvePaisWco())
                .fecFinVigencia(entity.getFecFinVigencia())
                .fecIniVigencia(entity.getFecIniVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }


    @Override
    public List<SelectDTO> listMoneda() {
        return catMonedaRepository.findAll().stream()
                .map(e -> {
                    SelectDTO d = new SelectDTO();
                    d.setCve(e.getCveMoneda());
                    d.setNombre(e.getNombre());
                    return d;
                })
                .collect(Collectors.toList());
    }


}
