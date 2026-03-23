package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatClasifToxicologicaTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatClasifToxicologicaTtraService {

    PageResponseDTO<CatClasifToxicologicaTtraDTO> list(String search, String sortBy, String sortDir, Pageable pageable);

    CatClasifToxicologicaTtraDTO findById(Short id);

    CatClasifToxicologicaTtraDTO create(CatClasifToxicologicaTtraDTO dto);

    CatClasifToxicologicaTtraDTO update(Short id, CatClasifToxicologicaTtraDTO dto);
}
