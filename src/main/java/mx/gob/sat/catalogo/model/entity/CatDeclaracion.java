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
 * <b>Class:</b> CatDeclaracion.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de declaraciones.</p>
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
@Table(name = "cat_declaracion")
public class CatDeclaracion {

    /** Clave de la declaracion. */
    @Id
    @Size(max = 20)
    @Column(name = "cve_declaracion", nullable = false, length = 20)
    private String cveDeclaracion;

    /** Descripcion de la declaracion. */
    @Size(max = 2000)
    @Column(name = "desc_declaracion", length = 2000)
    private String descDeclaracion;

    /** Fecha de captura. */
    @Column(name = "fec_captura")
    private Instant fecCaptura;

    /** Fecha de fin de vigencia. */
    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    /** Fecha de inicio de vigencia. */
    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    /** Indicador de activo. */
    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    /** Clave de referencia. */
    @Size(max = 20)
    @Column(name = "cve_referencia", length = 20)
    private String cveReferencia;
}
