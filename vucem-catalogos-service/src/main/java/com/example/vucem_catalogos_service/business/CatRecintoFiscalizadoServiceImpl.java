package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatRecintoFiscalizadoService;
import com.example.vucem_catalogos_service.model.dto.CatRecintoFiscalizadoDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatRecintoFiscalizado;
import com.example.vucem_catalogos_service.persistence.repo.ICatAduanaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatRecintoFiscalizadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class CatRecintoFiscalizadoServiceImpl implements ICatRecintoFiscalizadoService {

    @Autowired
    private ICatRecintoFiscalizadoRepository catRecintoFiscalizadoRepository;

    @Autowired
    private ICatAduanaRepository catAduanaRepository;

    @Override
    public PageResponseDTO<CatRecintoFiscalizadoDTO> list(String search, Pageable pageable) {
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

        Page<CatRecintoFiscalizadoDTO> page = catRecintoFiscalizadoRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatRecintoFiscalizadoDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatRecintoFiscalizadoDTO findById(Long id) {
        return catRecintoFiscalizadoRepository.findByRecintoFiscalizadoDTO(id)
                .orElseThrow(() -> new RuntimeException("CatRecintoFiscalizado no encontrado con id: " + id));
    }

    @Override
    public CatRecintoFiscalizadoDTO create(CatRecintoFiscalizadoDTO dto) {
        CatRecintoFiscalizado entity = new CatRecintoFiscalizado();

        if (catRecintoFiscalizadoRepository.existsById(dto.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ya existe un reciento fiscalizado con id: "
                            + dto.getId()
            );
        }

        entity.setId(dto.getId());
        entity.setIdeTipoRecintoFiscalizado(dto.getIdeTipoRecintoFiscalizado());
        entity.setNombre(dto.getNombre());
        entity.setRfc(dto.getRfc());
        entity.setNumAutorizacion(dto.getNumAutorizacion());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());
        entity.setCodCamir(dto.getCodCamir());
        entity.setBlnComRfMf(dto.getBlnComRfMf());
        entity.setCorreoElectronico(dto.getCorreoElectronico());
        entity.setDescUrl(dto.getDescUrl());
        entity.setTipo(dto.getTipo());

        if (dto.getCveAduana() != null) {
            entity.setCveAduana(
                    catAduanaRepository.findById(dto.getCveAduana())
                            .orElseThrow(() -> new RuntimeException("CatAduana no encontrada: " + dto.getCveAduana()))
            );
        }

        CatRecintoFiscalizado saved = catRecintoFiscalizadoRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatRecintoFiscalizadoDTO update(Long id, CatRecintoFiscalizadoDTO dto) {
        CatRecintoFiscalizado entity = catRecintoFiscalizadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatRecintoFiscalizado no encontrado con id: " + id));

        entity.setIdeTipoRecintoFiscalizado(dto.getIdeTipoRecintoFiscalizado());
        entity.setNombre(dto.getNombre());
        entity.setRfc(dto.getRfc());
        entity.setNumAutorizacion(dto.getNumAutorizacion());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());
        entity.setCodCamir(dto.getCodCamir());
        entity.setBlnComRfMf(dto.getBlnComRfMf());
        entity.setCorreoElectronico(dto.getCorreoElectronico());
        entity.setDescUrl(dto.getDescUrl());
        entity.setTipo(dto.getTipo());

        if (dto.getCveAduana() != null) {
            entity.setCveAduana(
                    catAduanaRepository.findById(dto.getCveAduana())
                            .orElseThrow(() -> new RuntimeException("CatAduana no encontrada: " + dto.getCveAduana()))
            );
        }

        CatRecintoFiscalizado saved = catRecintoFiscalizadoRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatRecintoFiscalizadoDTO mapToDTO(CatRecintoFiscalizado entity) {
        return CatRecintoFiscalizadoDTO.builder()
                .id(entity.getId())
                .cveAduana(entity.getCveAduana() != null ? entity.getCveAduana().getCveAduana() : null)
                .nombreAduana(entity.getCveAduana() != null ? entity.getCveAduana().getNombre() : null)
                .ideTipoRecintoFiscalizado(entity.getIdeTipoRecintoFiscalizado())
                .nombre(entity.getNombre())
                .rfc(entity.getRfc())
                .numAutorizacion(entity.getNumAutorizacion())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .codCamir(entity.getCodCamir())
                .blnComRfMf(entity.getBlnComRfMf())
                .correoElectronico(entity.getCorreoElectronico())
                .descUrl(entity.getDescUrl())
                .tipo(entity.getTipo())
                .build();
    }
}
