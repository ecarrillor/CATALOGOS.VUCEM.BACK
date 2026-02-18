package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "cat_clasif_producto")
public class CatClasifProducto {

    @Id
    @Column(name = "id_clasif_producto", nullable = false)
    private Long idClasifProduct;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Size(max = 500)
    @Column(name = "nombre", length = 500)
    private String nombre;

    @Size(max = 20)
    @Column(name = "ide_tipo_clasif_producto", length = 20)
    private String ideTipoClasifProducto;



}
