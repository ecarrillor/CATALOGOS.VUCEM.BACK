package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatTratadoBloqueDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatTratadoBloqueService {

    PageResponseDTO<CatTratadoBloqueDTO> list(String search, Pageable pageable);

    CatTratadoBloqueDTO findById(Short idTratadoAcuerdo, Short idBloque);

    CatTratadoBloqueDTO create(CatTratadoBloqueDTO dto);

    CatTratadoBloqueDTO update(Short idTratadoAcuerdo, Short idBloque, CatTratadoBloqueDTO dto);
}
