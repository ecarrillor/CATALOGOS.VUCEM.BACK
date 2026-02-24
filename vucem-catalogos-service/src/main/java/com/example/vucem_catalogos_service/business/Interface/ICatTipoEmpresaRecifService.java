package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatTipoEmpresaRecifDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatTipoEmpresaRecifService {

    PageResponseDTO<CatTipoEmpresaRecifDTO> list(String search, Pageable pageable);

    CatTipoEmpresaRecifDTO findById(String cveTipoEmpresaRecif);

    CatTipoEmpresaRecifDTO create(CatTipoEmpresaRecifDTO dto);

    CatTipoEmpresaRecifDTO update(String cveTipoEmpresaRecif, CatTipoEmpresaRecifDTO dto);
}
