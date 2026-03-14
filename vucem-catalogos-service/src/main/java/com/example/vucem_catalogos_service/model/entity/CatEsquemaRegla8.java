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
@Table(name = "cat_esquema_regla8")
public class CatEsquemaRegla8 {
    @Id
    @Column(name = "id_esquema_regla8", nullable = false)
    private Short id;

    @Size(max = 2000)
    @Column(name = "nombre", length = 2000)
    private String nombre;

    @Column(name = "desc_requisito", length = Integer.MAX_VALUE)
    private String descRequisito;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}
