package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatCa;
import com.example.vucem_catalogos_service.business.Interface.ICatCasService;
import com.example.vucem_catalogos_service.model.dto.CatCas.CatCaRequestDTO;
import com.example.vucem_catalogos_service.model.dto.CatCas.CatCaResponseDTO;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.persistence.repo.ICatCasRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class CatCasServiceImpl implements ICatCasService {

    @Autowired
    private ICatCasRepository repository;

    @Override
    public PageResponseDTO<CatCaResponseDTO> listAll(String search, Pageable pageable) {
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

        Page<CatCaResponseDTO> page = repository.search(texto, activo, pageable);

        return PageResponseDTO.<CatCaResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatCaResponseDTO crear(CatCaRequestDTO dto) {

        if (repository.existsById(dto.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }

        CatCa entity = new CatCa();
        entity.setId(dto.getId());
        entity.setDescCas(dto.getDescCas());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlActivo(true);

        return toResponseDTO(repository.save(entity));
    }

    @Override
    public CatCaResponseDTO findById(Short id) {
        CatCa entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Registro no encontrado con id: " + id
                ));
        return toResponseDTO(entity);
    }

    @Override
    public CatCaResponseDTO update(Short id, CatCaRequestDTO dto) {

        CatCa entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Registro no encontrado con id: " + id
                ));

        if (dto.getDescCas() != null) {
            entity.setDescCas(dto.getDescCas());
        }
        if (dto.getFecIniVigencia() != null) {
            entity.setFecIniVigencia(dto.getFecIniVigencia());
        }
        if (dto.getFecFinVigencia() != null) {
            entity.setFecFinVigencia(dto.getFecFinVigencia());
        }
        if (dto.getBlActivo() != null) {
            entity.setBlActivo(dto.getBlActivo());
        }

        return toResponseDTO(repository.save(entity));
    }

    @Override
    public List<ClasifProductoTraDTO> listadoTipoTramite() {
        return repository.listadoTipoTramite();
    }

    private CatCaResponseDTO toResponseDTO(CatCa entity) {
        return CatCaResponseDTO.builder()
                .id(entity.getId())
                .descCas(entity.getDescCas())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blActivo(entity.getBlActivo())
                .build();
    }
}
