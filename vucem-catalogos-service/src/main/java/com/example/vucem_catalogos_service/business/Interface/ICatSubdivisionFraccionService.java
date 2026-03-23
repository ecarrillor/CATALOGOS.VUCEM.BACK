package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatSubdivisionFraccionDTO;
import com.example.vucem_catalogos_service.model.dto.FraccionAranceSearchDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatSubdivisionFraccionService {

    PageResponseDTO<CatSubdivisionFraccionDTO> list(String search, String sortBy, String sortDir, Pageable pageable);

    CatSubdivisionFraccionDTO findById(String cveSubdivision);

    CatSubdivisionFraccionDTO create(CatSubdivisionFraccionDTO dto);

    CatSubdivisionFraccionDTO update(String cveSubdivision, CatSubdivisionFraccionDTO dto);

    List<FraccionAranceSearchDTO> listadoArancelariaById(String term);
}
