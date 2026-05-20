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
 * <b>Class:</b> CatEntidad.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA que representa el catalogo de entidades federativas.</p>
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
@Table(name = "cat_entidad")
public class CatEntidad {

    /** Clave de la entidad (PK, max 6 caracteres). */
    @Id
    @Size(max = 6)
    @Column(name = "cve_entidad", nullable = false, length = 6)
    private String cveEntidad;

    /** Nombre de la entidad. */
    @Size(max = 120)
    @Column(name = "nombre", length = 120)
    private String nombre;

    /** Fecha de captura del registro. */
    @Column(name = "fec_captura")
    private Instant fecCaptura;

    /** Fecha de fin de vigencia. */
    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    /** Codigo de entidad IDC. */
    @Size(max = 2)
    @Column(name = "cod_entidad_idc", length = 2)
    private String codEntidadIdc;

    /** Fecha de inicio de vigencia. */
    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    /** Indica si el registro esta activo. */
    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    /** Pais asociado a la entidad. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_pais")
    private CatPais cvePais;
}
