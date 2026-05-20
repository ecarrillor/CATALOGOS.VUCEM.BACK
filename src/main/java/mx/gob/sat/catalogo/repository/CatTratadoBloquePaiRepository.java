package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatTratadoBloquePai;
import mx.gob.sat.catalogo.model.entity.CatTratadoBloquePaiId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para el catalogo de tratado bloque pais.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Repository
public interface CatTratadoBloquePaiRepository extends JpaRepository<CatTratadoBloquePai, CatTratadoBloquePaiId> {
}
