package mx.gob.sat.commonmodel.model.common.catalogo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Stub local de AduanaEntity. Reemplazar con libreria common-model en CI/CD.
 * Entidad JPA del catalogo de aduanas del modelo comun SAT.
 */
@Getter
@Setter
@Entity
@Table(name = "cat_aduana_example")
public class AduanaEntity {

    /** Identificador numerico del registro de aduana. */
    @Id
    @Column(name = "id")
    private Integer id;

    /** Clave de la aduana. */
    @Column(name = "cve_aduana")
    private String cveAduana;

    /** Nombre de la aduana. */
    @Column(name = "nombre")
    private String nombre;
}
