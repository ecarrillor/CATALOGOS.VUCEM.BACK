package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "cat_documento_tramite")
public class CatDocumentoTramite {

    @EmbeddedId
    private CatDocumentoTramiteId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_doc", referencedColumnName = "id", insertable = false, updatable = false)
    private CatTipoDocumento idTipoDoc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_tramite", referencedColumnName = "id_tipo_tramite", insertable = false, updatable = false)
    private CatTipoTramite idTipoTramite;

    @Column(name = "bln_especifico")
    private Boolean blnEspecifico;

    @Size(max = 20)
    @Column(name = "ide_clasificacion_documento", length = 20)
    private String ideClasificacionDocumento;

    @Size(max = 20)
    @Column(name = "ide_tipo_solicitante_rfe", length = 20)
    private String ideTipoSolicitanteRfe;

    @Column(name = "fec_ini_vigencia")
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @Column(name = "bln_activo")
    private Boolean blnActivo;

    @Column(name = "bln_solo_anexar")
    private Boolean blnSoloAnexar;

    @Size(max = 20)
    @Column(name = "ide_regla_anexado", length = 20)
    private String ideReglaAnexado;
}
