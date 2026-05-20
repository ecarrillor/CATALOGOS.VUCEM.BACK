package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatSubpartidaFraccion;
import mx.gob.sat.catalogo.model.entity.CatSubpartidaFraccionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para el catalogo de subpartida fraccion.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Repository
public interface CatSubpartidaFraccionRepository extends JpaRepository<CatSubpartidaFraccion, CatSubpartidaFraccionId> {
}
