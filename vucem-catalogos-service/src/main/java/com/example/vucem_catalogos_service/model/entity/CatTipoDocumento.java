package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "cat_tipo_documento")
public class CatTipoDocumento {
    @Id
    @Column(name = "id_tipo_doc", nullable = false)
    private Short id;

    @Size(max = 2000)
    @Column(name = "nombre", length = 2000)
    private String nombre;

    @Column(name = "fec_captura")
    private Instant fecCaptura;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Size(max = 20)
    @Column(name = "ide_rango_resolucion_imagen", length = 20)
    private String ideRangoResolucionImagen;

    @Column(name = "tamanio_maximo")
    private Short tamanioMaximo;


}