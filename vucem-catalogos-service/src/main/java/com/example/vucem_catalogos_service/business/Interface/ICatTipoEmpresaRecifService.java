package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatTipoEmpresaRecifDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatTipoEmpresaRecif;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatTipoEmpresaRecifService {

    PageResponseDTO<CatTipoEmpresaRecifDTO> list(String search, String sortBy, String sortDir, Pageable pageable);

    CatTipoEmpresaRecifDTO findById(String cveTipoEmpresaRecif);

    CatTipoEmpresaRecifDTO create(CatTipoEmpresaRecifDTO dto);

    CatTipoEmpresaRecifDTO update(String cveTipoEmpresaRecif, CatTipoEmpresaRecifDTO dto);

    List<CatTipoEmpresaRecif> listTipoEmpresaRecif();
}
