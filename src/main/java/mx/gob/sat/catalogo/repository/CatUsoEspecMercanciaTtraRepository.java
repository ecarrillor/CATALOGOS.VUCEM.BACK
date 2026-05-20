package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatUsoEspecMercanciaTtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatUsoEspecMercanciaTtraRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de uso especial de mercancia ttra.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatUsoEspecMercanciaTtraRepository extends JpaRepository<CatUsoEspecMercanciaTtra, Short> {
}
