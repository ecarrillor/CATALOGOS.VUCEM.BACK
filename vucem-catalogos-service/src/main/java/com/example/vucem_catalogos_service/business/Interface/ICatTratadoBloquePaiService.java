package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.*;
import com.example.vucem_catalogos_service.persistence.repo.ICatPaisRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTratadoAcuerdoRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTratadoBloquePaiRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatTratadoBloquePaiService {

    PageResponseDTO<CatTratadoBloquePaiResponseDTO> list(String cvePais, Short idTratadoAcuerdo, Boolean blnActivo, String search, Pageable pageable);

    CatTratadoBloquePaiResponseDTO findById(String cvePais, Short idTratadoAcuerdo);

    List<CatTratadoBloquePaiResponseDTO> createMasivo(CatTratadoBloquePaiMasivoRequestDTO dto);

    CatTratadoBloquePaiResponseDTO update(String cvePais, Short idTratadoAcuerdo, CatTratadoBloquePaiRequestDTO dto);

    void delete(String cvePais, Short idTratadoAcuerdo);

    List<ICatPaisRepository.ComboProyeccion> listadoPaises();

    List<ICatTratadoAcuerdoRepository.ComboProyeccion> listadoTratados();

    CatPaisesComboResponseDTO paisesComboByTratados(List<Short> idsTratado);

    CatTratadoAcuerdoComboResponseDTO tratadosGuardadosByPaises(List<String> clavePaises);
}
