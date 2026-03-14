package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatClasifProductoService;
import com.example.vucem_catalogos_service.model.dto.CatClasifProductoDTO;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatClasifProducto;
import com.example.vucem_catalogos_service.model.entity.CatTipoTramite;
import com.example.vucem_catalogos_service.persistence.repo.ICatClasifProductoRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CatClasifProductoServiceImpl implements ICatClasifProductoService {

    @Autowired
    private ICatClasifProductoRepository catClasifProductoRepository;

    @Autowired
    private ICatTipoTramiteRepository catTipoTramiteRepository;

    @Override
    public PageResponseDTO<CatClasifProductoDTO> list(String search, Long idTipoTramite, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if ("activo".equalsIgnoreCase(search)) {
            activo = true;
        } else if ("inactivo".equalsIgnoreCase(search)) {
            activo = false;
        } else {
            texto = search;
        }

        Page<CatClasifProductoDTO> page = catClasifProductoRepository.search(texto, activo, idTipoTramite, pageable);

        return PageResponseDTO.<CatClasifProductoDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatClasifProductoDTO findById(Long id) {
        return catClasifProductoRepository.findByClasifProductoDTO(id)
                .orElseThrow(() -> new RuntimeException("CatClasifProducto no encontrado con id: " + id));
    }

    @Override
    public CatClasifProductoDTO create(CatClasifProductoDTO dto) {
        CatClasifProducto entity = new CatClasifProducto();
        entity.setIdClasifProduct(dto.getIdClasifProduct());
        entity.setNombre(dto.getNombre());
        entity.setIdeTipoClasifProducto(dto.getIdeTipoClasifProducto());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(true);

        if (dto.getIdTipoTramite() != null) {
            CatTipoTramite tipoTramite = catTipoTramiteRepository.findById(dto.getIdTipoTramite())
                    .orElseThrow(() -> new RuntimeException(
                            "CatTipoTramite no encontrado con id: " + dto.getIdTipoTramite()));
            entity.setIdTipoTramite(tipoTramite);
        }

        if (dto.getIdClasifProductoR() != null) {
            CatClasifProducto padre = catClasifProductoRepository.findById(dto.getIdClasifProductoR())
                    .orElseThrow(() -> new RuntimeException(
                            "CatClasifProducto padre no encontrado con id: " + dto.getIdClasifProductoR()));
            entity.setIdClasifProductoR(padre);
        }

        CatClasifProducto saved = catClasifProductoRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatClasifProductoDTO update(Long id, CatClasifProductoDTO dto) {
        CatClasifProducto entity = catClasifProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatClasifProducto no encontrado con id: " + id));

        if (dto.getNombre() != null) entity.setNombre(dto.getNombre());
        if (dto.getIdeTipoClasifProducto() != null) entity.setIdeTipoClasifProducto(dto.getIdeTipoClasifProducto());
        if (dto.getFecIniVigencia() != null) entity.setFecIniVigencia(dto.getFecIniVigencia());
        if (dto.getFecFinVigencia() != null) entity.setFecFinVigencia(dto.getFecFinVigencia());
        if (dto.getBlnActivo() != null) entity.setBlnActivo(dto.getBlnActivo());

        if (dto.getIdTipoTramite() != null) {
            CatTipoTramite tipoTramite = catTipoTramiteRepository.findById(dto.getIdTipoTramite())
                    .orElseThrow(() -> new RuntimeException(
                            "CatTipoTramite no encontrado con id: " + dto.getIdTipoTramite()));
            entity.setIdTipoTramite(tipoTramite);
        }

        if (dto.getIdClasifProductoR() != null) {
            CatClasifProducto padre = catClasifProductoRepository.findById(dto.getIdClasifProductoR())
                    .orElseThrow(() -> new RuntimeException(
                            "CatClasifProducto padre no encontrado con id: " + dto.getIdClasifProductoR()));
            entity.setIdClasifProductoR(padre);
        }

        CatClasifProducto saved = catClasifProductoRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public List<ClasifProductoTraDTO> listadoTipoTramite() {
        return catClasifProductoRepository.findTipoTramiteListado();
    }

    @Override
    public List<ClasifProductoTraDTO> listadoClasifPrR(Long idTipoTramite) {
        return catClasifProductoRepository.listadoClasifPrR(idTipoTramite);
    }

    private CatClasifProductoDTO mapToDTO(CatClasifProducto entity) {
        return CatClasifProductoDTO.builder()
                .idClasifProduct(entity.getIdClasifProduct())
                .idTipoTramite(entity.getIdTipoTramite() != null ? entity.getIdTipoTramite().getId() : null)
                .nombreTipoTramite(entity.getIdTipoTramite() != null ? entity.getIdTipoTramite().getDescSubservicio() : null)
                .idClasifProductoR(entity.getIdClasifProductoR() != null ? entity.getIdClasifProductoR().getIdClasifProduct() : null)
                .nombre(entity.getNombre())
                .ideTipoClasifProducto(entity.getIdeTipoClasifProducto())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
