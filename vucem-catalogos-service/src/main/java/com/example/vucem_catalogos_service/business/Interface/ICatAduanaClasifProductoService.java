package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.AduanaClasifProducto.CatAduanaClasifProdRequestDTO;
import com.example.vucem_catalogos_service.model.dto.AduanaClasifProducto.CatAduanaClasifProdResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatAduanaClasifProd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatAduanaClasifProductoService {

    PageResponseDTO<CatAduanaClasifProdResponseDTO> catAduanaListAll(String search, Pageable pageable);

    CatAduanaClasifProdResponseDTO crearAduanaClasifProducto(CatAduanaClasifProdRequestDTO aduanaClasifProd);

    CatAduanaClasifProdResponseDTO findById(Long id);

    CatAduanaClasifProdResponseDTO update(Long id, CatAduanaClasifProdRequestDTO dto);

    List<SelectDTO> listadoAduana();

    List<SelectDTO> listadoClasificacionProducto();
}
