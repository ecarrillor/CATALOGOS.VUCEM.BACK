package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatPaisTratadoAcuerdoRequestDTO;
import com.example.vucem_catalogos_service.model.dto.CatPaisTratadoAcuerdoResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.persistence.repo.ICatPaisRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTratadoAcuerdoRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatPaisTratadoAcuerdoService {

    PageResponseDTO<CatPaisTratadoAcuerdoResponseDTO> list(String cvePais, Short idTratadoAcuerdo, Boolean blnActivo, Pageable pageable);

    CatPaisTratadoAcuerdoResponseDTO findById(String cvePais, Short idTratadoAcuerdo);

    CatPaisTratadoAcuerdoResponseDTO create(CatPaisTratadoAcuerdoRequestDTO dto);

    CatPaisTratadoAcuerdoResponseDTO update(String cvePais, Short idTratadoAcuerdo, CatPaisTratadoAcuerdoRequestDTO dto);



    List<ICatPaisRepository.ComboProyeccion> listadoPaises();

}
