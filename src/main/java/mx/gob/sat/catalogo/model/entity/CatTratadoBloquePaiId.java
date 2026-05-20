package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;

/**
 * Clave compuesta para el catalogo de tratado bloque pais.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Data
@Embeddable
public class CatTratadoBloquePaiId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "cve_pais", nullable = false, length = 3)
    private String cvePais;

    @Column(name = "id_tratado_acuerdo", nullable = false)
    private Short idTratadoAcuerdo;
}
