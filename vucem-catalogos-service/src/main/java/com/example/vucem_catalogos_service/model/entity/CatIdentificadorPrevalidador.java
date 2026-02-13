package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "cat_identificador_prevalidador")
public class CatIdentificadorPrevalidador {
    @Id
    @Column(name = "id_identificador_prevalidador", nullable = false)
    private Long id;

    @Size(max = 1)
    @NotNull
    @Column(name = "caracter_identificacion", nullable = false, length = 1)
    private String caracterIdentificacion;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_utilizado", nullable = false)
    private Short blnUtilizado;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}
