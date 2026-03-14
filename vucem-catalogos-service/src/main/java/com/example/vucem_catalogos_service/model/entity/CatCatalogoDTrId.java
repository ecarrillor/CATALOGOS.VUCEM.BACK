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
public class CatCatalogoDTrId implements Serializable {
    private static final long serialVersionUID = -8897794768196687981L;
    @Size(max = 10)
    @NotNull
    @Column(name = "cve_catalogo", nullable = false, length = 10)
    private String cveCatalogo;

    @Size(max = 2)
    @NotNull
    @Column(name = "cve_lenguaje", nullable = false, length = 2)
    private String cveLenguaje;


}