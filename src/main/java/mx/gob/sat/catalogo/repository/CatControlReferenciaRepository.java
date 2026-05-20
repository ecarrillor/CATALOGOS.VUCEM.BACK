package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatControlReferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para el catalogo de control de referencia.
 */
@Repository
public interface CatControlReferenciaRepository extends JpaRepository<CatControlReferencia, Integer> {
}
