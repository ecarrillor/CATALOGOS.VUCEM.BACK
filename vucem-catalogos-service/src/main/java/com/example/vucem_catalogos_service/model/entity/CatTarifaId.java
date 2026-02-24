package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class CatTarifaId implements Serializable {
    private static final long serialVersionUID = 720432690186451710L;
    @NotNull
    @Column(name = "id_tipo_tramite", nullable = false)
    private Long idTipoTramite;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Size(max = 20)
    @NotNull
    @Column(name = "ide_tipo_tarifa", nullable = false, length = 20)
    private String ideTipoTarifa;


}
