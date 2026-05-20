package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Embeddable
public class CatEquivalenciaAelcId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "cve_moneda", nullable = false, length = 3)
    private String cveMoneda;
}
