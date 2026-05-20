package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatUnidadAdminVecina;
import mx.gob.sat.catalogo.model.entity.CatUnidadAdminVecinaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Interface:</b> CatUnidadAdminVecinaRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de unidades administrativas vecinas.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repository
 */
@Repository
public interface CatUnidadAdminVecinaRepository extends JpaRepository<CatUnidadAdminVecina, CatUnidadAdminVecinaId> {
}
