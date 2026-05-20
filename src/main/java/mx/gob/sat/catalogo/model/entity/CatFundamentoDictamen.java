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

import java.time.LocalDate;

/**
 * <b>Class:</b> CatFundamentoDictamen.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de fundamentos de dictamen.</p>
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
@Table(name = "cat_fundamento_dictamen")
public class CatFundamentoDictamen {

    /** Identificador del fundamento de dictamen. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idFundamentoDictamen;

    /** Descripcion del fundamento de dictamen. */
    @Size(max = 2000)
    @Column(name = "descripcion", length = 2000)
    private String descripcion;

    /** Indicador de sentido del fundamento. */
    @Column(name = "bln_sentido_fundamento")
    private Boolean blnSentidoFundamento;

    /** Fecha de inicio de vigencia. */
    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;
}
