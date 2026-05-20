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

import java.time.LocalDate;

/**
 * <b>Class:</b> CatFraccionHtsUsa.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de fracciones HTS USA.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Entidad
 */
@Getter
@Setter
@Entity
@Table(name = "cat_fraccion_hts_usa")
public class CatFraccionHtsUsa {

    /** Identificador de la fraccion HTS USA (PK). */
    @Id
    @Column(name = "id_fraccion_hts_usa", nullable = false)
    private Long idFraccionHtsUsa;

    /** Clave de la fraccion HTS USA. */
    @NotNull
    @Size(max = 10)
    @Column(name = "cve_fraccion_hts_usa", nullable = false, length = 10)
    private String cveFraccionHtsUsa;

    /** Descripcion de la fraccion HTS USA. */
    @Size(max = 1000)
    @Column(name = "descripcion", length = 1000)
    private String descripcion;

    /** Fecha de captura. */
    @Column(name = "fec_captura")
    private LocalDate fecCaptura;

    /** Fecha de inicio de vigencia. */
    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    /** Identificador de tipo de bien fraccion. */
    @Size(max = 20)
    @Column(name = "ide_tipo_bien_fraccion", length = 20)
    private String ideTipoBienFraccion;

    /** Indicador de exenta. */
    @Column(name = "bln_exenta")
    private Boolean blnExenta;

    /** Indicador de activo. */
    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    /** Unidad de medida asociada. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_unidad_medida")
    private CatUnidadMedida cveUnidadMedida;
}
