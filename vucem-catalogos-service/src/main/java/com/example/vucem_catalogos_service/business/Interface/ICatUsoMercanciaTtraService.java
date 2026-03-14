package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatUsoMercanciaTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatUsoMercanciaTtraService {

    PageResponseDTO<CatUsoMercanciaTtraDTO> list(String search, Pageable pageable);

    CatUsoMercanciaTtraDTO findById(Short id);

    CatUsoMercanciaTtraDTO create(CatUsoMercanciaTtraDTO dto);

    CatUsoMercanciaTtraDTO update(Short id, CatUsoMercanciaTtraDTO dto);
}
