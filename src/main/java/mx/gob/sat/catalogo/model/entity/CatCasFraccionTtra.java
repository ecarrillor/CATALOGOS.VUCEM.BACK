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

@Getter
@Setter
@Entity
@Table(name = "cat_cas_fraccion_ttra")
public class CatCasFraccionTtra {

    @Id
    @Column(name = "id_cas_fraccion_ttra", nullable = false)
    private Short idCasFraccionTtra;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cas", nullable = false, referencedColumnName = "id")
    private CatCas idCas;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cve_fraccion", nullable = false, referencedColumnName = "cve_fraccion")
    private CatFraccionArancelaria cveFraccion;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipo_tramite", nullable = false, referencedColumnName = "id")
    private CatTipoTramite idTipoTramite;

    @NotNull
    @Column(name = "bln_rotterdam", nullable = false)
    private Boolean blnRotterdam;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bl_activo", nullable = false)
    private Boolean blActivo;

    @Size(max = 2000)
    @Column(name = "desc_fraccion_alt", length = 2000)
    private String descFraccionAlt;

    @Column(name = "cvn_wasser")
    private Short cvnWasser;

    @Column(name = "cvn_armas")
    private Short cvnArmas;

    @Column(name = "cvn_montreal")
    private Short cvnMontreal;

    @Column(name = "cvn_estocolmo")
    private Short cvnEstocolmo;

    @Column(name = "forma_desc")
    private Short formaDesc;

    @Size(max = 20)
    @Column(name = "ide_identificador_regla", length = 20)
    private String ideIdentificadorRegla;
}
