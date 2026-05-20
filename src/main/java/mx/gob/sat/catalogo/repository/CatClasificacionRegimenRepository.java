package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatClasificacionRegimen;
import mx.gob.sat.catalogo.model.entity.CatClasificacionRegimenId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para el catalogo de clasificacion de regimen.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Repository
public interface CatClasificacionRegimenRepository extends JpaRepository<CatClasificacionRegimen, CatClasificacionRegimenId> {
}
