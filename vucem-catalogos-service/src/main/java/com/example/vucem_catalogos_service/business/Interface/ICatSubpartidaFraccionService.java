package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatSubpartidaFraccionDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectCatPartidaFraccion;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatSubpartidaFraccionService {

    PageResponseDTO<CatSubpartidaFraccionDTO> list(String search, Pageable pageable);

    CatSubpartidaFraccionDTO findById(String cveSubpartidaFraccion, String cveCapituloFraccion, String cvePartidaFraccion);

    CatSubpartidaFraccionDTO create(CatSubpartidaFraccionDTO dto);

    CatSubpartidaFraccionDTO update(String cveSubpartidaFraccion, String cveCapituloFraccion, String cvePartidaFraccion, CatSubpartidaFraccionDTO dto);


    List<SelectDTO> listadoCapituloFraccion();

    List<SelectCatPartidaFraccion> listadoPartidaFracciobn(String capitulo);
}
