package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatVidaSilvestreDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatEspecie;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatVidaSilvestreService {

    PageResponseDTO<CatVidaSilvestreDTO> list(String search, Pageable pageable);

    CatVidaSilvestreDTO findById(Integer id);

    CatVidaSilvestreDTO create(CatVidaSilvestreDTO dto);

    CatVidaSilvestreDTO update(Integer id, CatVidaSilvestreDTO dto);

    List<CatEspecie> listEspeciesActivas();
}
