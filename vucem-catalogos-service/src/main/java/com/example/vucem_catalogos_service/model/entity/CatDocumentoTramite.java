package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
@Entity
@Table(name = "cat_documento_tramite")
public class CatDocumentoTramite {
    @EmbeddedId
    private CatDocumentoTramiteId id;

    @Column(name = "bln_especifico")
    private Short blnEspecifico;

    @Size(max = 20)
    @Column(name = "ide_clasificacion_documento", length = 20)
    private String ideClasificacionDocumento;

    @Size(max = 20)
    @Column(name = "ide_tipo_solicitante_rfe", length = 20)
    private String ideTipoSolicitanteRfe;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Column(name = "bln_solo_anexar")
    private Short blnSoloAnexar;

    @Size(max = 20)
    @Column(name = "ide_regla_anexado", length = 20)
    private String ideReglaAnexado;


}