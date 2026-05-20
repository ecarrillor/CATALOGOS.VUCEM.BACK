package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatCapituloFraccion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatCapituloFraccionRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de capitulos de fraccion.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatCapituloFraccionRepository extends JpaRepository<CatCapituloFraccion, String> {

    /**
     * Busca capitulos de fraccion por nombre o clave con paginacion.
     *
     * @param nombre Texto en nombre.
     * @param cve Texto en clave.
     * @param pageable Paginacion.
     * @return Pagina de capitulos de fraccion.
     */
    Page<CatCapituloFraccion> findByNombreContainingIgnoreCaseOrCveCapituloFraccionContainingIgnoreCase(
            String nombre, String cve, Pageable pageable);
}
