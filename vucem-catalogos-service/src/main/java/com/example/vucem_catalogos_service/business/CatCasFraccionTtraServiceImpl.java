package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatCa;
import com.example.vucem_catalogos_service.model.entity.CatCasFraccionTtra;
import com.example.vucem_catalogos_service.business.Interface.ICatCasFraccionTtraService;
import com.example.vucem_catalogos_service.model.dto.CasFraccionTtra.CatCasFraccionTtraRequestDTO;
import com.example.vucem_catalogos_service.model.dto.CasFraccionTtra.CatCasFraccionTtraResponseDTO;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.persistence.repo.ICatCasRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatCasFraccionTtraRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatFraccionArancelariaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CatCasFraccionTtraServiceImpl implements ICatCasFraccionTtraService {

    @Autowired
    private ICatCasFraccionTtraRepository repository;

    @Autowired
    private ICatCasRepository casRepository;

    @Autowired
    private ICatFraccionArancelariaRepository fraccionArancelariaRepository;

    @Autowired
    private ICatTipoTramiteRepository tipoTramiteRepository;

    @Override
    public PageResponseDTO<CatCasFraccionTtraResponseDTO> listAll(String search, Long idTipoTramite, Pageable pageable) {
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

        Page<CatCasFraccionTtraResponseDTO> page =
                repository.search(texto, activo, idTipoTramite, pageable);

        return PageResponseDTO.<CatCasFraccionTtraResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatCasFraccionTtraResponseDTO crear(CatCasFraccionTtraRequestDTO dto) {

        if (repository.existsById(dto.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }

        CatCasFraccionTtra entity = new CatCasFraccionTtra();
        entity.setId(dto.getId());

        entity.setIdCas(
                casRepository.findById(dto.getIdCas())
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "CAS no encontrado"))
        );

        entity.setCveFraccion(
                fraccionArancelariaRepository.findById(dto.getCveFraccion())
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Fracción arancelaria no encontrada"))
        );

        entity.setIdTipoTramite(
                tipoTramiteRepository.findById(dto.getIdTipoTramite())
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Tipo de trámite no encontrado"))
        );

        entity.setBlnRotterdam(dto.getBlnRotterdam());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlActivo(true);
        entity.setDescFraccionAlt(dto.getDescFraccionAlt());
        entity.setCvnWasser(dto.getCvnWasser());
        entity.setCvnArmas(dto.getCvnArmas());
        entity.setCvnMontreal(dto.getCvnMontreal());
        entity.setCvnEstocolmo(dto.getCvnEstocolmo());
        entity.setFormaDesc(dto.getFormaDesc());
        entity.setIdeIdentificadorRegla(dto.getIdeIdentificadorRegla());

        return toResponseDTO(repository.save(entity));
    }

    @Override
    public CatCasFraccionTtraResponseDTO findById(Short id) {
        CatCasFraccionTtra entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Registro no encontrado con id: " + id
                ));
        return toResponseDTO(entity);
    }

    @Override
    public CatCasFraccionTtraResponseDTO update(Short id, CatCasFraccionTtraRequestDTO dto) {

        CatCasFraccionTtra entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Registro no encontrado con id: " + id
                ));

        if (dto.getIdCas() != null) {
            entity.setIdCas(
                    casRepository.findById(dto.getIdCas())
                            .orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "CAS no encontrado"))
            );
        }

        if (dto.getCveFraccion() != null) {
            entity.setCveFraccion(
                    fraccionArancelariaRepository.findById(dto.getCveFraccion())
                            .orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "Fracción arancelaria no encontrada"))
            );
        }

        if (dto.getIdTipoTramite() != null) {
            entity.setIdTipoTramite(
                    tipoTramiteRepository.findById(dto.getIdTipoTramite())
                            .orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "Tipo de trámite no encontrado"))
            );
        }

        if (dto.getBlnRotterdam() != null) {
            entity.setBlnRotterdam(dto.getBlnRotterdam());
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
        if (dto.getDescFraccionAlt() != null) {
            entity.setDescFraccionAlt(dto.getDescFraccionAlt());
        }
        if (dto.getCvnWasser() != null) {
            entity.setCvnWasser(dto.getCvnWasser());
        }
        if (dto.getCvnArmas() != null) {
            entity.setCvnArmas(dto.getCvnArmas());
        }
        if (dto.getCvnMontreal() != null) {
            entity.setCvnMontreal(dto.getCvnMontreal());
        }
        if (dto.getCvnEstocolmo() != null) {
            entity.setCvnEstocolmo(dto.getCvnEstocolmo());
        }
        if (dto.getFormaDesc() != null) {
            entity.setFormaDesc(dto.getFormaDesc());
        }
        if (dto.getIdeIdentificadorRegla() != null) {
            entity.setIdeIdentificadorRegla(dto.getIdeIdentificadorRegla());
        }

        return toResponseDTO(repository.save(entity));
    }

    @Override
    public List<ClasifProductoTraDTO> listadoTipoTramite() {
        return repository.listadoTipoTramite();
    }

    @Override
    public List<SelectDTO> listadoCas() {
        List<CatCa> lista = casRepository.findAllByBlActivoOrderByDescCasAsc((short) 1);
        List<SelectDTO> resultado = new ArrayList<>();
        for (CatCa c : lista) {
            SelectDTO dto = new SelectDTO();
            dto.setId(c.getId().longValue());
            dto.setNombre(c.getDescCas());
            resultado.add(dto);
        }
        return resultado;
    }

    private CatCasFraccionTtraResponseDTO toResponseDTO(CatCasFraccionTtra entity) {
        String descTipoTramite = entity.getIdTipoTramite().getId() + "  " +
                (entity.getIdTipoTramite().getDescModalidad() != null
                        ? entity.getIdTipoTramite().getDescModalidad()
                        : entity.getIdTipoTramite().getDescSubservicio());

        return CatCasFraccionTtraResponseDTO.builder()
                .id(entity.getId())
                .idCas(entity.getIdCas().getId())
                .descCas(entity.getIdCas().getDescCas())
                .cveFraccion(entity.getCveFraccion().getCveFraccion())
                .descripcionFraccion(entity.getCveFraccion().getDescripcion())
                .idTipoTramite(entity.getIdTipoTramite().getId())
                .descTipoTramite(descTipoTramite)
                .blnRotterdam(entity.getBlnRotterdam())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blActivo(entity.getBlActivo())
                .descFraccionAlt(entity.getDescFraccionAlt())
                .cvnWasser(entity.getCvnWasser())
                .cvnArmas(entity.getCvnArmas())
                .cvnMontreal(entity.getCvnMontreal())
                .cvnEstocolmo(entity.getCvnEstocolmo())
                .formaDesc(entity.getFormaDesc())
                .ideIdentificadorRegla(entity.getIdeIdentificadorRegla())
                .build();
    }
}
