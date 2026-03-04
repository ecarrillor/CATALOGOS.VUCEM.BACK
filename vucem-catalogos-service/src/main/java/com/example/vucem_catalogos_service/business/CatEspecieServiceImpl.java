package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatEspecieService;
import com.example.vucem_catalogos_service.model.dto.CategoriaTextil.CatCategoriaTextilResponseDTO;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.Especie.CatEspecieRequestDTO;
import com.example.vucem_catalogos_service.model.dto.Especie.CatEspecieResponseDTO;
import com.example.vucem_catalogos_service.model.dto.LeyendaTexto.CatLeyendaTextoRequestDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatCategoriaTextil;
import com.example.vucem_catalogos_service.model.entity.CatEspecie;
import com.example.vucem_catalogos_service.model.entity.CatVidaSilvestre;
import com.example.vucem_catalogos_service.persistence.repo.ICatEspecieRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatVidaSilvestreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class CatEspecieServiceImpl implements ICatEspecieService {

    @Autowired
    private ICatEspecieRepository iCatEspecieRepository;

    @Autowired
    private ICatVidaSilvestreRepository iCatVidaSilvestreRepository;

    @Override
    public PageResponseDTO<CatEspecieResponseDTO> listarEspecie(String search, Long tipo, Pageable pageable) {
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

        Page<CatEspecieResponseDTO> page =
                iCatEspecieRepository.search(texto, activo, tipo,pageable);

        return PageResponseDTO.<CatEspecieResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatEspecieResponseDTO crearEspecie(CatEspecieRequestDTO dto, Long tipo) {
        if (iCatEspecieRepository.existsById(dto.getNumEspecie())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }

        validarTipoTramite(tipo);
        CatEspecie entity = new CatEspecie();
        entity.setId(dto.getNumEspecie());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setDescEspecie(dto.getDescripcionEspecie());
        entity.setBlnActivo(dto.getBlnActivo());

        CatEspecie savedEspecie = iCatEspecieRepository.save(entity);

        String tipoVida = obtenerTipoVidaPorTipoTramite(tipo);

        CatVidaSilvestre vida = new CatVidaSilvestre();
        vida.setIdeTipoVidaSilvestre(tipoVida);
        vida.setIdEspecie(savedEspecie);

        vida.setDescNombreComun("PENDIENTE");
        vida.setDescNombreCientifico("PENDIENTE");
        vida.setFecIniVigencia(dto.getFecIniVigencia());
        vida.setBlnActivo(true);

        iCatVidaSilvestreRepository.save(vida);

        return CatEspecieResponseDTO.builder()
                .numEspecie(savedEspecie.getId())
                .descripcionEspecie(savedEspecie.getDescEspecie())
                .fecIniVigencia(savedEspecie.getFecIniVigencia())
                .fecFinVigencia(savedEspecie.getFecFinVigencia())
                .blnActivo(savedEspecie.getBlnActivo())
                .build();
    }

    @Override
    public CatEspecieResponseDTO findById(Integer id) {
        CatEspecie entity = iCatEspecieRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Registro no encontrado con id: " + id
                        )
                );

        return CatEspecieResponseDTO.builder()
                .numEspecie(entity.getId())
                .descripcionEspecie(entity.getDescEspecie())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }

    @Override
    public CatEspecieResponseDTO update(Integer id, CatEspecieRequestDTO dto) {
        CatEspecie entity = iCatEspecieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));


        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setDescEspecie(dto.getDescripcionEspecie());
        entity.setBlnActivo(dto.getBlnActivo());

        CatEspecie updated = iCatEspecieRepository.save(entity);

        return CatEspecieResponseDTO.builder()
                .numEspecie(updated.getId())
                .descripcionEspecie(updated.getDescEspecie())
                .fecIniVigencia(updated.getFecIniVigencia())
                .fecFinVigencia(updated.getFecFinVigencia())
                .blnActivo(updated.getBlnActivo())
                .build();
    }

    @Override
    public List<ClasifProductoTraDTO> listadoTipoTramite() {
        return iCatEspecieRepository.listadoTipoTramite();
    }

    private void  validarTipoTramite(Long tipo) {

        if (List.of(220101L,220201L,221601L).contains(tipo)) return;
        if (List.of(220102L,220402L).contains(tipo)) return;
        if (List.of(220202L,221602L).contains(tipo)) return;
        if (List.of(230101L,230201L,230202L,250101L,250102L,250103L).contains(tipo)) return;
        if (List.of(230901L,230903L).contains(tipo)) return;
        if (List.of(230902L,230903L).contains(tipo)) return;

        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "El idTipoTramite no corresponde a ningún tipo válido"
        );
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
                "El idTipoTramite no corresponde a ningún tipo válido"
        );
    }
}
