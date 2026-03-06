package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatTratadoBloquePaiMasivoRequestDTO;
import com.example.vucem_catalogos_service.model.dto.CatTratadoBloquePaiRequestDTO;
import com.example.vucem_catalogos_service.model.dto.CatTratadoBloquePaiResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.persistence.repo.ICatPaisRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTratadoAcuerdoRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTratadoBloquePaiRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatTratadoBloquePaiService {

    PageResponseDTO<CatTratadoBloquePaiResponseDTO> list(String cvePais, Short idTratadoAcuerdo, Boolean blnActivo, Pageable pageable);

    CatTratadoBloquePaiResponseDTO findById(String cvePais, Short idTratadoAcuerdo);

    List<CatTratadoBloquePaiResponseDTO> createMasivo(CatTratadoBloquePaiMasivoRequestDTO dto);

    CatTratadoBloquePaiResponseDTO update(String cvePais, Short idTratadoAcuerdo, CatTratadoBloquePaiRequestDTO dto);

    void delete(String cvePais, Short idTratadoAcuerdo);

    List<ICatPaisRepository.ComboProyeccion> listadoPaises();

    List<ICatTratadoAcuerdoRepository.ComboProyeccion> listadoTratados();

    List<ICatTratadoBloquePaiRepository.ComboProyeccion> paisesGuardadosByTratados(List<Short> idsTratado);

    List<ICatTratadoBloquePaiRepository.ComboProyeccion> tratadosGuardadosByPaises(List<String> clavePaises);
}
