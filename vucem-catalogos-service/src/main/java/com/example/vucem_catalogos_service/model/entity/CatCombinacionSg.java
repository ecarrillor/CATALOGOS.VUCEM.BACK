package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

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
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @Column(name = "bln_activo")
    private Boolean blnActivo;

    @Size(max = 20)
    @Column(name = "ide_tipo_certificado_merc", length = 20)
    private String ideTipoCertificadoMerc;
    @OneToMany
    @JoinColumn(name = "id_combinacion_sg")
    private Set<VucDatosGeneralesSol> vucDatosGeneralesSols = new LinkedHashSet<>();


}