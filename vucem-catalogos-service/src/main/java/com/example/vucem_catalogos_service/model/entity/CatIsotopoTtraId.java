package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@Embeddable
public class CatIsotopoTtraId implements Serializable {
    private static final long serialVersionUID = -1831628837425637981L;
    @NotNull
    @Column(name = "id_isotopo", nullable = false)
    private Short idIsotopo;

    @NotNull
    @Column(name = "id_tipo_tramite", nullable = false)
    private Integer idTipoTramite;


}