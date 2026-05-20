package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * <b>Class:</b> CatCriterioOrigen.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de criterios de origen.</p>
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
@Table(name = "cat_criterio_origen")
public class CatCriterioOrigen {

    /** Clave del criterio de origen. */
    @Id
    @Size(max = 20)
    @Column(name = "cve_criterio_origen", nullable = false, length = 20)
    private String cveCriterioOrigen;

    /** Nombre del criterio de origen. */
    @Size(max = 50)
    @Column(name = "nombre", length = 50)
    private String nombre;

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
}
