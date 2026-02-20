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
public class CatClasificacionRegimanId implements Serializable {
    private static final long serialVersionUID = -3176933530637366052L;
    @Size(max = 2)
    @NotNull
    @Column(name = "cve_clasificacion_regimen", nullable = false, length = 2)
    private String cveClasificacionRegimen;

    @Size(max = 2)
    @NotNull
    @Column(name = "cve_regimen", nullable = false, length = 2)
    private String cveRegimen;


}