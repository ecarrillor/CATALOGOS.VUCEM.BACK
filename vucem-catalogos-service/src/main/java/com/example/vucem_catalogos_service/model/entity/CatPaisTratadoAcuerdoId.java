package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Data
@EqualsAndHashCode
@Embeddable
public class CatPaisTratadoAcuerdoId implements Serializable {
    private static final long serialVersionUID = -568964018412796896L;
    @NotNull
    @Column(name = "fec_fin_vigencia", nullable = false)
    private Instant fecFinVigencia;

    @Size(max = 3)
    @NotNull
    @Column(name = "cve_pais", nullable = false, length = 3)
    private String cvePais;

    @NotNull
    @Column(name = "id_tratado_acuerdo", nullable = false)
    private Short idTratadoAcuerdo;


}