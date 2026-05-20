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
 * <b>Class:</b> CatFundamentoTtra.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de fundamentos ttra.</p>
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
@Table(name = "cat_fundamento_ttra")
public class CatFundamentoTtra {

    /** Identificador del fundamento ttra. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fundamento_ttra", nullable = false)
    private Short idFundamentoTtra;

    /** Tipo de fundamento ttra. */
    @Size(max = 20)
    @Column(name = "ide_tipo_fundamento_ttra", length = 20)
    private String ideTipoFundamentoTtra;

    /** Descripcion del fundamento. */
    @Size(max = 512)
    @Column(name = "desc_fundamento", length = 512)
    private String descFundamento;

    /** Descripcion del contenido del fundamento. */
    @Size(max = 2000)
    @Column(name = "desc_contenido_fundamento", length = 2000)
    private String descContenidoFundamento;

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

    /** Tipo de tramite asociado. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_tramite", referencedColumnName = "id_tipo_tramite")
    private CatTipoTramite idTipoTramite;
}
