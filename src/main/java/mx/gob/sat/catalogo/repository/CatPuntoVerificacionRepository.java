package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatPuntoVerificacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatPuntoVerificacionRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de puntos de verificacion.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatPuntoVerificacionRepository extends JpaRepository<CatPuntoVerificacion, Integer> {

    /**
     * Busca puntos de verificacion por nombre con paginacion.
     *
     * @param nomPuntoVerif Texto en nombre.
     * @param pageable Paginacion.
     * @return Pagina de puntos de verificacion.
     */
    Page<CatPuntoVerificacion> findByNomPuntoVerifContainingIgnoreCase(
            String nomPuntoVerif, Pageable pageable);
}
