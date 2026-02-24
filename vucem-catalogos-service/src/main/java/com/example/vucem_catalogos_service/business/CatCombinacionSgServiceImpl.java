package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatCombinacionSgService;
import com.example.vucem_catalogos_service.model.dto.CatCombinacionSgDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatCatalogoD;
import com.example.vucem_catalogos_service.model.entity.CatCombinacionSg;
import com.example.vucem_catalogos_service.model.entity.CatPais;
import com.example.vucem_catalogos_service.persistence.repo.ICatCatalogoDRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatCombinacionSgRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatPaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CatCombinacionSgServiceImpl implements ICatCombinacionSgService {

    @Autowired
    private ICatCombinacionSgRepository catCombinacionSgRepository;

    @Autowired
    private ICatCatalogoDRepository catCatalogoDRepository;

    @Autowired
    private ICatPaisRepository catPaisRepository;

    @Override
    public PageResponseDTO<CatCombinacionSgDTO> list(String search, Pageable pageable) {
        Short activo = null;
        String texto = null;

        if ("activo".equalsIgnoreCase(search)) {
            activo = 1;
        } else if ("inactivo".equalsIgnoreCase(search)) {
            activo = 0;
        } else {
            texto = search;
        }

        Page<CatCombinacionSgDTO> page = catCombinacionSgRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatCombinacionSgDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatCombinacionSgDTO findById(Long id) {
        return catCombinacionSgRepository.findByCombinacionSgDTO(id)
                .orElseThrow(() -> new RuntimeException("CatCombinacionSg no encontrado con id: " + id));
    }

    @Override
    public CatCombinacionSgDTO create(CatCombinacionSgDTO dto) {
        CatCombinacionSg entity = new CatCombinacionSg();
        entity.setId(dto.getId());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());
        entity.setIdeTipoCertificadoMerc(dto.getIdeTipoCertificadoMerc());

        if (dto.getCvcEspecie() != null) {
            CatCatalogoD especie = catCatalogoDRepository.findById(dto.getCvcEspecie())
                    .orElseThrow(() -> new RuntimeException("CatCatalogoD no encontrado: " + dto.getCvcEspecie()));
            entity.setCvcEspecie(especie);
        }

        if (dto.getCvcFuncionZootecnica() != null) {
            CatCatalogoD funcion = catCatalogoDRepository.findById(dto.getCvcFuncionZootecnica())
                    .orElseThrow(() -> new RuntimeException("CatCatalogoD no encontrado: " + dto.getCvcFuncionZootecnica()));
            entity.setCvcFuncionZootecnica(funcion);
        }

        if (dto.getCvcNombreComun() != null) {
            CatCatalogoD nombreComun = catCatalogoDRepository.findById(dto.getCvcNombreComun())
                    .orElseThrow(() -> new RuntimeException("CatCatalogoD no encontrado: " + dto.getCvcNombreComun()));
            entity.setCvcNombreComun(nombreComun);
        }

        if (dto.getCvcTipoProducto() != null) {
            CatCatalogoD tipoProducto = catCatalogoDRepository.findById(dto.getCvcTipoProducto())
                    .orElseThrow(() -> new RuntimeException("CatCatalogoD no encontrado: " + dto.getCvcTipoProducto()));
            entity.setCvcTipoProducto(tipoProducto);
        }

        if (dto.getCvePais() != null) {
            CatPais pais = catPaisRepository.findById(dto.getCvePais())
                    .orElseThrow(() -> new RuntimeException("CatPais no encontrado: " + dto.getCvePais()));
            entity.setCvePais(pais);
        }

        CatCombinacionSg saved = catCombinacionSgRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatCombinacionSgDTO update(Long id, CatCombinacionSgDTO dto) {
        CatCombinacionSg entity = catCombinacionSgRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatCombinacionSg no encontrado con id: " + id));

        if (dto.getFecIniVigencia() != null) entity.setFecIniVigencia(dto.getFecIniVigencia());
        if (dto.getFecFinVigencia() != null) entity.setFecFinVigencia(dto.getFecFinVigencia());
        if (dto.getBlnActivo() != null) entity.setBlnActivo(dto.getBlnActivo());
        if (dto.getIdeTipoCertificadoMerc() != null) entity.setIdeTipoCertificadoMerc(dto.getIdeTipoCertificadoMerc());

        if (dto.getCvcEspecie() != null) {
            CatCatalogoD especie = catCatalogoDRepository.findById(dto.getCvcEspecie())
                    .orElseThrow(() -> new RuntimeException("CatCatalogoD no encontrado: " + dto.getCvcEspecie()));
            entity.setCvcEspecie(especie);
        }

        if (dto.getCvcFuncionZootecnica() != null) {
            CatCatalogoD funcion = catCatalogoDRepository.findById(dto.getCvcFuncionZootecnica())
                    .orElseThrow(() -> new RuntimeException("CatCatalogoD no encontrado: " + dto.getCvcFuncionZootecnica()));
            entity.setCvcFuncionZootecnica(funcion);
        }

        if (dto.getCvcNombreComun() != null) {
            CatCatalogoD nombreComun = catCatalogoDRepository.findById(dto.getCvcNombreComun())
                    .orElseThrow(() -> new RuntimeException("CatCatalogoD no encontrado: " + dto.getCvcNombreComun()));
            entity.setCvcNombreComun(nombreComun);
        }

        if (dto.getCvcTipoProducto() != null) {
            CatCatalogoD tipoProducto = catCatalogoDRepository.findById(dto.getCvcTipoProducto())
                    .orElseThrow(() -> new RuntimeException("CatCatalogoD no encontrado: " + dto.getCvcTipoProducto()));
            entity.setCvcTipoProducto(tipoProducto);
        }

        if (dto.getCvePais() != null) {
            CatPais pais = catPaisRepository.findById(dto.getCvePais())
                    .orElseThrow(() -> new RuntimeException("CatPais no encontrado: " + dto.getCvePais()));
            entity.setCvePais(pais);
        }

        CatCombinacionSg saved = catCombinacionSgRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatCombinacionSgDTO mapToDTO(CatCombinacionSg entity) {
        return CatCombinacionSgDTO.builder()
                .id(entity.getId())
                .cvcEspecie(entity.getCvcEspecie() != null ? entity.getCvcEspecie().getCveCatalogo() : null)
                .descEspecie(entity.getCvcEspecie() != null ? entity.getCvcEspecie().getDescGenerica1() : null)
                .cvcFuncionZootecnica(entity.getCvcFuncionZootecnica() != null ? entity.getCvcFuncionZootecnica().getCveCatalogo() : null)
                .descFuncionZootecnica(entity.getCvcFuncionZootecnica() != null ? entity.getCvcFuncionZootecnica().getDescGenerica1() : null)
                .cvcNombreComun(entity.getCvcNombreComun() != null ? entity.getCvcNombreComun().getCveCatalogo() : null)
                .descNombreComun(entity.getCvcNombreComun() != null ? entity.getCvcNombreComun().getDescGenerica1() : null)
                .cvcTipoProducto(entity.getCvcTipoProducto() != null ? entity.getCvcTipoProducto().getCveCatalogo() : null)
                .descTipoProducto(entity.getCvcTipoProducto() != null ? entity.getCvcTipoProducto().getDescGenerica1() : null)
                .cvePais(entity.getCvePais() != null ? entity.getCvePais().getCvePais() : null)
                .nombrePais(entity.getCvePais() != null ? entity.getCvePais().getNombre() : null)
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .ideTipoCertificadoMerc(entity.getIdeTipoCertificadoMerc())
                .build();
    }
}
