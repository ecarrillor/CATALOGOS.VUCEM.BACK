package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
@Entity
@Table(name = "cat_fraccion_arancelaria")
public class CatFraccionArancelaria {
    @Id
    @Size(max = 8)
    @Column(name = "cve_fraccion", nullable = false, length = 8)
    private String cveFraccion;

    @Size(max = 2000)
    @Column(name = "descripcion", length = 2000)
    private String descripcion;

    @JoinColumns({
            @JoinColumn(name = "cve_subpartida_fraccion",
                    referencedColumnName = "cve_subpartida_fraccion"),
            @JoinColumn(name = "cve_capitulo_fraccion",
                    referencedColumnName = "cve_capitulo_fraccion"),
            @JoinColumn(name = "cve_partida_fraccion",
                    referencedColumnName = "cve_partida_fraccion")})
    @ManyToOne(fetch = FetchType.LAZY)
    private CatSubpartidaFraccion catSubpartidaFraccion;

    @Column(name = "fec_captura")
    private Instant fecCaptura;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @Size(max = 2)
    @Column(name = "capitulo", length = 2)
    private String capitulo;

    @Size(max = 4)
    @Column(name = "partida", length = 4)
    private String partida;

    @Size(max = 6)
    @Column(name = "sub_partida", length = 6)
    private String subPartida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_usuario", referencedColumnName = "cve_usuario")
    private InfUsuario cveUsuario;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Column(name = "bln_anexo_28")
    private Short blnAnexo28;

    @Column(name = "bln_decreto_immex")
    private Short blnDecretoImmex;


}