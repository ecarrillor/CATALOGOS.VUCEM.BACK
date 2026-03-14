package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatTratadoAcuerdoDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatTratadoAcuerdoService {

    PageResponseDTO<CatTratadoAcuerdoDTO> list(String search, Pageable pageable);

    CatTratadoAcuerdoDTO findById(Short id);

    CatTratadoAcuerdoDTO create(CatTratadoAcuerdoDTO dto);

    CatTratadoAcuerdoDTO update(Short id, CatTratadoAcuerdoDTO dto);

    List<SelectDTO> listTratadoAcuerdo();
}
