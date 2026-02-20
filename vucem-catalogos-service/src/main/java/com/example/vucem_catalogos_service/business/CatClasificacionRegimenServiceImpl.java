package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatClasificacionRegimenService;
import com.example.vucem_catalogos_service.model.dto.CatClasificacionRegimenDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatClasificacionRegimen;
import com.example.vucem_catalogos_service.model.entity.CatClasificacionRegimanId;
import com.example.vucem_catalogos_service.model.entity.CatRegimen;
import com.example.vucem_catalogos_service.persistence.repo.ICatClasificacionRegimenRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatRegimenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CatClasificacionRegimenServiceImpl implements ICatClasificacionRegimenService {

    @Autowired
    private ICatClasificacionRegimenRepository iCatClasificacionRegimenRepository;

    @Autowired
    private ICatRegimenRepository iCatRegimenRepository;

    @Override
    public PageResponseDTO<CatClasificacionRegimenDTO> list(String search, Pageable pageable) {
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

        Page<CatClasificacionRegimenDTO> page =
                iCatClasificacionRegimenRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatClasificacionRegimenDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatClasificacionRegimenDTO findById(String cveClasificacionRegimen, String cveRegimen) {
        return iCatClasificacionRegimenRepository
                .findByClasificacionRegimenDTO(cveClasificacionRegimen, cveRegimen);
    }

    @Override
    public CatClasificacionRegimenDTO create(CatClasificacionRegimenDTO dto) {
        CatClasificacionRegimanId id = new CatClasificacionRegimanId();
        id.setCveClasificacionRegimen(dto.getCveClasificacionRegimen());
        id.setCveRegimen(dto.getCveRegimen());

        CatClasificacionRegimen entity = new CatClasificacionRegimen();
        entity.setId(id);
        entity.setCveRegimen(
                iCatRegimenRepository.findById(dto.getCveRegimen())
                        .orElseThrow(() -> new RuntimeException("Régimen no encontrado: " + dto.getCveRegimen()))
        );
        entity.setNombre(dto.getNombre());
        entity.setCodRegimen(dto.getCodRegimen());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatClasificacionRegimen saved = iCatClasificacionRegimenRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatClasificacionRegimenDTO update(String cveClasificacionRegimen, String cveRegimen,
                                              CatClasificacionRegimenDTO dto) {
        CatClasificacionRegimanId id = new CatClasificacionRegimanId();
        id.setCveClasificacionRegimen(cveClasificacionRegimen);
        id.setCveRegimen(cveRegimen);

        CatClasificacionRegimen entity = iCatClasificacionRegimenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "CatClasificacionRegimen no encontrado: "
                                + cveClasificacionRegimen + " - " + cveRegimen));

        if (dto.getNombre() != null) {
            entity.setNombre(dto.getNombre());
        }

        if (dto.getCodRegimen() != null) {
            entity.setCodRegimen(dto.getCodRegimen());
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

        CatClasificacionRegimen updated = iCatClasificacionRegimenRepository.save(entity);

        return mapToDTO(updated);
    }

    @Override
    public List<SelectDTO> listadoRegimenes() {
        List<CatRegimen> regimenes = iCatRegimenRepository.findByBlnActivoTrue();
        List<SelectDTO> resultado = new ArrayList<>();

        for (CatRegimen r : regimenes) {
            SelectDTO dto = new SelectDTO();
            dto.setCve(r.getCveRegimen());
            dto.setNombre(r.getNombre());
            resultado.add(dto);
        }
        return resultado;
    }

    private CatClasificacionRegimenDTO mapToDTO(CatClasificacionRegimen e) {
        return CatClasificacionRegimenDTO.builder()
                .cveClasificacionRegimen(e.getId().getCveClasificacionRegimen())
                .cveRegimen(e.getId().getCveRegimen())
                .nombreRegimen(e.getCveRegimen().getNombre())
                .nombre(e.getNombre())
                .codRegimen(e.getCodRegimen())
                .fecIniVigencia(e.getFecIniVigencia())
                .fecFinVigencia(e.getFecFinVigencia())
                .blnActivo(e.getBlnActivo())
                .build();
    }
}
