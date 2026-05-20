package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * <b>Class:</b> CatIdentificadorPrevalidador.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de identificadores de prevalidador.</p>
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
@Table(name = "cat_identificador_prevalidador")
public class CatIdentificadorPrevalidador {

    /** Identificador del identificador de prevalidador. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idIdentificadorPrevalidador;

    /** Caracter de identificacion. */
    @NotNull
    @Size(max = 1)
    @Column(name = "caracter_identificacion", nullable = false, length = 1)
    private String caracterIdentificacion;

    /** Fecha de inicio de vigencia. */
    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    /** Indicador de utilizado. */
    @NotNull
    @Column(name = "bln_utilizado", nullable = false)
    private Boolean blnUtilizado;

    /** Indicador de activo. */
    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;
}
