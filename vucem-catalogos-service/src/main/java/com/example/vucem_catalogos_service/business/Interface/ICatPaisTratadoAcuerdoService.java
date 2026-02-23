package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatPaisTratadoAcuerdoRequestDTO;
import com.example.vucem_catalogos_service.model.dto.CatPaisTratadoAcuerdoResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface ICatPaisTratadoAcuerdoService {
    PageResponseDTO<CatPaisTratadoAcuerdoResponseDTO> list(String search, Pageable of);

    CatPaisTratadoAcuerdoResponseDTO findById(String idPais, Short idTratado);

    CatPaisTratadoAcuerdoResponseDTO create(CatPaisTratadoAcuerdoRequestDTO dto);

    CatPaisTratadoAcuerdoResponseDTO update(String idPais, Short idTratado, CatPaisTratadoAcuerdoRequestDTO dto);
}
