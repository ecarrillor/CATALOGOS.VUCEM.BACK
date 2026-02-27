package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.IInfAdicionalAduanaService;
import com.example.vucem_catalogos_service.model.dto.InfAdicionalAduanaDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatAduana;
import com.example.vucem_catalogos_service.model.entity.InfAdicionalAduana;
import com.example.vucem_catalogos_service.persistence.repo.ICatAduanaRepository;
import com.example.vucem_catalogos_service.persistence.repo.IInfAdicionalAduanaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class InfAdicionalAduanaServiceImpl implements IInfAdicionalAduanaService {

    @Autowired
    private IInfAdicionalAduanaRepository infAdicionalAduanaRepository;

    @Autowired
    private ICatAduanaRepository catAduanaRepository;

    @Override
    public PageResponseDTO<InfAdicionalAduanaDTO> list(String search, Pageable pageable) {
        String texto = (search != null && !search.isBlank()) ? search : null;

        Page<InfAdicionalAduanaDTO> page = infAdicionalAduanaRepository.search(texto, pageable);

        return PageResponseDTO.<InfAdicionalAduanaDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public InfAdicionalAduanaDTO findById(String cveAduana) {
        return infAdicionalAduanaRepository.findByInfAdicionalAduanaDTO(cveAduana)
                .orElseThrow(() -> new RuntimeException("InfAdicionalAduana no encontrada con cveAduana: " + cveAduana));
    }

    @Override
    public InfAdicionalAduanaDTO create(InfAdicionalAduanaDTO dto) {
        CatAduana aduana = catAduanaRepository.findById(dto.getCveAduana())
                .orElseThrow(() -> new RuntimeException(
                        "CatAduana no encontrada con cveAduana: " + dto.getCveAduana()));

        if (infAdicionalAduanaRepository.existsById(dto.getCveAduana())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }

        InfAdicionalAduana entity = new InfAdicionalAduana();
        entity.setCatAduana(aduana);
        entity.setCorreoNotificacion(dto.getCorreoNotificacion());
        entity.setBlnCuentaRni(dto.getBlnCuentaRni());
        entity.setTagAduana(dto.getTagAduana());

        InfAdicionalAduana saved = infAdicionalAduanaRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public InfAdicionalAduanaDTO update(String cveAduana, InfAdicionalAduanaDTO dto) {
        InfAdicionalAduana entity = infAdicionalAduanaRepository.findById(cveAduana)
                .orElseThrow(() -> new RuntimeException("InfAdicionalAduana no encontrada con cveAduana: " + cveAduana));

        if (dto.getCorreoNotificacion() != null) entity.setCorreoNotificacion(dto.getCorreoNotificacion());
        if (dto.getBlnCuentaRni() != null) entity.setBlnCuentaRni(dto.getBlnCuentaRni());
        if (dto.getTagAduana() != null) entity.setTagAduana(dto.getTagAduana());

        InfAdicionalAduana saved = infAdicionalAduanaRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public List<SelectDTO> listadoAduana() {
        return catAduanaRepository.findByBlnActivoTrueOrderByNombreAsc()
                .stream()
                .map(e -> {
                    SelectDTO dto = new SelectDTO();
                    dto.setCve(e.getCveAduana());
                    dto.setNombre(e.getNombre());
                    return dto;
                })
                .toList();
    }

    private InfAdicionalAduanaDTO mapToDTO(InfAdicionalAduana entity) {
        return InfAdicionalAduanaDTO.builder()
                .cveAduana(entity.getCveAduana())
                .nombreAduana(entity.getCatAduana() != null ? entity.getCatAduana().getNombre() : null)
                .correoNotificacion(entity.getCorreoNotificacion())
                .blnCuentaRni(entity.getBlnCuentaRni())
                .tagAduana(entity.getTagAduana())
                .build();
    }
}
