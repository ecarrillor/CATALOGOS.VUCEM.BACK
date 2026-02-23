package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatTipoRfcService;
import com.example.vucem_catalogos_service.model.dto.CatTipoRfcDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatTipoRfc;
import com.example.vucem_catalogos_service.model.entity.CatTipoRfcId;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoRfcRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatUnidadAdministrativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CatTipoRfcServiceImpl implements ICatTipoRfcService {

    @Autowired
    private ICatTipoRfcRepository catTipoRfcRepository;

    @Autowired
    private ICatUnidadAdministrativaRepository catUnidadAdministrativaRepository;

    @Override
    public PageResponseDTO<CatTipoRfcDTO> list(String search, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if ("activo".equalsIgnoreCase(search)) {
            activo = true;
        } else if ("inactivo".equalsIgnoreCase(search)) {
            activo = false;
        } else {
            texto = search;
        }

        Page<CatTipoRfcDTO> page = catTipoRfcRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatTipoRfcDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatTipoRfcDTO findById(String rfc, String ideTipoRfc) {
        return catTipoRfcRepository.findByTipoRfcDTO(rfc, ideTipoRfc)
                .orElseThrow(() -> new RuntimeException("CatTipoRfc no encontrado para rfc=" + rfc + ", ideTipoRfc=" + ideTipoRfc));
    }

    @Override
    public CatTipoRfcDTO create(CatTipoRfcDTO dto) {
        CatTipoRfcId id = new CatTipoRfcId();
        id.setRfc(dto.getRfc());
        id.setIdeTipoRfc(dto.getIdeTipoRfc());

        CatTipoRfc entity = new CatTipoRfc();
        entity.setId(id);
        entity.setRazonSocial(dto.getRazonSocial());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setDireccion(dto.getDireccion());
        entity.setTelefono(dto.getTelefono());
        entity.setClave(dto.getClave());
        entity.setBlnActivo(dto.getBlnActivo());
        entity.setCorreoElectronico(dto.getCorreoElectronico());
        entity.setFax(dto.getFax());
        entity.setBlnLabAcreditado(dto.getBlnLabAcreditado());

        if (dto.getCveUnidadAdministrativa() != null) {
            entity.setCveUnidadAdministrativa(
                    catUnidadAdministrativaRepository.findById(dto.getCveUnidadAdministrativa())
                            .orElseThrow(() -> new RuntimeException("CatUnidadAdministrativa no encontrada: " + dto.getCveUnidadAdministrativa()))
            );
        }

        CatTipoRfc saved = catTipoRfcRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatTipoRfcDTO update(String rfc, String ideTipoRfc, CatTipoRfcDTO dto) {
        CatTipoRfcId pk = new CatTipoRfcId();
        pk.setRfc(rfc);
        pk.setIdeTipoRfc(ideTipoRfc);

        CatTipoRfc entity = catTipoRfcRepository.findById(pk)
                .orElseThrow(() -> new RuntimeException("CatTipoRfc no encontrado para rfc=" + rfc + ", ideTipoRfc=" + ideTipoRfc));

        entity.setRazonSocial(dto.getRazonSocial());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setDireccion(dto.getDireccion());
        entity.setTelefono(dto.getTelefono());
        entity.setClave(dto.getClave());
        entity.setBlnActivo(dto.getBlnActivo());
        entity.setCorreoElectronico(dto.getCorreoElectronico());
        entity.setFax(dto.getFax());
        entity.setBlnLabAcreditado(dto.getBlnLabAcreditado());

        if (dto.getCveUnidadAdministrativa() != null) {
            entity.setCveUnidadAdministrativa(
                    catUnidadAdministrativaRepository.findById(dto.getCveUnidadAdministrativa())
                            .orElseThrow(() -> new RuntimeException("CatUnidadAdministrativa no encontrada: " + dto.getCveUnidadAdministrativa()))
            );
        }

        CatTipoRfc saved = catTipoRfcRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatTipoRfcDTO mapToDTO(CatTipoRfc entity) {
        return CatTipoRfcDTO.builder()
                .rfc(entity.getId().getRfc())
                .ideTipoRfc(entity.getId().getIdeTipoRfc())
                .razonSocial(entity.getRazonSocial())
                .fecFinVigencia(entity.getFecFinVigencia())
                .fecIniVigencia(entity.getFecIniVigencia())
                .direccion(entity.getDireccion())
                .telefono(entity.getTelefono())
                .clave(entity.getClave())
                .blnActivo(entity.getBlnActivo())
                .correoElectronico(entity.getCorreoElectronico())
                .fax(entity.getFax())
                .blnLabAcreditado(entity.getBlnLabAcreditado())
                .cveUnidadAdministrativa(entity.getCveUnidadAdministrativa() != null
                        ? entity.getCveUnidadAdministrativa().getCveUnidadAdministrativa() : null)
                .nombreUnidadAdministrativa(entity.getCveUnidadAdministrativa() != null
                        ? entity.getCveUnidadAdministrativa().getNombre() : null)
                .build();
    }
}
