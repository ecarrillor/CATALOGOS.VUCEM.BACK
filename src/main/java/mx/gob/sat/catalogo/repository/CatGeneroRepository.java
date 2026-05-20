package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatGenero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatGeneroRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de generos.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatGeneroRepository extends JpaRepository<CatGenero, Integer> {

    /**
     * Busca generos por descripcion con paginacion.
     *
     * @param descGenero Texto en descripcion.
     * @param pageable Paginacion.
     * @return Pagina de generos.
     */
    Page<CatGenero> findByDescGeneroContainingIgnoreCase(String descGenero, Pageable pageable);
}
