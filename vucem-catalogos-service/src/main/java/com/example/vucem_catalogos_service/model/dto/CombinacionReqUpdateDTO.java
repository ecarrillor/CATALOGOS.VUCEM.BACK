package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CombinacionReqUpdateDTO {

    List<CombinacionReqUpdateResponseDTO> especie;
    List<CombinacionReqUpdateResponseDTO> zootecnica;
    List<CombinacionReqUpdateResponseDTO> mercancia;
    List<CombinacionReqUpdateResponseDTO> tipoProd;
}
