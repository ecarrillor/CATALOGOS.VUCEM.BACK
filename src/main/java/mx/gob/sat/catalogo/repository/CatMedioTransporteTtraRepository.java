package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatMedioTransporteTtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatMedioTransporteTtraRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de medios de transporte ttra.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatMedioTransporteTtraRepository extends JpaRepository<CatMedioTransporteTtra, Integer> {
}
