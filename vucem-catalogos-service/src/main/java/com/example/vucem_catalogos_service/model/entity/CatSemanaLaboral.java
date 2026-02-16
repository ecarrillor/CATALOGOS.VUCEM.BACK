package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "cat_semana_laboral")
public class CatSemanaLaboral {
    @Id
    @Column(name = "id_semana_laboral", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    @Column(name = "bln_activo")
    private Boolean blnActivo;


}