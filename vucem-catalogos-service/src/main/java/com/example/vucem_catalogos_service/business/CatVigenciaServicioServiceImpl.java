package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatVigenciaServicioService;
import com.example.vucem_catalogos_service.model.dto.CatVigenciaServicioDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.*;
import com.example.vucem_catalogos_service.persistence.repo.ICatCriterioOrigenRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatPaisTratadoAcuerdoRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTratadoAcuerdoRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatVigenciaServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CatVigenciaServicioServiceImpl implements ICatVigenciaServicioService {

    @Autowired
    private ICatVigenciaServicioRepository catVigenciaServicioRepository;

    @Autowired
    private ICatPaisTratadoAcuerdoRepository catPaisTratadoAcuerdoRepository;

    @Autowired
    private ICatTratadoAcuerdoRepository catTratadoAcuerdoRepository;

    @Autowired
    private ICatCriterioOrigenRepository catCriterioOrigenRepository;

    @Override
    public PageResponseDTO<CatVigenciaServicioDTO> list(String search, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if ("activo".equalsIgnoreCase(search)) {
            activo = true;
        } else if ("inactivo".equalsIgnoreCase(search)) {
            activo = false;
        } else {
            texto = search;
        }


        Page<CatVigenciaServicioDTO> page = catVigenciaServicioRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatVigenciaServicioDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatVigenciaServicioDTO findById(Short id) {
        return catVigenciaServicioRepository.findByVigenciaServicioDTO(id)
                .orElseThrow(() -> new RuntimeException("CatVigenciaServicio no encontrado con id: " + id));
    }

    @Override
    public CatVigenciaServicioDTO create(CatVigenciaServicioDTO dto) {

        if (catVigenciaServicioRepository.existsById(dto.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }

        CatVigenciaServicio entity = new CatVigenciaServicio();
        entity.setId(dto.getId());
        entity.setNumVigencia(dto.getNumVigencia());
        entity.setIdeTipoVigencia(dto.getIdeTipoVigencia());
        entity.setIdeTipoServicioCeror(dto.getIdeTipoServicioCeror());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        if (dto.getCvePais() != null && dto.getIdTratadoAcuerdo() != null) {
            CatPaisTratadoAcuerdo paisTratado = catPaisTratadoAcuerdoRepository
                    .findByCvePaisAndIdTratadoAcuerdo(dto.getCvePais(), dto.getIdTratadoAcuerdo())
                    .orElseThrow(() -> new RuntimeException("CatPaisTratadoAcuerdo no encontrado"));
            entity.setCatPaisTratadoAcuerdo(paisTratado);
        }

        if (dto.getIdBloque() != null) {
            CatTratadoAcuerdo bloque = catTratadoAcuerdoRepository.findById(dto.getIdBloque())
                    .orElseThrow(() -> new RuntimeException(
                            "CatTratadoAcuerdo no encontrado con id: " + dto.getIdBloque()));
            entity.setIdBloque(bloque);
        }

        if (dto.getCriterio() != null) {
            CatCriterioOrigen criterio = catCriterioOrigenRepository.findById(dto.getCriterio())
                    .orElseThrow(() -> new RuntimeException(
                            "CatCriterioOrigen no encontrado con id: " + dto.getCriterio()));
            entity.setCveCriterioOrigen(criterio);
        }

        CatVigenciaServicio saved = catVigenciaServicioRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatVigenciaServicioDTO update(Short id, CatVigenciaServicioDTO dto) {
        CatVigenciaServicio entity = catVigenciaServicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatVigenciaServicio no encontrado con id: " + id));

        if (dto.getNumVigencia() != null) entity.setNumVigencia(dto.getNumVigencia());
        if (dto.getIdeTipoVigencia() != null) entity.setIdeTipoVigencia(dto.getIdeTipoVigencia());
        if (dto.getIdeTipoServicioCeror() != null) entity.setIdeTipoServicioCeror(dto.getIdeTipoServicioCeror());
        if (dto.getFecIniVigencia() != null) entity.setFecIniVigencia(dto.getFecIniVigencia());
        if (dto.getBlnActivo() != null) entity.setBlnActivo(dto.getBlnActivo());

        if (dto.getCvePais() != null && dto.getIdTratadoAcuerdo() != null) {
            CatPaisTratadoAcuerdo paisTratado = catPaisTratadoAcuerdoRepository
                    .findByCvePaisAndIdTratadoAcuerdo(dto.getCvePais(), dto.getIdTratadoAcuerdo())
                    .orElseThrow(() -> new RuntimeException("CatPaisTratadoAcuerdo no encontrado"));
            entity.setCatPaisTratadoAcuerdo(paisTratado);
        }

        if (dto.getIdBloque() != null) {
            CatTratadoAcuerdo bloque = catTratadoAcuerdoRepository.findById(dto.getIdBloque())
                    .orElseThrow(() -> new RuntimeException(
                            "CatTratadoAcuerdo no encontrado con id: " + dto.getIdBloque()));
            entity.setIdBloque(bloque);
        }

        CatVigenciaServicio saved = catVigenciaServicioRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public List<SelectDTO> listadoCriterioOrigen() {
        List<CatCriterioOrigen> entidades = catCriterioOrigenRepository.findByBlnActivoTrue();
        List<SelectDTO> resultado = new ArrayList<>();

        for (CatCriterioOrigen e : entidades) {
            SelectDTO dto = new SelectDTO();
            dto.setCve(e.getCveCriterioOrigen());
            dto.setNombre(e.getNombre());
            resultado.add(dto);
        }
        return resultado;
    }

    private CatVigenciaServicioDTO mapToDTO(CatVigenciaServicio entity) {
        return CatVigenciaServicioDTO.builder()
                .id(entity.getId())
                .numVigencia(entity.getNumVigencia())
                .ideTipoVigencia(entity.getIdeTipoVigencia())
                .ideTipoServicioCeror(entity.getIdeTipoServicioCeror())
                .cvePais(entity.getCatPaisTratadoAcuerdo() != null
                        ? entity.getCatPaisTratadoAcuerdo().getId().getCvePais() : null)
                .idTratadoAcuerdo(entity.getCatPaisTratadoAcuerdo() != null
                        ? entity.getCatPaisTratadoAcuerdo().getId().getIdTratadoAcuerdo() : null)
                .idBloque(entity.getIdBloque() != null ? entity.getIdBloque().getId() : null)
                .nombreBloque(entity.getIdBloque() != null ? entity.getIdBloque().getNombre() : null)
                .fecIniVigencia(entity.getFecIniVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
