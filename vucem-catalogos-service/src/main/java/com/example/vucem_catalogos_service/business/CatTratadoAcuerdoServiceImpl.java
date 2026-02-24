package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatTratadoAcuerdoService;
import com.example.vucem_catalogos_service.model.dto.CatTratadoAcuerdoDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatTratadoAcuerdo;
import com.example.vucem_catalogos_service.persistence.repo.ICatTratadoAcuerdoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CatTratadoAcuerdoServiceImpl implements ICatTratadoAcuerdoService {

    @Autowired
    private ICatTratadoAcuerdoRepository catTratadoAcuerdoRepository;

    @Override
    public PageResponseDTO<CatTratadoAcuerdoDTO> list(String search, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if ("activo".equalsIgnoreCase(search)) {
            activo = true;
        } else if ("inactivo".equalsIgnoreCase(search)) {
            activo = false;
        } else {
            texto = search;
        }

        Page<CatTratadoAcuerdoDTO> page = catTratadoAcuerdoRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatTratadoAcuerdoDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatTratadoAcuerdoDTO findById(Short id) {
        return catTratadoAcuerdoRepository.findByTratadoAcuerdoDTO(id)
                .orElseThrow(() -> new RuntimeException("CatTratadoAcuerdo no encontrado con id: " + id));
    }

    @Override
    public CatTratadoAcuerdoDTO create(CatTratadoAcuerdoDTO dto) {
        CatTratadoAcuerdo entity = new CatTratadoAcuerdo();
        entity.setId(dto.getId());
        entity.setIdeTipoTratadoAcuerdo(dto.getIdeTipoTratadoAcuerdo());
        entity.setCveTratadoAcuerdo(dto.getCveTratadoAcuerdo());
        entity.setNombre(dto.getNombre());
        entity.setBlnPexim(true);
        entity.setFecCaptura(LocalDate.now());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setIdeTipoCupoSaai(dto.getIdeTipoCupoSaai());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setBlnActivo(true);
        entity.setBlnEvaluarIndividual(true);

        CatTratadoAcuerdo saved = catTratadoAcuerdoRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatTratadoAcuerdoDTO update(Short id, CatTratadoAcuerdoDTO dto) {
        CatTratadoAcuerdo entity = catTratadoAcuerdoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "CatTratadoAcuerdo no encontrado con id: " + id));

        if (dto.getIdeTipoTratadoAcuerdo() != null) {
            entity.setIdeTipoTratadoAcuerdo(dto.getIdeTipoTratadoAcuerdo());
        }

        if (dto.getCveTratadoAcuerdo() != null) {
            entity.setCveTratadoAcuerdo(dto.getCveTratadoAcuerdo());
        }

        if (dto.getNombre() != null) {
            entity.setNombre(dto.getNombre());
        }

        if (dto.getBlnPexim() != null) {
            entity.setBlnPexim(dto.getBlnPexim());
        }

        if (dto.getFecCaptura() != null) {
            entity.setFecCaptura(dto.getFecCaptura());
        }

        if (dto.getFecFinVigencia() != null) {
            entity.setFecFinVigencia(dto.getFecFinVigencia());
        }

        if (dto.getIdeTipoCupoSaai() != null) {
            entity.setIdeTipoCupoSaai(dto.getIdeTipoCupoSaai());
        }

        if (dto.getFecIniVigencia() != null) {
            entity.setFecIniVigencia(dto.getFecIniVigencia());
        }

        if (dto.getBlnActivo() != null) {
            entity.setBlnActivo(dto.getBlnActivo());
        }

        if (dto.getBlnEvaluarIndividual() != null) {
            entity.setBlnEvaluarIndividual(dto.getBlnEvaluarIndividual());
        }

        CatTratadoAcuerdo saved = catTratadoAcuerdoRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public List<SelectDTO> listTratadoAcuerdo() {
        return catTratadoAcuerdoRepository.findAll().stream()
                .map(e -> {
                    SelectDTO d = new SelectDTO();
                    d.setCve(e.getCveTratadoAcuerdo());
                    d.setNombre(e.getNombre());
                    return d;
                })
                .collect(Collectors.toList());
    }

    private CatTratadoAcuerdoDTO mapToDTO(CatTratadoAcuerdo entity) {
        return CatTratadoAcuerdoDTO.builder()
                .id(entity.getId())
                .ideTipoTratadoAcuerdo(entity.getIdeTipoTratadoAcuerdo())
                .cveTratadoAcuerdo(entity.getCveTratadoAcuerdo())
                .nombre(entity.getNombre())
                .blnPexim(entity.getBlnPexim())
                .fecCaptura(entity.getFecCaptura())
                .fecFinVigencia(entity.getFecFinVigencia())
                .ideTipoCupoSaai(entity.getIdeTipoCupoSaai())
                .fecIniVigencia(entity.getFecIniVigencia())
                .blnActivo(entity.getBlnActivo())
                .blnEvaluarIndividual(entity.getBlnEvaluarIndividual())
                .build();
    }


}
