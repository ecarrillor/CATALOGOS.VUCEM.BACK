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
public class CatUnidadAdminAduanaId implements Serializable {
    private static final long serialVersionUID = 5154231812198098948L;
    @Size(max = 10)
    @NotNull
    @Column(name = "cve_unidad_administrativa", nullable = false, length = 10)
    private String cveUnidadAdministrativa;

    @Size(max = 3)
    @NotNull
    @Column(name = "cve_aduana", nullable = false, length = 3)
    private String cveAduana;


}