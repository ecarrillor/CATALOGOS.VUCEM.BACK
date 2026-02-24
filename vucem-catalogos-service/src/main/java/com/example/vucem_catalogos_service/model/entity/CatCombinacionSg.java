package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "cat_combinacion_sg")
public class CatCombinacionSg {
    @Id
    @Column(name = "id_combinacion_sg", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cvc_especie")
    private CatCatalogoD cvcEspecie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cvc_funcion_zootecnica")
    private CatCatalogoD cvcFuncionZootecnica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cvc_nombre_comun")
    private CatCatalogoD cvcNombreComun;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cvc_tipo_producto")
    private CatCatalogoD cvcTipoProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_pais")
    private CatPais cvePais;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @Column(name = "bln_activo")
    private Short blnActivo;

    @Size(max = 20)
    @Column(name = "ide_tipo_certificado_merc", length = 20)
    private String ideTipoCertificadoMerc;


}