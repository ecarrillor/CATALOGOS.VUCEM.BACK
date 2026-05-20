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
 * <b>Class:</b> CatTratadoAcuerdo.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de tratados y acuerdos.</p>
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
@Table(name = "cat_tratado_acuerdo")
public class CatTratadoAcuerdo {

    /** Identificador del tratado o acuerdo. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Short idTratadoAcuerdo;

    /** Identificador de tipo de tratado o acuerdo. */
    @Size(max = 20)
    @Column(name = "ide_tipo_tratado_acuerdo", length = 20)
    private String ideTipoTratadoAcuerdo;

    /** Clave del tratado o acuerdo. */
    @NotNull
    @Size(max = 20)
    @Column(name = "cve_tratado_acuerdo", nullable = false, length = 20)
    private String cveTratadoAcuerdo;

    /** Nombre del tratado o acuerdo. */
    @Size(max = 250)
    @Column(name = "nombre", length = 250)
    private String nombre;

    /** Indicador PEXIM. */
    @Column(name = "bln_pexim")
    private Boolean blnPexim;

    /** Fecha de captura. */
    @Column(name = "fec_captura")
    private LocalDate fecCaptura;

    /** Fecha de fin de vigencia. */
    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    /** Identificador de tipo de cupo SAAI. */
    @Size(max = 20)
    @Column(name = "ide_tipo_cupo_saai", length = 20)
    private String ideTipoCupoSaai;

    /** Fecha de inicio de vigencia. */
    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    /** Indicador de activo. */
    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    /** Indicador de evaluacion individual. */
    @Column(name = "bln_evaluar_individual")
    private Boolean blnEvaluarIndividual;
}
