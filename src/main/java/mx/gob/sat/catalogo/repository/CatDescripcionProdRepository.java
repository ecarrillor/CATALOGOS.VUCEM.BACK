package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatDescripcionProd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatDescripcionProdRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de descripciones de producto.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatDescripcionProdRepository extends JpaRepository<CatDescripcionProd, Integer> {

    /**
     * Busca descripciones de producto por texto con paginacion.
     *
     * @param descripcionProducto Texto en descripcion.
     * @param pageable Paginacion.
     * @return Pagina de descripciones de producto.
     */
    Page<CatDescripcionProd> findByDescripcionProductoContainingIgnoreCase(
            String descripcionProducto, Pageable pageable);
}
