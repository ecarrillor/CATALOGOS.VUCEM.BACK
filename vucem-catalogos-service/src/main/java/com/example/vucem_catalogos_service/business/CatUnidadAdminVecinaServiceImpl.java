package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatUnidadAdminVecinaService;
import com.example.vucem_catalogos_service.model.dto.CatUnidadAdminVecinaDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatEntidad;
import com.example.vucem_catalogos_service.model.entity.CatUnidadAdminVecina;
import com.example.vucem_catalogos_service.model.entity.CatUnidadAdminVecinaId;
import com.example.vucem_catalogos_service.model.entity.CatUnidadAdministrativa;
import com.example.vucem_catalogos_service.persistence.repo.ICatEntidadRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatUnidadAdminVecinaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatUnidadAdministrativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CatUnidadAdminVecinaServiceImpl implements ICatUnidadAdminVecinaService {

    @Autowired
    private ICatUnidadAdminVecinaRepository catUnidadAdminVecinaRepository;

    @Autowired
    private ICatUnidadAdministrativaRepository catUnidadAdministrativaRepository;

    @Autowired
    private ICatEntidadRepository catEntidadRepository;

    @Override
    public PageResponseDTO<CatUnidadAdminVecinaDTO> list(String search, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if ("activo".equalsIgnoreCase(search)) {
            activo = true;
        } else if ("inactivo".equalsIgnoreCase(search)) {
            activo = false;
        } else {
            texto = search;
        }

        Page<CatUnidadAdminVecinaDTO> page = catUnidadAdminVecinaRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatUnidadAdminVecinaDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatUnidadAdminVecinaDTO findById(String cveUnidadAdministrativa, String cveEntidad) {
        return catUnidadAdminVecinaRepository
                .findByUnidadAdminVecinaDTO(cveUnidadAdministrativa, cveEntidad)
                .orElseThrow(() -> new RuntimeException(
                        "CatUnidadAdminVecina no encontrada con cveUnidadAdministrativa: "
                                + cveUnidadAdministrativa + " y cveEntidad: " + cveEntidad));
    }

    @Override
    public CatUnidadAdminVecinaDTO create(CatUnidadAdminVecinaDTO dto) {
        CatUnidadAdministrativa unidadAdmin = catUnidadAdministrativaRepository
                .findById(dto.getCveUnidadAdministrativa())
                .orElseThrow(() -> new RuntimeException(
                        "CatUnidadAdministrativa no encontrada: " + dto.getCveUnidadAdministrativa()));

        CatEntidad entidad = catEntidadRepository.findById(dto.getCveEntidad())
                .orElseThrow(() -> new RuntimeException(
                        "CatEntidad no encontrada: " + dto.getCveEntidad()));

        CatUnidadAdminVecinaId id = new CatUnidadAdminVecinaId();
        id.setCveUnidadAdministrativa(dto.getCveUnidadAdministrativa());
        id.setCveEntidad(dto.getCveEntidad());

        CatUnidadAdminVecina entity = new CatUnidadAdminVecina();
        entity.setId(id);
        entity.setCveUnidadAdministrativa(unidadAdmin);
        entity.setCveEntidad(entidad);
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatUnidadAdminVecina saved = catUnidadAdminVecinaRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatUnidadAdminVecinaDTO update(String cveUnidadAdministrativa, String cveEntidad,
                                          CatUnidadAdminVecinaDTO dto) {
        CatUnidadAdminVecinaId id = new CatUnidadAdminVecinaId();
        id.setCveUnidadAdministrativa(cveUnidadAdministrativa);
        id.setCveEntidad(cveEntidad);

        CatUnidadAdminVecina entity = catUnidadAdminVecinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "CatUnidadAdminVecina no encontrada con cveUnidadAdministrativa: "
                                + cveUnidadAdministrativa + " y cveEntidad: " + cveEntidad));

        if (dto.getFecIniVigencia() != null) entity.setFecIniVigencia(dto.getFecIniVigencia());
        if (dto.getFecFinVigencia() != null) entity.setFecFinVigencia(dto.getFecFinVigencia());
        if (dto.getBlnActivo() != null) entity.setBlnActivo(dto.getBlnActivo());

        CatUnidadAdminVecina saved = catUnidadAdminVecinaRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatUnidadAdminVecinaDTO mapToDTO(CatUnidadAdminVecina entity) {
        return CatUnidadAdminVecinaDTO.builder()
                .cveUnidadAdministrativa(entity.getId().getCveUnidadAdministrativa())
                .cveEntidad(entity.getId().getCveEntidad())
                .nombreUnidadAdministrativa(entity.getCveUnidadAdministrativa() != null
                        ? entity.getCveUnidadAdministrativa().getNombre() : null)
                .nombreEntidad(entity.getCveEntidad() != null
                        ? entity.getCveEntidad().getNombre() : null)
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
