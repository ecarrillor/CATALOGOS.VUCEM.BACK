package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatLeyendaTextoService;
import com.example.vucem_catalogos_service.model.dto.LeyendaTexto.CatLeyendaTextoRequestDTO;
import com.example.vucem_catalogos_service.model.dto.LeyendaTexto.CatLeyendaTextoResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatLeyendaTexto;
import com.example.vucem_catalogos_service.persistence.repo.ICatLeyendaTextoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class CatLeyendaTextoServiceImpl implements ICatLeyendaTextoService {

    @Autowired
    private ICatLeyendaTextoRepository iCatLeyendaTextoRepository;

    @Override
    public PageResponseDTO<CatLeyendaTextoResponseDTO> listarLeyendaTexto(String search, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if (search != null && !search.isBlank()) {

            String s = search.trim().toLowerCase();

            if (s.equals("activo")) {
                activo = true;
            }
            else if (s.equals("inactivo")) {
                activo = false;
            }
            else {
                texto = "%" + search.trim().toLowerCase() + "%";
            }
        }

        Page<CatLeyendaTextoResponseDTO> page =
                iCatLeyendaTextoRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatLeyendaTextoResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatLeyendaTextoResponseDTO crearLeyendaTexto(CatLeyendaTextoRequestDTO dto) {
        if (iCatLeyendaTextoRepository.existsById(dto.getCveLeyenda())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }

        CatLeyendaTexto entity = new CatLeyendaTexto();
        entity.setId(dto.getCveLeyenda());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setLeyenda(dto.getLeyenda());
        entity.setNumAnio(dto.getNumAnio());
        entity.setIdeTipoLeyendaTexto(dto.getTipoLeyenda());
        entity.setBlnActivo(dto.getBlnActivo());

        CatLeyendaTexto saved = iCatLeyendaTextoRepository.save(entity);

        return CatLeyendaTextoResponseDTO.builder()
                .cveLeyenda(saved.getId())
                .leyenda(saved.getLeyenda())
                .tipoLeyenda(saved.getIdeTipoLeyendaTexto())
                .numAnio(saved.getNumAnio())
                .fecIniVigencia(saved.getFecIniVigencia())
                .fecFinVigencia(saved.getFecFinVigencia())
                .blnActivo(saved.getBlnActivo())
                .build();
    }

    @Override
    public CatLeyendaTextoResponseDTO findById(Long id) {
        CatLeyendaTexto entity = iCatLeyendaTextoRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Registro no encontrado con id: " + id
                        )
                );

        return CatLeyendaTextoResponseDTO.builder()
                .cveLeyenda(entity.getId())
                .leyenda(entity.getLeyenda())
                .tipoLeyenda(entity.getIdeTipoLeyendaTexto())
                .numAnio(entity.getNumAnio())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }

    @Override
    public CatLeyendaTextoResponseDTO update(Long id, CatLeyendaTextoRequestDTO dto) {
        CatLeyendaTexto entity = iCatLeyendaTextoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));


        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setLeyenda(dto.getLeyenda());
        entity.setNumAnio(dto.getNumAnio());
        entity.setIdeTipoLeyendaTexto(dto.getTipoLeyenda());
        entity.setBlnActivo(dto.getBlnActivo());

        CatLeyendaTexto updated = iCatLeyendaTextoRepository.save(entity);

        return CatLeyendaTextoResponseDTO.builder()
                .cveLeyenda(updated.getId())
                .leyenda(updated.getLeyenda())
                .tipoLeyenda(updated.getIdeTipoLeyendaTexto())
                .numAnio(updated.getNumAnio())
                .fecIniVigencia(updated.getFecIniVigencia())
                .fecFinVigencia(updated.getFecFinVigencia())
                .blnActivo(updated.getBlnActivo())
                .build();
    }
}
