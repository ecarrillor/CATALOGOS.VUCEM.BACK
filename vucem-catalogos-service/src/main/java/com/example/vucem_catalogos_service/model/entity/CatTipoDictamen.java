package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "cat_tipo_dictamen")
public class CatTipoDictamen {
    @Id
    @Column(name = "id_tipo_dictamen", nullable = false)
    private Short id;

    @Size(max = 120)
    @Column(name = "nombre", length = 120)
    private String nombre;

    @Column(name = "orden")
    private Short orden;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;



}
