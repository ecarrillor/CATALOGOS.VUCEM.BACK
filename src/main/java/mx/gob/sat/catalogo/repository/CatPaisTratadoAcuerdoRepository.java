package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatPaisTratadoAcuerdo;
import mx.gob.sat.catalogo.model.entity.CatPaisTratadoAcuerdoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para el catalogo de pais tratado acuerdo.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Repository
public interface CatPaisTratadoAcuerdoRepository extends JpaRepository<CatPaisTratadoAcuerdo, CatPaisTratadoAcuerdoId> {
}
