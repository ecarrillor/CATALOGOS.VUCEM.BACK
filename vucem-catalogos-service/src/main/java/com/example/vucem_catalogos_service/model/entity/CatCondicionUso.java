package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
@Entity
@Table(name = "cat_condicion_uso")
public class CatCondicionUso {
    @Id
    @Column(name = "id_condicion_uso", nullable = false)
    private Short id;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "descripcion", nullable = false, length = Integer.MAX_VALUE)
    private String descripcion;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @NotNull
    @Column(name = "descripcion_html", nullable = false, length = Integer.MAX_VALUE)
    private String descripcionHtml;


}