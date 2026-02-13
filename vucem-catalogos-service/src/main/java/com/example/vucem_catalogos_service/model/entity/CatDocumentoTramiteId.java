package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@Embeddable
public class CatDocumentoTramiteId implements Serializable {
    private static final long serialVersionUID = -5140269353744234380L;
    @NotNull
    @Column(name = "id_tipo_doc", nullable = false)
    private Short idTipoDoc;

    @NotNull
    @Column(name = "id_tipo_tramite", nullable = false)
    private Integer idTipoTramite;


}
