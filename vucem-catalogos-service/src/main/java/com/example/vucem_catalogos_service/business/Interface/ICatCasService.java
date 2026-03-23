package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatCas.CatCaRequestDTO;
import com.example.vucem_catalogos_service.model.dto.CatCas.CatCaResponseDTO;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatCasService {

    PageResponseDTO<CatCaResponseDTO> listAll(String search, Long idTipoTramite, String sortBy, String sortDir, Pageable pageable);

    CatCaResponseDTO crear(CatCaRequestDTO dto, Long idTipoTramite);

    CatCaResponseDTO findById(Short id);

    CatCaResponseDTO update(Short id, CatCaRequestDTO dto);

    List<ClasifProductoTraDTO> listadoTipoTramite();

    ClasifProductoTraDTO lastCas();
}
