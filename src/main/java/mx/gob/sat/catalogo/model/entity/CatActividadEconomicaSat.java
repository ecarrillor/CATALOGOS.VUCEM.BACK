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
@Table(name = "cat_actividad_economica_sat")
public class CatActividadEconomicaSat {

    @Id
    @Column(name = "id_actividad_economica_sat", nullable = false)
    private Long idActividadEconomicaSat;

    @Size(max = 2000)
    @Column(name = "descripcion", length = 2000)
    private String descripcion;

    @Size(max = 2000)
    @Column(name = "desc_scian", length = 2000)
    private String descScian;

    @Size(max = 2000)
    @Column(name = "desc_notas", length = 2000)
    private String descNotas;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "fec_captura", nullable = false)
    private LocalDate fecCaptura;

    @Column(name = "fec_actualizacion")
    private LocalDate fecActualizacion;

    @Size(max = 2)
    @Column(name = "cve_tipo_industria_idc", length = 2)
    private String cveTipoIndustriaIdc;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_actividad_economica_r", referencedColumnName = "id_actividad_economica_sat")
    private CatActividadEconomicaSat idActividadEconomicaR;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_tipo_empresa_recif")
    private CatTipoEmpresaRecif cveTipoEmpresaRecif;
}
