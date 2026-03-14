package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class CatTratadoBloqueId implements Serializable {
    private static final long serialVersionUID = -1140992368906891595L;
    @NotNull
    @Column(name = "id_tratado_acuerdo", nullable = false)
    private Short idTratadoAcuerdo;

    @NotNull
    @Column(name = "id_bloque", nullable = false)
    private Short idBloque;


}
