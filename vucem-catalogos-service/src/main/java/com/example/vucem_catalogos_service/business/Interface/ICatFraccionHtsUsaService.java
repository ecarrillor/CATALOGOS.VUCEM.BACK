package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatFraccionHtsUsaDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatFraccionHtsUsaService {
    PageResponseDTO<CatFraccionHtsUsaDTO> list(String search, Pageable pageable);
    CatFraccionHtsUsaDTO findById(Long id);
    CatFraccionHtsUsaDTO create(CatFraccionHtsUsaDTO dto);
    CatFraccionHtsUsaDTO update(Long id, CatFraccionHtsUsaDTO dto);
    List<SelectDTO> listadoUnidadesMedida();
}
