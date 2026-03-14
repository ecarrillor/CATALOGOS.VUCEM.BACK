package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.FundamentoDictamen.CatFundamentoDictamenRequestDTO;
import com.example.vucem_catalogos_service.model.dto.FundamentoDictamen.CatFundamentoDictamenResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatFundamentoDictamenService {

    PageResponseDTO<CatFundamentoDictamenResponseDTO> listarFundamentoDictamen(String search, Pageable pageable);

    CatFundamentoDictamenResponseDTO crearFundamentoDictamen(CatFundamentoDictamenRequestDTO dto);

    CatFundamentoDictamenResponseDTO findById(Long id);

    CatFundamentoDictamenResponseDTO update(Long id, CatFundamentoDictamenRequestDTO dto);
}
