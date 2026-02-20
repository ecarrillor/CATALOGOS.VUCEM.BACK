package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatColoniaService {
    PageResponseDTO<CatColoniaDTO> list(String search, Pageable pageable);
    CatColoniaDTO findById(String cveColonia);
    CatColoniaDTO create(CatColoniaDTO dto);
    CatColoniaDTO update(String cveColonia, CatColoniaDTO dto);
    List<CatColoniaDTO> findByCp(String cp);

    List<CatMuncipioDTO> findMunicipios(String cvePais, String cveEntidad);

    List<LocalidadDTO> findLocalidades(String cvePais, String cveEntidad, String cveMunicipio);
}
