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

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "cat_fraccion_ttra")
public class CatFraccionTtra {

    @Id
    @Column(name = "id_fraccion_gob", nullable = false)
    private Long idFraccionGob;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_fraccion", referencedColumnName = "cve_fraccion")
    private CatFraccionArancelaria cveFraccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_tramite", referencedColumnName = "id")
    private CatTipoTramite idTipoTramite;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Size(max = 2000)
    @Column(name = "desc_fraccion_alt", length = 2000)
    private String descFraccionAlt;

    @Size(max = 20)
    @Column(name = "ide_clasif_partida", length = 20)
    private String ideClasifPartida;

    @Column(name = "bln_fraccion_controlada")
    private Boolean blnFraccionControlada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria_textil", referencedColumnName = "id")
    private CatCategoriaTextil idCategoriaTextil;

    @Column(name = "factor_conversion", precision = 19, scale = 2)
    private BigDecimal factorConversion;

    @Column(name = "valor_equivalencia", precision = 19, scale = 2)
    private BigDecimal valorEquivalencia;

    @Size(max = 10)
    @Column(name = "cve_unidad_medida", length = 10)
    private String cveUnidadMedida;

    @Size(max = 2000)
    @Column(name = "regla_aplicable", length = 2000)
    private String reglaAplicable;
}
