package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatAprobCertService {
    PageResponseDTO<CatAprobCertSeResponseDTO> list(String search, Pageable pageable);

    CatAprobCertSeResponseDTO findById(Short id);

    CatAprobCertSeResponseDTO create(CatAprobCertSeRequestDTO dto);

    CatAprobCertSeResponseDTO update(Short id, CatAprobCertSeRequestDTO dto);

    List<SelectDTO> listadoLaboratorio();
}
