package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class CatUnidadAdminVecinaId implements Serializable {
    private static final long serialVersionUID = -3775284865236203171L;
    @Size(max = 10)
    @NotNull
    @Column(name = "cve_unidad_administrativa", nullable = false, length = 10)
    private String cveUnidadAdministrativa;

    @Size(max = 6)
    @NotNull
    @Column(name = "cve_entidad", nullable = false, length = 6)
    private String cveEntidad;


}