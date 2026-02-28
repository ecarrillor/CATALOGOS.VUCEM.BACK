package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "cat_cas")
public class CatCa {
    @Id
    @Column(name = "id_cas", nullable = false)
    private Short id;

    @Size(max = 50)
    @NotNull
    @Column(name = "desc_cas", nullable = false, length = 50)
    private String descCas;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bl_activo", nullable = false)
    private Boolean blActivo;


}