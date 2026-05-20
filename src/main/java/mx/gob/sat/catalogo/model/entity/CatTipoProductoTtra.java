package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
 * <b>Class:</b> CatTipoProductoTtra.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de tipos de producto ttra.</p>
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
@Table(name = "cat_tipo_producto_ttra")
public class CatTipoProductoTtra {

    /** Identificador del tipo de producto ttra. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_producto_ttra", nullable = false)
    private Short idTipoProductoTtra;

    /** Descripcion del tipo de producto. */
    @Size(max = 250)
    @Column(name = "desc_tipo_producto", length = 250)
    private String descTipoProducto;

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

    /** Identificador del tipo de certificado de mercancias. */
    @Size(max = 20)
    @Column(name = "ide_tipo_certificado_merc", length = 20)
    private String ideTipoCertificadoMerc;

    /** Tipo de tramite asociado. */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipo_tramite", nullable = false, referencedColumnName = "id_tipo_tramite")
    private CatTipoTramite idTipoTramite;
}
