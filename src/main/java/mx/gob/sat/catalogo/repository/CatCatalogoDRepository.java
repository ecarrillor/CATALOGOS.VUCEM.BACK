package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatCatalogoD;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatCatalogoDRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de catalogos D.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatCatalogoDRepository extends JpaRepository<CatCatalogoD, String> {

    /**
     * Busca catalogos D por descripcion generica 1 o clave con paginacion.
     *
     * @param descGenerica1 Texto en descripcion generica 1.
     * @param cve Texto en clave.
     * @param pageable Paginacion.
     * @return Pagina de catalogos D.
     */
    Page<CatCatalogoD> findByDescGenerica1ContainingIgnoreCaseOrCveCatalogoContainingIgnoreCase(
            String descGenerica1, String cve, Pageable pageable);
}
