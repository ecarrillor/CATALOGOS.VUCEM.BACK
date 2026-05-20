package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatMoneda.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA que representa el catalogo de monedas.</p>
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
@Table(name = "cat_moneda")
public class CatMoneda {

    /** Clave de la moneda (PK, max 3 caracteres). */
    @Id
    @Size(max = 3)
    @Column(name = "cve_moneda", nullable = false, length = 3)
    private String cveMoneda;

    /** Nombre de la moneda. */
    @Size(max = 120)
    @Column(name = "nombre", length = 120)
    private String nombre;

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
}
