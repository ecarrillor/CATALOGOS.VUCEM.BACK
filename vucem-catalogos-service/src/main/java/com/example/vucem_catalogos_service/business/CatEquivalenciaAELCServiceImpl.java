package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatEquivalenciaAELCService;
import com.example.vucem_catalogos_service.model.dto.CatEquivalenciaAelcDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatEquivalenciaAelc;
import com.example.vucem_catalogos_service.model.entity.CatEquivalenciaAelcId;
import com.example.vucem_catalogos_service.persistence.repo.ICatEquivalenciaAelcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
public class CatEquivalenciaAELCServiceImpl implements ICatEquivalenciaAELCService {

    @Autowired
    private ICatEquivalenciaAelcRepository catEquivalenciaAelcRepository;

    @Override
    public PageResponseDTO<CatEquivalenciaAelcDTO> list(String search, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if ("activo".equalsIgnoreCase(search)) {
            activo = true;
        } else if ("inactivo".equalsIgnoreCase(search)) {
            activo = false;
        } else {
            texto = search;
        }

        Page<CatEquivalenciaAelcDTO> page = catEquivalenciaAelcRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatEquivalenciaAelcDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatEquivalenciaAelcDTO findById(Instant fecIniVigencia, String cveMoneda) {
        return catEquivalenciaAelcRepository.findByEquivalenciaAelcDTO(fecIniVigencia, cveMoneda)
                .orElseThrow(() -> new RuntimeException(
                        "CatEquivalenciaAelc no encontrada para fecIniVigencia=" + fecIniVigencia + ", cveMoneda=" + cveMoneda));
    }

    @Override
    public CatEquivalenciaAelcDTO create(CatEquivalenciaAelcDTO dto) {
        CatEquivalenciaAelcId id = new CatEquivalenciaAelcId();
        id.setFecIniVigencia(dto.getFecIniVigencia());
        id.setCveMoneda(dto.getCveMoneda());

        CatEquivalenciaAelc entity = new CatEquivalenciaAelc();
        entity.setId(id);
        entity.setValor(dto.getValor());
        entity.setFecCaptura(dto.getFecCaptura());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatEquivalenciaAelc saved = catEquivalenciaAelcRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatEquivalenciaAelcDTO update(Instant fecIniVigencia, String cveMoneda, CatEquivalenciaAelcDTO dto) {
        CatEquivalenciaAelcId pk = new CatEquivalenciaAelcId();
        pk.setFecIniVigencia(fecIniVigencia);
        pk.setCveMoneda(cveMoneda);

        CatEquivalenciaAelc entity = catEquivalenciaAelcRepository.findById(pk)
                .orElseThrow(() -> new RuntimeException(
                        "CatEquivalenciaAelc no encontrada para fecIniVigencia=" + fecIniVigencia + ", cveMoneda=" + cveMoneda));

        if (dto.getValor() != null) {
            entity.setValor(dto.getValor());
        }
        if (dto.getFecCaptura() != null) {
            entity.setFecCaptura(dto.getFecCaptura());
        }
        if (dto.getFecFinVigencia() != null) {
            entity.setFecFinVigencia(dto.getFecFinVigencia());
        }
        if (dto.getBlnActivo() != null) {
            entity.setBlnActivo(dto.getBlnActivo());
        }

        CatEquivalenciaAelc saved = catEquivalenciaAelcRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatEquivalenciaAelcDTO mapToDTO(CatEquivalenciaAelc entity) {
        return CatEquivalenciaAelcDTO.builder()
                .fecIniVigencia(entity.getId().getFecIniVigencia())
                .cveMoneda(entity.getId().getCveMoneda())
                .valor(entity.getValor())
                .fecCaptura(entity.getFecCaptura())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
