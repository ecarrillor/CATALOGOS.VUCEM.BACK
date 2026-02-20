package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatNormalOficialDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatNormalOficialService {
    PageResponseDTO<CatNormalOficialDTO> list(String search, Pageable pageable);
    CatNormalOficialDTO findById(Integer id);
    CatNormalOficialDTO create(CatNormalOficialDTO dto);
    CatNormalOficialDTO update(Integer id, CatNormalOficialDTO dto);
    List<CatNormalOficialDTO> listActivos();
}
