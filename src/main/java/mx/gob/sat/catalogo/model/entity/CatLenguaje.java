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
 * <b>Class:</b> CatLenguaje.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de lenguajes.</p>
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
@Table(name = "cat_lenguaje")
public class CatLenguaje {

    /** Clave del lenguaje. */
    @Id
    @Size(max = 2)
    @Column(name = "cve_lenguaje", nullable = false, length = 2)
    private String cveLenguaje;

    /** Nombre del lenguaje. */
    @NotNull
    @Size(max = 128)
    @Column(name = "nombre", nullable = false, length = 128)
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
