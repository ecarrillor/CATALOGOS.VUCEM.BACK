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
 * <b>Class:</b> CatDelegMun.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de delegaciones y municipios.</p>
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
@Table(name = "cat_deleg_mun")
public class CatDelegMun {

    /** Clave de la delegacion o municipio. */
    @Id
    @Size(max = 6)
    @Column(name = "cve_deleg_mun", nullable = false, length = 6)
    private String cveDelegMun;

    /** Nombre de la delegacion o municipio. */
    @Size(max = 120)
    @Column(name = "nombre", length = 120)
    private String nombre;

    /** Fecha de captura. */
    @Column(name = "fec_captura")
    private LocalDate fecCaptura;

    /** Fecha de fin de vigencia. */
    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    /** Municipio SAT. */
    @Size(max = 5)
    @Column(name = "sat_municipality", length = 5)
    private String satMunicipality;

    /** Fecha de inicio de vigencia. */
    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    /** Indicador de activo. */
    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    /** Entidad federativa asociada. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_entidad", referencedColumnName = "cve_entidad")
    private CatEntidad cveEntidad;
}
