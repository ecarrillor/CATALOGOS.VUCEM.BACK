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
 * <b>Class:</b> CatAduana.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA que representa el catalogo de aduanas.</p>
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
@Table(name = "cat_aduana")
public class CatAduana {

    /** Clave de la aduana (PK, max 3 caracteres). */
    @Id
    @Size(max = 3)
    @Column(name = "cve_aduana", nullable = false, length = 3)
    private String cveAduana;

    /** Nombre de la aduana. */
    @Size(max = 120)
    @Column(name = "nombre", length = 120)
    private String nombre;

    /** Fecha de captura del registro. */
    @Column(name = "fec_captura")
    private Instant fecCaptura;

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

    /** Correo electronico de contacto de la aduana. */
    @Size(max = 30)
    @Column(name = "correo_electronico", length = 30)
    private String correoElectronico;

    /** Tipo de aduana asociado. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_tipo_aduana", referencedColumnName = "cve_tipo_aduana")
    private CatTipoAduana tipoAduana;

    /** Entidad federativa asociada a la aduana. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_entidad", referencedColumnName = "cve_entidad")
    private CatEntidad entidad;
}
