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
 * <b>Class:</b> CatPais.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA que representa el catalogo de paises.</p>
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
@Table(name = "cat_pais")
public class CatPais {

    /** Clave del pais (PK, max 3 caracteres). */
    @Id
    @Size(max = 3)
    @Column(name = "cve_pais", nullable = false, length = 3)
    private String cvePais;

    /** Nombre del pais. */
    @Size(max = 120)
    @Column(name = "nombre", length = 120)
    private String nombre;

    /** Moneda asociada al pais. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_moneda", referencedColumnName = "cve_moneda")
    private CatMoneda cveMoneda;

    /** Clave del pais en el formato WCO. */
    @Size(max = 2)
    @Column(name = "cve_pais_wco", length = 2)
    private String cvePaisWco;

    /** Fecha de captura del registro. */
    @Column(name = "fec_captura")
    private LocalDate fecCaptura;

    /** Fecha de fin de vigencia. */
    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    /** Fecha de inicio de vigencia. */
    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    /** Indica si el registro esta activo. */
    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    /** Indica si el pais tiene restriccion. */
    @Column(name = "bln_restriccion")
    private Boolean blnRestriccion;

    /** Nombre alterno del pais. */
    @Size(max = 120)
    @Column(name = "nombre_alterno", length = 120)
    private String nombreAlterno;
}
