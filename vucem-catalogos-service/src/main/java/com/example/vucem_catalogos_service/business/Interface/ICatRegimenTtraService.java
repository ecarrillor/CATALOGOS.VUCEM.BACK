package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatRegimenTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatRegimenTtra;
import com.example.vucem_catalogos_service.model.entity.CatTipoTramite;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatRegimenTtraService {
    PageResponseDTO<CatRegimenTtraDTO> list(String search, String sortBy, String sortDir, Pageable pageable);
    CatRegimenTtraDTO findById(Short id);
    CatRegimenTtraDTO create(CatRegimenTtraDTO dto);
    CatRegimenTtraDTO update(Short id, CatRegimenTtraDTO dto);

    List<SelectDTO> listTipoTramite();

    List<SelectDTO> listRegimen();
}
