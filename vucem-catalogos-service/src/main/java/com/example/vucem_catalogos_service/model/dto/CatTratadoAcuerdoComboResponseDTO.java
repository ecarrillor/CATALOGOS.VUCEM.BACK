package com.example.vucem_catalogos_service.model.dto;

import com.example.vucem_catalogos_service.persistence.repo.ICatTratadoBloquePaiRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatTratadoAcuerdoComboResponseDTO {

    private List<ICatTratadoBloquePaiRepository.ComboProyeccion> tratadosGuardados;
    private List<ICatTratadoBloquePaiRepository.ComboProyeccion> tratadosNoGuardados;
}
