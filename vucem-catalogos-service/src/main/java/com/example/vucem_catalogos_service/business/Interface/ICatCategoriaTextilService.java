package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CategoriaTextil.CatCategoriaTextilRequestDTO;
import com.example.vucem_catalogos_service.model.dto.CategoriaTextil.CatCategoriaTextilResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatCategoriaTextilService {
    PageResponseDTO<CatCategoriaTextilResponseDTO> listarCategoriaTextil(String search, Pageable pageable);

    CatCategoriaTextilResponseDTO crearCategoriaTextil(CatCategoriaTextilRequestDTO dto);

    CatCategoriaTextilResponseDTO findById(Long id);

    CatCategoriaTextilResponseDTO update(Long id, CatCategoriaTextilRequestDTO dto);

    List<SelectDTO> listadoUnidadMedida();

}
