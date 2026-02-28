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
import java.time.LocalDate;

@Data
@Entity
@Table(name = "cat_descripcion_prod")
public class CatDescripcionProd {
    @Id
    @Column(name = "id_descripcion_prod", nullable = false)
    private Integer id;

    @Size(max = 256)
    @Column(name = "descripcion_producto", length = 256)
    private String descripcionProducto;

    @Column(name = "fec_captura")
    private LocalDate fecCaptura;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}
