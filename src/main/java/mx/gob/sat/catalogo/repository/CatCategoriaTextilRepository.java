package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatCategoriaTextil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatCategoriaTextilRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de categorias textiles.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatCategoriaTextilRepository extends JpaRepository<CatCategoriaTextil, Long> {

    /**
     * Busca categorias textiles por descripcion o codigo con paginacion.
     *
     * @param descripcion Texto en descripcion.
     * @param codCategoriaTextil Texto en codigo.
     * @param pageable Paginacion.
     * @return Pagina de categorias textiles.
     */
    Page<CatCategoriaTextil> findByDescripcionContainingIgnoreCaseOrCodCategoriaTextilContainingIgnoreCase(
            String descripcion, String codCategoriaTextil, Pageable pageable);
}
