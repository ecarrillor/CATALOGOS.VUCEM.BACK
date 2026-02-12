package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
@Entity
@Table(name = "cat_leyenda_texto")
public class CatLeyendaTexto {
    @Id
    @Column(name = "id_leyenda", nullable = false)
    private Integer id;

    @Size(max = 20)
    @NotNull
    @Column(name = "ide_tipo_leyenda_texto", nullable = false, length = 20)
    private String ideTipoLeyendaTexto;

    @Column(name = "num_anio")
    private Short numAnio;

    @Size(max = 2000)
    @NotNull
    @Column(name = "leyenda", nullable = false, length = 2000)
    private String leyenda;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}