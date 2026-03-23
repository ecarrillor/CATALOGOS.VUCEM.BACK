package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.Especie.CatEspecieRequestDTO;
import com.example.vucem_catalogos_service.model.dto.Especie.CatEspecieResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatEspecieService {
    PageResponseDTO<CatEspecieResponseDTO> listarEspecie(String search, Long tipo, String sortBy, String sortDir, Pageable pageable);

    CatEspecieResponseDTO crearEspecie(CatEspecieRequestDTO dto, Long tipo);

    CatEspecieResponseDTO findById(Integer id);

    CatEspecieResponseDTO update(Integer id, CatEspecieRequestDTO dto);

    List<ClasifProductoTraDTO> listadoTipoTramite();

    ClasifProductoTraDTO lastEspecie();
}
