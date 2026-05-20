package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;

/**
 * Clave compuesta para el catalogo de clasificacion de regimen.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Data
@Embeddable
public class CatClasificacionRegimenId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "cve_clasificacion_regimen", nullable = false, length = 2)
    private String cveClasificacionRegimen;

    @Column(name = "cve_regimen", nullable = false, length = 2)
    private String cveRegimen;
}
