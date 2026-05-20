package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * <b>Class:</b> CatCatalogoD.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de catalogos D (detalle).</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Entidad
 */
@Getter
@Setter
@Entity
@Table(name = "cat_catalogo_d")
public class CatCatalogoD {

    /** Clave del catalogo D. */
    @Id
    @Size(max = 10)
    @Column(name = "cve_catalogo", nullable = false, length = 10)
    private String cveCatalogo;

    /** Codigo generico 1. */
    @Size(max = 100)
    @Column(name = "codigo_generico1", length = 100)
    private String codigoGenerico1;

    /** Codigo generico 2. */
    @Size(max = 100)
    @Column(name = "codigo_generico2", length = 100)
    private String codigoGenerico2;

    /** Descripcion generica 1. */
    @Size(max = 1000)
    @Column(name = "desc_generica1", length = 1000)
    private String descGenerica1;

    /** Descripcion generica 2. */
    @Size(max = 300)
    @Column(name = "desc_generica2", length = 300)
    private String descGenerica2;

    /** Numero generico 1. */
    @Column(name = "num_generico1")
    private Long numGenerico1;

    /** Numero generico 2. */
    @Column(name = "num_generico2")
    private Long numGenerico2;

    /** Fecha de inicio de vigencia. */
    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    /** Catalogo H asociado. */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_catalogo_h", referencedColumnName = "cve_catalogo_h", nullable = false)
    private CatCatalogoH cveCatalogoH;

    /** Catalogo D relacionado (auto-referencia). */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_catalogo_r", referencedColumnName = "cve_catalogo")
    private CatCatalogoD cveCatalogoR;
}
