package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "cat_documento_tramite")
public class CatDocumentoTramite {
    @EmbeddedId
    private CatDocumentoTramiteId id;

    @MapsId("idTipoDoc")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipo_doc", nullable = false)
    private CatTipoDocumento idTipoDoc;

    @MapsId("idTipoTramite")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipo_tramite", nullable = false)
    private CatTipoTramite idTipoTramite;

    @Column(name = "bln_especifico")
    private Boolean blnEspecifico;

    @Size(max = 20)
    @Column(name = "ide_clasificacion_documento", length = 20)
    private String ideClasificacionDocumento;

    @Size(max = 20)
    @Column(name = "ide_tipo_solicitante_rfe", length = 20)
    private String ideTipoSolicitanteRfe;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Column(name = "bln_solo_anexar")
    private Boolean blnSoloAnexar;

    @Size(max = 20)
    @Column(name = "ide_regla_anexado", length = 20)
    private String ideReglaAnexado;


}