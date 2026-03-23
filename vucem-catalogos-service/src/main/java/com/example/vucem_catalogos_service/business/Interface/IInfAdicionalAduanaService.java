package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.InfAdicionalAduanaDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IInfAdicionalAduanaService {
    PageResponseDTO<InfAdicionalAduanaDTO> list(String search, String sortBy, String sortDir, Pageable pageable);
    InfAdicionalAduanaDTO findById(String cveAduana);
    InfAdicionalAduanaDTO create(InfAdicionalAduanaDTO dto);
    InfAdicionalAduanaDTO update(String cveAduana, InfAdicionalAduanaDTO dto);
    List<SelectDTO> listadoAduana();
}
