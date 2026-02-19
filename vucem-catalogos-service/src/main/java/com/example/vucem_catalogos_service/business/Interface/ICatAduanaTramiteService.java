package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.AduanaTramite.CatAduanaTramiteRequestDTO;
import com.example.vucem_catalogos_service.model.dto.AduanaTramite.CatAduanaTramiteResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatAduanaTramiteService {
    PageResponseDTO<CatAduanaTramiteResponseDTO> listarAduanaTramite(String search, Pageable pageable);

    CatAduanaTramiteResponseDTO crearAduanaTramite(CatAduanaTramiteRequestDTO dto);

    CatAduanaTramiteResponseDTO findById(Long id);

    CatAduanaTramiteResponseDTO update(Long id, CatAduanaTramiteRequestDTO dto);

    List<SelectDTO> listadoTiposTramite();
}
