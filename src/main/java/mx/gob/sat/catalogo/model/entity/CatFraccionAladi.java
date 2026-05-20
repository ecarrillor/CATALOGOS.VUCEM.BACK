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
 * <b>Class:</b> CatFraccionAladi.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de fracciones ALADI.</p>
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
@Table(name = "cat_fraccion_aladi")
public class CatFraccionAladi {

    /** Identificador de la fraccion ALADI. */
    @Id
    @Column(name = "id_fraccion_aladi", nullable = false)
    private Long idFraccionAladi;

    /** Tipo de fraccion ALADI. */
    @Size(max = 20)
    @Column(name = "ide_tipo_fraccion_aladi", length = 20)
    private String ideTipoFraccionAladi;

    /** Clave de la fraccion. */
    @NotNull
    @Size(max = 8)
    @Column(name = "cve_fraccion", nullable = false, length = 8)
    private String cveFraccion;

    /** Descripcion de la fraccion ALADI. */
    @Size(max = 1000)
    @Column(name = "descripcion", length = 1000)
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
}
