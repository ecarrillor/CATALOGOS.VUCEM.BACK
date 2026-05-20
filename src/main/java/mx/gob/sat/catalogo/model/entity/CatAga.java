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
 * <b>Class:</b> CatAga.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de AGA (Administracion General de Aduanas).</p>
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
@Table(name = "cat_aga")
public class CatAga {

    /** Clave del parametro AGA. */
    @Id
    @Size(max = 30)
    @Column(name = "cve_parametro", nullable = false, length = 30)
    private String cveParametro;

    /** Descripcion del parametro. */
    @NotNull
    @Size(max = 200)
    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    /** Valor del parametro. */
    @NotNull
    @Size(max = 4000)
    @Column(name = "valor", nullable = false, length = 4000)
    private String valor;

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
