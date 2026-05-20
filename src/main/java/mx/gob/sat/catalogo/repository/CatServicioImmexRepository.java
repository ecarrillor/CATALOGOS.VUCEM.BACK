package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatServicioImmex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatServicioImmexRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de servicios IMMEX.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatServicioImmexRepository extends JpaRepository<CatServicioImmex, Short> {

    /**
     * Busca servicios IMMEX por nombre con paginacion.
     *
     * @param nombre Texto en nombre.
     * @param pageable Paginacion.
     * @return Pagina de servicios IMMEX.
     */
    Page<CatServicioImmex> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
