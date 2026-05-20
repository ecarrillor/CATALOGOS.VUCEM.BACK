package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatTratamientoEspecial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatTratamientoEspecialRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de tratamientos especiales.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatTratamientoEspecialRepository extends JpaRepository<CatTratamientoEspecial, Short> {

    /**
     * Busca tratamientos especiales por nombre con paginacion.
     *
     * @param nombre Texto en nombre.
     * @param pageable Paginacion.
     * @return Pagina de tratamientos especiales.
     */
    Page<CatTratamientoEspecial> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
