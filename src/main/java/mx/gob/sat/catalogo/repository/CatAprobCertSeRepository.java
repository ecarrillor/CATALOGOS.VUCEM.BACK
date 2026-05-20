package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatAprobCertSe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatAprobCertSeRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de aprobaciones de certificado SE.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatAprobCertSeRepository extends JpaRepository<CatAprobCertSe, Short> {
}
