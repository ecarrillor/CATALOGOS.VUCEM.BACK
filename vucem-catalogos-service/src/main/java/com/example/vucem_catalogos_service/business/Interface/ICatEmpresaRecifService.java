package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatEmpresaRecifDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatEmpresaRecifService {
    PageResponseDTO<CatEmpresaRecifDTO> list(String search, Pageable pageable);
    CatEmpresaRecifDTO findById(String recif);
    CatEmpresaRecifDTO create(CatEmpresaRecifDTO dto);
    CatEmpresaRecifDTO update(String recif, CatEmpresaRecifDTO dto);
    List<SelectDTO> listadoUnidadesAdmin();
}
