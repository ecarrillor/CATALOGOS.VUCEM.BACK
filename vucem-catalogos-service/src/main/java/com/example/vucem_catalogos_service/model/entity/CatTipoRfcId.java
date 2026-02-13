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
public class CatTipoRfcId implements Serializable {
    private static final long serialVersionUID = -2712771512596037874L;
    @Size(max = 13)
    @NotNull
    @Column(name = "rfc", nullable = false, length = 13)
    private String rfc;

    @Size(max = 20)
    @NotNull
    @Column(name = "ide_tipo_rfc", nullable = false, length = 20)
    private String ideTipoRfc;


}
