package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatEsquemaRegla8;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatEsquemaRegla8Repository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de esquemas de regla 8.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatEsquemaRegla8Repository extends JpaRepository<CatEsquemaRegla8, Short> {

    /**
     * Busca esquemas de regla 8 por nombre con paginacion.
     *
     * @param nombre Texto en nombre.
     * @param pageable Paginacion.
     * @return Pagina de esquemas de regla 8.
     */
    Page<CatEsquemaRegla8> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
