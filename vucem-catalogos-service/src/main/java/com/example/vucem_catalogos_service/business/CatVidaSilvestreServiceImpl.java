package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatVidaSilvestreService;
import com.example.vucem_catalogos_service.model.dto.CatVidaSilvestreDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatEspecie;
import com.example.vucem_catalogos_service.model.entity.CatVidaSilvestre;
import com.example.vucem_catalogos_service.persistence.repo.ICatEspecieRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatVidaSilvestreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CatVidaSilvestreServiceImpl implements ICatVidaSilvestreService {

    @Autowired
    private ICatVidaSilvestreRepository catVidaSilvestreRepository;

    @Autowired
    private ICatEspecieRepository catEspecieRepository;

    @Override
    public PageResponseDTO<CatVidaSilvestreDTO> list(String search, String tipo, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if ("activo".equalsIgnoreCase(search)) {
            activo = true;
        } else if ("inactivo".equalsIgnoreCase(search)) {
            activo = false;
        } else {
            texto = search;
        }

        Page<CatVidaSilvestreDTO> page = catVidaSilvestreRepository.search(texto, tipo, activo, pageable);

        return PageResponseDTO.<CatVidaSilvestreDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatVidaSilvestreDTO findById(Integer id) {
        return catVidaSilvestreRepository.findByVidaSilvestreDTO(id)
                .orElseThrow(() -> new RuntimeException("CatVidaSilvestre no encontrado con id: " + id));
    }

    @Override
    public CatVidaSilvestreDTO create(CatVidaSilvestreDTO dto) {
        CatVidaSilvestre entity = new CatVidaSilvestre();
        entity.setIdeTipoVidaSilvestre(dto.getIdeTipoVidaSilvestre());
        entity.setDescNombreComun(dto.getDescNombreComun());
        entity.setDescNombreCientifico(dto.getDescNombreCientifico());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());
        entity.setIdeClasifTaxonomica(dto.getIdeClasifTaxonomica());
        entity.setFuncionZootecnica(dto.getFuncionZootecnica());

        if (dto.getIdEspecie() != null) {
            entity.setIdEspecie(
                    catEspecieRepository.findById(dto.getIdEspecie())
                            .orElseThrow(() -> new RuntimeException("CatEspecie no encontrada: " + dto.getIdEspecie()))
            );
        }

        CatVidaSilvestre saved = catVidaSilvestreRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatVidaSilvestreDTO update(Integer id, CatVidaSilvestreDTO dto) {
        CatVidaSilvestre entity = catVidaSilvestreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatVidaSilvestre no encontrado con id: " + id));

        if (dto.getIdeTipoVidaSilvestre() != null) {
            entity.setIdeTipoVidaSilvestre(dto.getIdeTipoVidaSilvestre());
        }
        if (dto.getDescNombreComun() != null) {
            entity.setDescNombreComun(dto.getDescNombreComun());
        }
        if (dto.getDescNombreCientifico() != null) {
            entity.setDescNombreCientifico(dto.getDescNombreCientifico());
        }
        if (dto.getFecIniVigencia() != null) {
            entity.setFecIniVigencia(dto.getFecIniVigencia());
        }
        if (dto.getFecFinVigencia() != null) {
            entity.setFecFinVigencia(dto.getFecFinVigencia());
        }
        if (dto.getBlnActivo() != null) {
            entity.setBlnActivo(dto.getBlnActivo());
        }
        if (dto.getIdeClasifTaxonomica() != null) {
            entity.setIdeClasifTaxonomica(dto.getIdeClasifTaxonomica());
        }
        if (dto.getFuncionZootecnica() != null) {
            entity.setFuncionZootecnica(dto.getFuncionZootecnica());
        }
        if (dto.getIdEspecie() != null) {
            entity.setIdEspecie(
                    catEspecieRepository.findById(dto.getIdEspecie())
                            .orElseThrow(() -> new RuntimeException("CatEspecie no encontrada: " + dto.getIdEspecie()))
            );
        }

        CatVidaSilvestre saved = catVidaSilvestreRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public List<CatEspecie> listEspeciesActivas() {
        return catEspecieRepository.findByBlnActivoTrue();
    }

    private CatVidaSilvestreDTO mapToDTO(CatVidaSilvestre entity) {
        return CatVidaSilvestreDTO.builder()
                .id(entity.getId())
                .ideTipoVidaSilvestre(entity.getIdeTipoVidaSilvestre())
                .idEspecie(entity.getIdEspecie() != null ? entity.getIdEspecie().getId() : null)
                .descEspecie(entity.getIdEspecie() != null ? entity.getIdEspecie().getDescEspecie() : null)
                .descNombreComun(entity.getDescNombreComun())
                .descNombreCientifico(entity.getDescNombreCientifico())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .ideClasifTaxonomica(entity.getIdeClasifTaxonomica())
                .funcionZootecnica(entity.getFuncionZootecnica())
                .build();
    }
}
