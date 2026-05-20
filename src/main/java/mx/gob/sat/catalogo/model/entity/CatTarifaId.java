package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Embeddable
public class CatTarifaId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "id_tipo_tramite", nullable = false)
    private Long idTipoTramite;

    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "ide_tipo_tarifa", nullable = false, length = 20)
    private String ideTipoTarifa;
}
