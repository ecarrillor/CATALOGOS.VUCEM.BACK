package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatAprobCertSeRequestDTO;
import com.example.vucem_catalogos_service.model.dto.CatAprobCertSeResponseDTO;
import com.example.vucem_catalogos_service.model.dto.CatUnidadAdministrativaNameDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatAprobCertService {
    PageResponseDTO<CatAprobCertSeResponseDTO> list(String search, Pageable pageable);

    CatAprobCertSeResponseDTO findById(Short id);

    CatAprobCertSeResponseDTO create(CatAprobCertSeRequestDTO dto);

    CatAprobCertSeResponseDTO update(Short id, CatAprobCertSeRequestDTO dto);

}
