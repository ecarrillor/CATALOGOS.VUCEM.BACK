package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatUnidadAdminAduanaDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatUnidadAdminAduanaService {

    PageResponseDTO<CatUnidadAdminAduanaDTO> list(String search, String sortBy, String sortDir, Pageable pageable);

    CatUnidadAdminAduanaDTO findById(String cveUnidadAdministrativa, String cveAduana);

    CatUnidadAdminAduanaDTO create(CatUnidadAdminAduanaDTO dto);

    CatUnidadAdminAduanaDTO update(String cveUnidadAdministrativa, String cveAduana, CatUnidadAdminAduanaDTO dto);
}
