package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatPartidaFraccion;
import mx.gob.sat.catalogo.model.entity.CatPartidaFraccionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para el catalogo de partida fraccion.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Repository
public interface CatPartidaFraccionRepository extends JpaRepository<CatPartidaFraccion, CatPartidaFraccionId> {
}
