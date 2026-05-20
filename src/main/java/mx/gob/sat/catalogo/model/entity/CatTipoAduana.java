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
 * <b>Class:</b> CatTipoAduana.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA que representa el catalogo de tipos de aduana.</p>
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
@Table(name = "cat_tipo_aduana")
public class CatTipoAduana {

    /** Clave del tipo de aduana (PK, max 2 caracteres). */
    @Id
    @Size(max = 2)
    @Column(name = "cve_tipo_aduana", nullable = false, length = 2)
    private String cveTipoAduana;

    /** Nombre del tipo de aduana. */
    @Size(max = 30)
    @Column(name = "nombre", length = 30)
    private String nombre;

    /** Fecha de inicio de vigencia. */
    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    /** Indica si el registro esta activo. */
    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;
}
