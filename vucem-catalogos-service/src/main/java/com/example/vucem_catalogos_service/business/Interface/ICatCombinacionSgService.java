package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatCombinacionSgService {
    PageResponseDTO<CatCombinacionSgDTO> list(String search, String sortBy, String sortDir, Pageable pageable);
    CatCombinacionSgDTO findById(Long id);
    CatCombinacionSgDTO create(CatCombinacionSgDTO dto);
    CatCombinacionSgDTO update(Long id, CatCombinacionSgDTO dto);

    CombinacionReqResponseDTO listTipoCertificado(String id);

    CombinacionReqUpdateDTO listUpdateTipoCertificado(String id, String catalogo);
}
