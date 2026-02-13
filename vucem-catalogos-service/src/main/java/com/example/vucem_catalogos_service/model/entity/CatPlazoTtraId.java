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
public class CatPlazoTtraId implements Serializable {
    private static final long serialVersionUID = 4634884882715768991L;
    @NotNull
    @Column(name = "id_tipo_tramite", nullable = false)
    private Integer idTipoTramite;

    @Size(max = 20)
    @NotNull
    @Column(name = "ide_plazo_vigencia", nullable = false, length = 20)
    private String idePlazoVigencia;


}
