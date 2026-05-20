package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * <b>Class:</b> CatControlReferencia.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de control de referencia.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 19 de mayo del 2026
 * @version 1.0
 * @category Entidad
 */
@Getter
@Setter
@Entity
@Table(name = "cat_control_referencia")
public class CatControlReferencia {

    /** Identificador del control de referencia. */
    @Id
    @Column(name = "id_control_referencia", nullable = false)
    private Integer idControlReferencia;

    /** Tipo de presentacion. */
    @Size(max = 20)
    @Column(name = "ide_tipo_presentacion", length = 20)
    private String ideTipoPresentacion;

    /** Subtipo de presentacion. */
    @Size(max = 20)
    @Column(name = "ide_subtipo_presentacion", length = 20)
    private String ideSubtipoPresentacion;

    /** Cantidad minima. */
    @NotNull
    @Column(name = "minimo", nullable = false)
    private Short minimo;

    /** Cantidad maxima. */
    @NotNull
    @Column(name = "maximo", nullable = false)
    private Short maximo;

    /** Cantidad de presentacion. */
    @NotNull
    @Column(name = "cantidad_presentacion", nullable = false, precision = 10, scale = 3)
    private BigDecimal cantidadPresentacion;

    /** Tamanio de la muestra. */
    @Column(name = "tamanio_muestra", precision = 10, scale = 3)
    private BigDecimal tamanioMuestra;

    /** Fecha de inicio de vigencia. */
    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    /** Indicador de activo. */
    @Column(name = "bln_activo")
    private Boolean blnActivo;
}
