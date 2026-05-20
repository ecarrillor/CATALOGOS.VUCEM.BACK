package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
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

    @Size(max = 2)
    @Column(name = "cve_subpartida_fraccion", length = 2)
    private String cveSubpartidaFraccion;

    @Size(max = 2)
    @Column(name = "cve_capitulo_fraccion", length = 2)
    private String cveCapituloFraccion;

    @Size(max = 2)
    @Column(name = "cve_partida_fraccion", length = 2)
    private String cvePartidaFraccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "cve_subpartida_fraccion", referencedColumnName = "cve_subpartida_fraccion", insertable = false, updatable = false),
        @JoinColumn(name = "cve_capitulo_fraccion", referencedColumnName = "cve_capitulo_fraccion", insertable = false, updatable = false),
        @JoinColumn(name = "cve_partida_fraccion", referencedColumnName = "cve_partida_fraccion", insertable = false, updatable = false)
    })
    private CatSubpartidaFraccion catSubpartidaFraccion;

    @Column(name = "fec_captura")
    private LocalDate fecCaptura;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @Size(max = 2)
    @Column(name = "capitulo", length = 2)
    private String capitulo;

    @Size(max = 4)
    @Column(name = "partida", length = 4)
    private String partida;

    @Size(max = 6)
    @Column(name = "sub_partida", length = 6)
    private String subPartida;

    @Size(max = 32)
    @Column(name = "cve_usuario", length = 32)
    private String cveUsuario;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Column(name = "bln_anexo_28")
    private Boolean blnAnexo28;

    @Column(name = "bln_decreto_immex")
    private Boolean blnDecretoImmex;
}
