package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatNormalOficialService;
import com.example.vucem_catalogos_service.model.dto.CatNormalOficialDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatNormalOficial;
import com.example.vucem_catalogos_service.persistence.repo.ICatNormalOficialRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatPaisRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CatNormalOficialServiceImpl implements ICatNormalOficialService {

    @Autowired
    private ICatNormalOficialRepository catNormalOficialRepository;

    @Autowired
    private ICatPaisRepository catPaisRepository;

    @Override
    public PageResponseDTO<CatNormalOficialDTO> list(String search, Pageable pageable) {
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

        Page<CatNormalOficialDTO> page = catNormalOficialRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatNormalOficialDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatNormalOficialDTO findById(Integer id) {
        return catNormalOficialRepository.findByNormalOficialDTO(id);
    }

    @Override
    public CatNormalOficialDTO create(CatNormalOficialDTO dto) {
        CatNormalOficial entity = new CatNormalOficial();

        entity.setId(dto.getId());
        entity.setClaveNorma(dto.getClaveNorma());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo() != null ? dto.getBlnActivo() : true);
        entity.setDescNorma(dto.getDescNorma());
        entity.setFecPublicacion(dto.getFecPublicacion());
        entity.setFecEntradaVigor(dto.getFecEntradaVigor());
        entity.setIdeClasifNorma(dto.getIdeClasifNorma());
        entity.setBlnLoteEstructurado(dto.getBlnLoteEstructurado());

        if (dto.getCvePais() != null) {
            entity.setCvePais(
                    catPaisRepository.findById(dto.getCvePais())
                            .orElseThrow(() -> new RuntimeException("País no encontrado: " + dto.getCvePais()))
            );
        }

        if (dto.getIdNormaOficialR() != null) {
            entity.setIdNormaOficialR(
                    catNormalOficialRepository.findById(dto.getIdNormaOficialR())
                            .orElseThrow(() -> new RuntimeException("Norma oficial referenciada no encontrada: " + dto.getIdNormaOficialR()))
            );
        }

        CatNormalOficial saved = catNormalOficialRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatNormalOficialDTO update(Integer id, CatNormalOficialDTO dto) {
        CatNormalOficial entity = catNormalOficialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Norma oficial no encontrada: " + id));

        if (dto.getClaveNorma() != null) {
            entity.setClaveNorma(dto.getClaveNorma());
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

        if (dto.getDescNorma() != null) {
            entity.setDescNorma(dto.getDescNorma());
        }

        if (dto.getFecPublicacion() != null) {
            entity.setFecPublicacion(dto.getFecPublicacion());
        }

        if (dto.getFecEntradaVigor() != null) {
            entity.setFecEntradaVigor(dto.getFecEntradaVigor());
        }

        if (dto.getIdeClasifNorma() != null) {
            entity.setIdeClasifNorma(dto.getIdeClasifNorma());
        }

        if (dto.getBlnLoteEstructurado() != null) {
            entity.setBlnLoteEstructurado(dto.getBlnLoteEstructurado());
        }

        if (dto.getCvePais() != null) {
            entity.setCvePais(
                    catPaisRepository.findById(dto.getCvePais())
                            .orElseThrow(() -> new RuntimeException(
                                    "País no encontrado: " + dto.getCvePais()))
            );
        }

        if (dto.getIdNormaOficialR() != null) {
            entity.setIdNormaOficialR(
                    catNormalOficialRepository.findById(dto.getIdNormaOficialR())
                            .orElseThrow(() -> new RuntimeException(
                                    "Norma oficial referenciada no encontrada: "
                                            + dto.getIdNormaOficialR()))
            );
        }

        CatNormalOficial updated = catNormalOficialRepository.save(entity);

        return mapToDTO(updated);
    }

    @Override
    public List<CatNormalOficialDTO> listActivos() {
        return catNormalOficialRepository.findAllActivos();
    }

    private CatNormalOficialDTO mapToDTO(CatNormalOficial entity) {
        return CatNormalOficialDTO.builder()
                .id(entity.getId())
                .claveNorma(entity.getClaveNorma())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .descNorma(entity.getDescNorma())
                .fecPublicacion(entity.getFecPublicacion())
                .fecEntradaVigor(entity.getFecEntradaVigor())
                .ideClasifNorma(entity.getIdeClasifNorma())
                .cvePais(entity.getCvePais() != null ? entity.getCvePais().getCvePais() : null)
                .nombrePais(entity.getCvePais() != null ? entity.getCvePais().getNombre() : null)
                .idNormaOficialR(entity.getIdNormaOficialR() != null ? entity.getIdNormaOficialR().getId() : null)
                .blnLoteEstructurado(entity.getBlnLoteEstructurado())
                .build();
    }
}
