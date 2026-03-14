package com.example.vucem_catalogos_service.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SelectDTO {

    private String cve;
    private String nombre;

    private Long id;

}
