package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatNormalOficial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatNormalOficialRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de normas oficiales.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatNormalOficialRepository extends JpaRepository<CatNormalOficial, Integer> {

    /**
     * Busca normas oficiales por clave o descripcion con paginacion.
     *
     * @param claveNorma Texto en clave de norma.
     * @param descNorma Texto en descripcion.
     * @param pageable Paginacion.
     * @return Pagina de normas oficiales.
     */
    Page<CatNormalOficial> findByClaveNormaContainingIgnoreCaseOrDescNormaContainingIgnoreCase(
            String claveNorma, String descNorma, Pageable pageable);
}
