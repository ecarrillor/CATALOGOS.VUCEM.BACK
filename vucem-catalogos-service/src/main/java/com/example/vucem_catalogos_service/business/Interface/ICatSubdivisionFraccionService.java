package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatSubdivisionFraccionDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatSubdivisionFraccionService {

    PageResponseDTO<CatSubdivisionFraccionDTO> list(String search, Pageable pageable);

    CatSubdivisionFraccionDTO findById(String cveSubdivision);

    CatSubdivisionFraccionDTO create(CatSubdivisionFraccionDTO dto);

    CatSubdivisionFraccionDTO update(String cveSubdivision, CatSubdivisionFraccionDTO dto);
}
