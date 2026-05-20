package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatCriterioDictaminacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatCriterioDictaminacionRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de criterios de dictaminacion.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatCriterioDictaminacionRepository extends JpaRepository<CatCriterioDictaminacion, Short> {

    /**
     * Busca criterios de dictaminacion por nombre con paginacion.
     *
     * @param nombre Texto en nombre.
     * @param pageable Paginacion.
     * @return Pagina de criterios de dictaminacion.
     */
    Page<CatCriterioDictaminacion> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
