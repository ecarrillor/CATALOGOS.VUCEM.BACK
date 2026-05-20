package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatProductoRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de productos.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatProductoRepository extends JpaRepository<CatProducto, String> {

    /**
     * Busca productos por nombre, clave o descripcion con paginacion.
     *
     * @param nombre Texto en nombre.
     * @param cve Texto en clave.
     * @param descripcion Texto en descripcion.
     * @param pageable Paginacion.
     * @return Pagina de productos.
     */
    Page<CatProducto> findByNombreContainingIgnoreCaseOrCveProductoContainingIgnoreCaseOrDescripcionContainingIgnoreCase(
            String nombre, String cve, String descripcion, Pageable pageable);
}
