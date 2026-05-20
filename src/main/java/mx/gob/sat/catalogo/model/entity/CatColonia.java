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

import java.time.LocalDate;

/**
 * <b>Class:</b> CatColonia.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de colonias.</p>
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
@Table(name = "cat_colonia")
public class CatColonia {

    /** Clave de la colonia. */
    @Id
    @Size(max = 12)
    @Column(name = "cve_colonia", nullable = false, length = 12)
    private String cveColonia;

    /** Nombre de la colonia. */
    @Size(max = 120)
    @Column(name = "nombre", length = 120)
    private String nombre;

    /** Fecha de captura. */
    @Column(name = "fec_captura")
    private LocalDate fecCaptura;

    /** Fecha de inicio de vigencia. */
    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    /** Codigo postal. */
    @Size(max = 12)
    @Column(name = "cp", length = 12)
    private String cp;

    /** Codigo de colonia SAT. */
    @Size(max = 10)
    @Column(name = "sat_colony_cd", length = 10)
    private String satColonyCd;

    /** Indicador de activo. */
    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    /** Delegacion o municipio asociada. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_deleg_mun", referencedColumnName = "cve_deleg_mun")
    private CatDelegMun cveDelegMun;

    /** Localidad asociada. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_localidad", referencedColumnName = "cve_localidad")
    private CatLocalidad cveLocalidad;
}
