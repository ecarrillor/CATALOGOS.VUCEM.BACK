package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "cat_declaracion_tramite")
public class CatDeclaracionTramite {

    @EmbeddedId
    private CatDeclaracionTramiteId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_declaracion", referencedColumnName = "cve_declaracion", insertable = false, updatable = false)
    private CatDeclaracion cveDeclaracion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_tramite", referencedColumnName = "id_tipo_tramite", insertable = false, updatable = false)
    private CatTipoTramite idTipoTramite;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;
}
