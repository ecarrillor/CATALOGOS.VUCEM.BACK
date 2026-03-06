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
public class CatPaisesComboResponseDTO {

    private List<ICatTratadoBloquePaiRepository.ComboProyeccion> paisesGuardados;
    private List<ICatTratadoBloquePaiRepository.ComboProyeccion> paisesNoGuardados;
}
