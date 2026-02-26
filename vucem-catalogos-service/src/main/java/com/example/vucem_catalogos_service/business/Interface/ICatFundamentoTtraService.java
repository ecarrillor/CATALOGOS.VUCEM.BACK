package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatFundamentoTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatFundamentoTtraService {
    PageResponseDTO<CatFundamentoTtraDTO> list(String search, Pageable pageable);
    CatFundamentoTtraDTO findById(Short id);
    CatFundamentoTtraDTO create(CatFundamentoTtraDTO dto);
    CatFundamentoTtraDTO update(Short id, CatFundamentoTtraDTO dto);
}
