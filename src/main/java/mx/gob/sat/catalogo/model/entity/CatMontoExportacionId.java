package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Embeddable
public class CatMontoExportacionId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "rfc_exportador", nullable = false, length = 20)
    private String rfcExportador;

    @Column(name = "fec_monto_exportacion", nullable = false)
    private LocalDate fecMontoExportacion;
}
