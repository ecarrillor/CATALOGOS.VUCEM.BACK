package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatVidaSilvestreService;
import com.example.vucem_catalogos_service.model.dto.CatVidaSilvestreDTO;
import com.example.vucem_catalogos_service.model.dto.CatVidaSilvestreResponseDTO;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatEspecie;
import com.example.vucem_catalogos_service.model.entity.CatVidaSilvestre;
import com.example.vucem_catalogos_service.persistence.repo.ICatEspecieRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatVidaSilvestreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class CatVidaSilvestreServiceImpl implements ICatVidaSilvestreService {

    @Autowired
    private ICatVidaSilvestreRepository catVidaSilvestreRepository;

    @Autowired
    private ICatEspecieRepository catEspecieRepository;

    @Override
    public PageResponseDTO<CatVidaSilvestreDTO> list(String search, Long tipo, Pageable pageable) {
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
    public CatVidaSilvestreDTO create(CatVidaSilvestreResponseDTO dto, Long tipo) {
        if (catVidaSilvestreRepository.existsById(dto.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }

        if (catVidaSilvestreRepository
                .existsByDescNombreComunAndDescNombreCientifico(
                        dto.getDescNombreComun(),
                        dto.getDescNombreCientifico())) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ya existe un registro con el mismo nombre común y nombre científico"
            );
        }
        CatVidaSilvestre entity = new CatVidaSilvestre();
        String tipoVida = obtenerTipoVidaPorTipoTramite(dto.getIdTipoTramite());

        entity.setIdeTipoVidaSilvestre(tipoVida);

        entity.setId(dto.getId());
        //entity.setIdeTipoVidaSilvestre(dto.getIdeTipoVidaSilvestre());
        entity.setDescNombreComun(dto.getDescNombreComun());
        entity.setDescNombreCientifico(dto.getDescNombreCientifico());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatVidaSilvestre saved = catVidaSilvestreRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatVidaSilvestreDTO update(Integer id, CatVidaSilvestreDTO dto) {
        CatVidaSilvestre entity = catVidaSilvestreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatVidaSilvestre no encontrado con id: " + id));


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


        CatVidaSilvestre saved = catVidaSilvestreRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public List<CatEspecie> listEspeciesActivas() {
        return catEspecieRepository.findByBlnActivoTrue();
    }

    @Override
    public List<ClasifProductoTraDTO> listadoTipoTramite() {
        return catVidaSilvestreRepository.listadoTipoTramite();
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


    private String obtenerTipoVidaPorTipoTramite(Long tipo) {

        if (List.of(220101L,220201L,221601L).contains(tipo)) return "TIVS.SGIZ";
        if (List.of(220102L,220402L).contains(tipo)) return "TIVS.SGF";
        if (List.of(220202L,221602L).contains(tipo)) return "TIVS.SGFC";
        if (List.of(230101L,230201L,230202L,250101L,250102L,250103L).contains(tipo)) return "TIVS.SEM";
        if (List.of(230901L,230903L).contains(tipo)) return "TIVS.SEMVS";
        if (List.of(230902L,230903L).contains(tipo)) return "TIVS.SEMCI";

        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "El idTipoTramite no corresponde a ningún tipo de vida silvestre"
        );
    }
}
