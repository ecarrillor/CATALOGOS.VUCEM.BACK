package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatClasificacionRegimenDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatClasificacionRegimenService {
    PageResponseDTO<CatClasificacionRegimenDTO> list(String search, Pageable pageable);
    CatClasificacionRegimenDTO findById(String cveClasificacionRegimen, String cveRegimen);
    CatClasificacionRegimenDTO create(CatClasificacionRegimenDTO dto);
    CatClasificacionRegimenDTO update(String cveClasificacionRegimen, String cveRegimen, CatClasificacionRegimenDTO dto);
    List<SelectDTO> listadoRegimenes();
}
