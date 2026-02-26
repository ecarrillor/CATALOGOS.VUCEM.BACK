package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatAduanaClasifProdService;
import com.example.vucem_catalogos_service.model.dto.CatAduanaClasifProdDTO;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatAduana;
import com.example.vucem_catalogos_service.model.entity.CatAduanaClasifProd;
import com.example.vucem_catalogos_service.model.entity.CatClasifProducto;
import com.example.vucem_catalogos_service.persistence.repo.ICatAduanaClasifProdRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatAduanaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatClasifProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CatAduanaClasifProdServiceImpl implements ICatAduanaClasifProdService {

    @Autowired
    private ICatAduanaClasifProdRepository catAduanaClasifProdRepository;

    @Autowired
    private ICatAduanaRepository catAduanaRepository;

    @Autowired
    private ICatClasifProductoRepository catClasifProductoRepository;

    @Override
    public PageResponseDTO<CatAduanaClasifProdDTO> list(String search, Long idClasifProducto, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if ("activo".equalsIgnoreCase(search)) {
            activo = true;
        } else if ("inactivo".equalsIgnoreCase(search)) {
            activo = false;
        } else {
            texto = search;
        }

        Page<CatAduanaClasifProdDTO> page = catAduanaClasifProdRepository.search(texto, activo, idClasifProducto, pageable);

        return PageResponseDTO.<CatAduanaClasifProdDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatAduanaClasifProdDTO findById(Long id) {
        return catAduanaClasifProdRepository.findByAduanaClasifProdDTO(id)
                .orElseThrow(() -> new RuntimeException("CatAduanaClasifProd no encontrado con id: " + id));
    }

    @Override
    public CatAduanaClasifProdDTO create(CatAduanaClasifProdDTO dto) {
        CatAduanaClasifProd entity = new CatAduanaClasifProd();
        entity.setId(dto.getId());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        if (dto.getCveAduana() != null) {
            CatAduana aduana = catAduanaRepository.findById(dto.getCveAduana())
                    .orElseThrow(() -> new RuntimeException(
                            "CatAduana no encontrada con cveAduana: " + dto.getCveAduana()));
            entity.setAduana(aduana);
        }

        if (dto.getIdClasifProducto() != null) {
            CatClasifProducto clasifProducto = catClasifProductoRepository.findById(dto.getIdClasifProducto())
                    .orElseThrow(() -> new RuntimeException(
                            "CatClasifProducto no encontrado con id: " + dto.getIdClasifProducto()));
            entity.setIdClasifProducto(clasifProducto);
        }

        CatAduanaClasifProd saved = catAduanaClasifProdRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatAduanaClasifProdDTO update(Long id, CatAduanaClasifProdDTO dto) {
        CatAduanaClasifProd entity = catAduanaClasifProdRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatAduanaClasifProd no encontrado con id: " + id));

        if (dto.getFecIniVigencia() != null) entity.setFecIniVigencia(dto.getFecIniVigencia());
        if (dto.getFecFinVigencia() != null) entity.setFecFinVigencia(dto.getFecFinVigencia());
        if (dto.getBlnActivo() != null) entity.setBlnActivo(dto.getBlnActivo());

        if (dto.getCveAduana() != null) {
            CatAduana aduana = catAduanaRepository.findById(dto.getCveAduana())
                    .orElseThrow(() -> new RuntimeException(
                            "CatAduana no encontrada con cveAduana: " + dto.getCveAduana()));
            entity.setAduana(aduana);
        }

        if (dto.getIdClasifProducto() != null) {
            CatClasifProducto clasifProducto = catClasifProductoRepository.findById(dto.getIdClasifProducto())
                    .orElseThrow(() -> new RuntimeException(
                            "CatClasifProducto no encontrado con id: " + dto.getIdClasifProducto()));
            entity.setIdClasifProducto(clasifProducto);
        }

        CatAduanaClasifProd saved = catAduanaClasifProdRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public List<ClasifProductoTraDTO> listadoTipoTramite() {
        return catAduanaClasifProdRepository.findTipoTramiteListado()
                .stream()
                .map(row -> new ClasifProductoTraDTO(
                        row[0] != null ? ((Number) row[0]).longValue() : null,
                        (String) row[1]))
                .toList();
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

    @Override
    public List<ClasifProductoTraDTO> listadoClasifProducto(Long idTipoTramite) {
        return catClasifProductoRepository.listadoClasifPrR(idTipoTramite);
    }

    private CatAduanaClasifProdDTO mapToDTO(CatAduanaClasifProd entity) {
        return CatAduanaClasifProdDTO.builder()
                .id(entity.getId())
                .cveAduana(entity.getAduana() != null ? entity.getAduana().getCveAduana() : null)
                .nombreAduana(entity.getAduana() != null ? entity.getAduana().getNombre() : null)
                .idClasifProducto(entity.getIdClasifProducto() != null ? entity.getIdClasifProducto().getIdClasifProduct() : null)
                .nombreClasifProducto(entity.getIdClasifProducto() != null ? entity.getIdClasifProducto().getNombre() : null)
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
