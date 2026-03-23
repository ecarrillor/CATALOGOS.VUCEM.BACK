package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatRecintoFiscalizadoDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatRecintoFiscalizadoService {

    PageResponseDTO<CatRecintoFiscalizadoDTO> list(String search, int page, int size, String sortBy, String sortDir);

    CatRecintoFiscalizadoDTO findById(Long id);

    CatRecintoFiscalizadoDTO create(CatRecintoFiscalizadoDTO dto);

    CatRecintoFiscalizadoDTO update(Long id, CatRecintoFiscalizadoDTO dto);
}
