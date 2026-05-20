package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class CatDeclaracionTramiteId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "cve_declaracion", nullable = false, length = 20)
    private String cveDeclaracion;

    @Column(name = "id_tipo_tramite", nullable = false)
    private Long idTipoTramite;
}
