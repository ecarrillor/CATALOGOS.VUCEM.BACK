package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatUnidadMedida.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de unidades de medida.</p>
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
@Table(name = "cat_unidad_medida")
public class CatUnidadMedida {

    /** Clave de la unidad de medida. */
    @Id
    @Size(max = 10)
    @Column(name = "cve_unidad_medida", nullable = false, length = 10)
    private String cveUnidadMedida;

    /** Descripcion de la unidad de medida. */
    @Size(max = 100)
    @Column(name = "descripcion", length = 100)
    private String descripcion;

    /** Fecha de captura. */
    @Column(name = "fec_captura")
    private LocalDate fecCaptura;

    /** Fecha de fin de vigencia. */
    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    /** Fecha de inicio de vigencia. */
    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    /** Indicador de activo. */
    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    /** Identificador de origen de unidad de medida. */
    @Size(max = 20)
    @Column(name = "ide_origen_unidad_medida", length = 20)
    private String ideOrigenUnidadMedida;

    /** Sigla de la unidad de medida. */
    @Size(max = 50)
    @Column(name = "sigla", length = 50)
    private String sigla;

    /** Identificador OMA. */
    @Size(max = 10)
    @Column(name = "id_oma", length = 10)
    private String idOma;

    /** Sigla CBP. */
    @Size(max = 50)
    @Column(name = "sigla_cbp", length = 50)
    private String siglaCbp;
}
