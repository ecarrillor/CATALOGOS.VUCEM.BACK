package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatTratadoBloque;
import mx.gob.sat.catalogo.model.entity.CatTratadoBloqueId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para el catalogo de tratado bloque.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Repository
public interface CatTratadoBloqueRepository extends JpaRepository<CatTratadoBloque, CatTratadoBloqueId> {
}
