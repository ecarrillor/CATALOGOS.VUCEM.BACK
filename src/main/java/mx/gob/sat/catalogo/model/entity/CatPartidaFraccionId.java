package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;

/**
 * Clave compuesta para el catalogo de partida fraccion.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Data
@Embeddable
public class CatPartidaFraccionId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "cve_capitulo_fraccion", nullable = false, length = 2)
    private String cveCapituloFraccion;

    @Column(name = "cve_partida_fraccion", nullable = false, length = 2)
    private String cvePartidaFraccion;
}
