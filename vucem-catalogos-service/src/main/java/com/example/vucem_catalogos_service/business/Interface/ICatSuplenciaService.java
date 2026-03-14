package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatSuplenciaDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatSuplenciaService {
    PageResponseDTO<CatSuplenciaDTO> list(String search, Pageable pageable);
    CatSuplenciaDTO findById(Short id);
    CatSuplenciaDTO create(CatSuplenciaDTO dto);
    CatSuplenciaDTO update(Short id, CatSuplenciaDTO dto);
    List<SelectDTO> listadoDependencias();
}
