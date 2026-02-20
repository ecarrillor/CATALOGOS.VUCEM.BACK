package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatFraccionALADIService;
import com.example.vucem_catalogos_service.model.dto.FraccionALADI.CatFraccionALADIRequestDTO;
import com.example.vucem_catalogos_service.model.dto.FraccionALADI.CatFraccionALADIResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatFraccionAladi;
import com.example.vucem_catalogos_service.persistence.repo.ICatFraccionAladiRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class CatFraccionALADIServiceImpl implements ICatFraccionALADIService {

    @Autowired
    private ICatFraccionAladiRepository iCatFraccionAladiRepository;

    @Override
    public PageResponseDTO<CatFraccionALADIResponseDTO> listarFraccionAladi(String search, Pageable pageable) {
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

        Page<CatFraccionALADIResponseDTO> page =
                iCatFraccionAladiRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatFraccionALADIResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatFraccionALADIResponseDTO crearFraacionAladi(CatFraccionALADIRequestDTO dto) {


            if (iCatFraccionAladiRepository.existsById(dto.getIdFraccionAladi())) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Ya existe una fracción ALADI con el id: " + dto.getIdFraccionAladi()
                );
            }

            CatFraccionAladi entity = new CatFraccionAladi();

            entity.setId(dto.getIdFraccionAladi());
            entity.setFecIniVigencia(dto.getFecIniVigencia());
            entity.setFecFinVigencia(dto.getFecFinVigencia());
            entity.setIdeTipoFraccionAladi(dto.getCveTipoFraccion());
            entity.setCveFraccion(dto.getCveFraccionAladi());
            entity.setDescripcion(dto.getDescripcion());
            entity.setBlnActivo(dto.getBlnActivo());

            CatFraccionAladi saved = iCatFraccionAladiRepository.save(entity);

        return CatFraccionALADIResponseDTO.builder()
                .idFraccionAladi(saved.getId())
                .cveTipoFraccion(saved.getIdeTipoFraccionAladi())
                .descripcion(saved.getDescripcion())
                .cveFraccionAladi(saved.getCveFraccion())
                .fecIniVigencia(saved.getFecIniVigencia())
                .fecFinVigencia(saved.getFecFinVigencia())
                .blnActivo(saved.getBlnActivo())
                .build();
    }

    @Override
    public CatFraccionALADIResponseDTO findById(Long id) {
        CatFraccionAladi entity = iCatFraccionAladiRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Registro no encontrado con id: " + id
                        )
                );

        return CatFraccionALADIResponseDTO.builder()
                .idFraccionAladi(entity.getId())
                .cveTipoFraccion(entity.getIdeTipoFraccionAladi())
                .descripcion(entity.getDescripcion())
                .cveFraccionAladi(entity.getCveFraccion())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }

    @Override
    public CatFraccionALADIResponseDTO update(Long id, CatFraccionALADIRequestDTO dto) {
        CatFraccionAladi entity = iCatFraccionAladiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));


        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setDescripcion(dto.getDescripcion());
        entity.setIdeTipoFraccionAladi(dto.getCveTipoFraccion());
        entity.setCveFraccion(dto.getCveFraccionAladi());
        entity.setBlnActivo(dto.getBlnActivo());

        CatFraccionAladi updated = iCatFraccionAladiRepository.save(entity);

        return CatFraccionALADIResponseDTO.builder()
                .idFraccionAladi(updated.getId())
                .cveTipoFraccion(updated.getIdeTipoFraccionAladi())
                .descripcion(updated.getDescripcion())
                .cveFraccionAladi(updated.getCveFraccion())
                .fecIniVigencia(updated.getFecIniVigencia())
                .fecFinVigencia(updated.getFecFinVigencia())
                .blnActivo(updated.getBlnActivo())
                .build();
    }
}
