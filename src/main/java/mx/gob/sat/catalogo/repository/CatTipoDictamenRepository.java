package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatTipoDictamen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatTipoDictamenRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de tipos de dictamen.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatTipoDictamenRepository extends JpaRepository<CatTipoDictamen, Long> {

    /**
     * Busca tipos de dictamen por nombre con paginacion.
     *
     * @param nombre Texto en nombre.
     * @param pageable Paginacion.
     * @return Pagina de tipos de dictamen.
     */
    Page<CatTipoDictamen> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
