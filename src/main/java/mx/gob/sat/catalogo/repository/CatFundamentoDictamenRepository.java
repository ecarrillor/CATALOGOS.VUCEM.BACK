package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatFundamentoDictamen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatFundamentoDictamenRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de fundamentos de dictamen.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatFundamentoDictamenRepository extends JpaRepository<CatFundamentoDictamen, Long> {

    /**
     * Busca fundamentos de dictamen por descripcion con paginacion.
     *
     * @param descripcion Texto en descripcion.
     * @param pageable Paginacion.
     * @return Pagina de fundamentos de dictamen.
     */
    Page<CatFundamentoDictamen> findByDescripcionContainingIgnoreCase(String descripcion, Pageable pageable);
}
