package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Embeddable
public class CatDiaNoLaborableId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "fec_no_laborable", nullable = false)
    private LocalDate fecNoLaborable;

    @Column(name = "cve_calendario", nullable = false, length = 4)
    private String cveCalendario;
}
