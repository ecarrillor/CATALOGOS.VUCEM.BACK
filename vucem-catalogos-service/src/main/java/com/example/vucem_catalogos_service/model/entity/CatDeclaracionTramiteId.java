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
public class CatDeclaracionTramiteId implements Serializable {
    private static final long serialVersionUID = 5219094496808664433L;
    @Size(max = 20)
    @NotNull
    @Column(name = "cve_declaracion", nullable = false, length = 20)
    private String cveDeclaracion;

    @NotNull
    @Column(name = "id_tipo_tramite", nullable = false)
    private Integer idTipoTramite;


}