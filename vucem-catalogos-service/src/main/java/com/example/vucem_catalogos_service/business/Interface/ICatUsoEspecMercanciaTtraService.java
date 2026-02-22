package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatUsoEspecMercanciaTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatUsoEspecMercanciaTtraService {
    PageResponseDTO<CatUsoEspecMercanciaTtraDTO> list(String search, Pageable pageable);
    CatUsoEspecMercanciaTtraDTO findById(Short id);
    CatUsoEspecMercanciaTtraDTO create(CatUsoEspecMercanciaTtraDTO dto);
    CatUsoEspecMercanciaTtraDTO update(Short id, CatUsoEspecMercanciaTtraDTO dto);
}
