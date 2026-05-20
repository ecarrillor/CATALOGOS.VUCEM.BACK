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
 * <b>Class:</b> CatLocalidad.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de localidades.</p>
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
@Table(name = "cat_localidad")
public class CatLocalidad {

    /** Clave de la localidad. */
    @Id
    @Size(max = 12)
    @Column(name = "cve_localidad", nullable = false, length = 12)
    private String cveLocalidad;

    /** Nombre de la localidad. */
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

    /** Codigo de localidad SAT. */
    @Size(max = 10)
    @Column(name = "sat_town_code", length = 10)
    private String satTownCode;

    /** Indicador de activo. */
    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    /** Delegacion o municipio asociado. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_deleg_mun", referencedColumnName = "cve_deleg_mun")
    private CatDelegMun cveDelegMun;
}
