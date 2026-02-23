package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatActividadEconomicaSatService;
import com.example.vucem_catalogos_service.model.dto.CatActividadEconomicaSatDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatActividadEconomicaSat;
import com.example.vucem_catalogos_service.persistence.repo.ICatActividadEconomicaSatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CatActividadEconomicaSatServiceImpl implements ICatActividadEconomicaSatService {

    @Autowired
    private ICatActividadEconomicaSatRepository catActividadEconomicaSatRepository;

    @Override
    public PageResponseDTO<CatActividadEconomicaSatDTO> list(String search, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if ("activo".equalsIgnoreCase(search)) {
            activo = true;
        } else if ("inactivo".equalsIgnoreCase(search)) {
            activo = false;
        } else {
            texto = search;
        }

        Page<CatActividadEconomicaSatDTO> page = catActividadEconomicaSatRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatActividadEconomicaSatDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatActividadEconomicaSatDTO findById(Long id) {
        return catActividadEconomicaSatRepository.findByActividadEconomicaSatDTO(id)
                .orElseThrow(() -> new RuntimeException("CatActividadEconomicaSat no encontrada con id: " + id));
    }

    @Override
    public CatActividadEconomicaSatDTO create(CatActividadEconomicaSatDTO dto) {
        CatActividadEconomicaSat entity = new CatActividadEconomicaSat();
        entity.setDescripcion(dto.getDescripcion());
        entity.setDescScian(dto.getDescScian());
        entity.setDescNotas(dto.getDescNotas());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setFecCaptura(dto.getFecCaptura());
        entity.setFecActualizacion(dto.getFecActualizacion());
        entity.setCveTipoIndustriaIdc(dto.getCveTipoIndustriaIdc());
        entity.setBlnActivo(dto.getBlnActivo());

        if (dto.getIdActividadEconomicaR() != null) {
            entity.setIdActividadEconomicaR(
                    catActividadEconomicaSatRepository.findById(dto.getIdActividadEconomicaR())
                            .orElseThrow(() -> new RuntimeException(
                                    "CatActividadEconomicaSat padre no encontrada con id: " + dto.getIdActividadEconomicaR()))
            );
        }

        CatActividadEconomicaSat saved = catActividadEconomicaSatRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatActividadEconomicaSatDTO update(Long id, CatActividadEconomicaSatDTO dto) {
        CatActividadEconomicaSat entity = catActividadEconomicaSatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatActividadEconomicaSat no encontrada con id: " + id));

        if (dto.getDescripcion() != null) {
            entity.setDescripcion(dto.getDescripcion());
        }
        if (dto.getDescScian() != null) {
            entity.setDescScian(dto.getDescScian());
        }
        if (dto.getDescNotas() != null) {
            entity.setDescNotas(dto.getDescNotas());
        }
        if (dto.getFecIniVigencia() != null) {
            entity.setFecIniVigencia(dto.getFecIniVigencia());
        }
        if (dto.getFecFinVigencia() != null) {
            entity.setFecFinVigencia(dto.getFecFinVigencia());
        }
        if (dto.getFecCaptura() != null) {
            entity.setFecCaptura(dto.getFecCaptura());
        }
        if (dto.getFecActualizacion() != null) {
            entity.setFecActualizacion(dto.getFecActualizacion());
        }
        if (dto.getCveTipoIndustriaIdc() != null) {
            entity.setCveTipoIndustriaIdc(dto.getCveTipoIndustriaIdc());
        }
        if (dto.getBlnActivo() != null) {
            entity.setBlnActivo(dto.getBlnActivo());
        }
        if (dto.getIdActividadEconomicaR() != null) {
            entity.setIdActividadEconomicaR(
                    catActividadEconomicaSatRepository.findById(dto.getIdActividadEconomicaR())
                            .orElseThrow(() -> new RuntimeException(
                                    "CatActividadEconomicaSat padre no encontrada con id: " + dto.getIdActividadEconomicaR()))
            );
        }

        CatActividadEconomicaSat saved = catActividadEconomicaSatRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatActividadEconomicaSatDTO mapToDTO(CatActividadEconomicaSat entity) {
        return CatActividadEconomicaSatDTO.builder()
                .id(entity.getId())
                .idActividadEconomicaR(entity.getIdActividadEconomicaR() != null
                        ? entity.getIdActividadEconomicaR().getId() : null)
                .descripcionPadre(entity.getIdActividadEconomicaR() != null
                        ? entity.getIdActividadEconomicaR().getDescripcion() : null)
                .descripcion(entity.getDescripcion())
                .descScian(entity.getDescScian())
                .descNotas(entity.getDescNotas())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .fecCaptura(entity.getFecCaptura())
                .fecActualizacion(entity.getFecActualizacion())
                .cveTipoIndustriaIdc(entity.getCveTipoIndustriaIdc())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
