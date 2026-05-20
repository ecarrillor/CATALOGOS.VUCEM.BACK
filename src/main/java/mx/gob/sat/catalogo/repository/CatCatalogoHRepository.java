package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatCatalogoH;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>Class:</b> CatCatalogoHRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de catalogos H.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatCatalogoHRepository extends JpaRepository<CatCatalogoH, String> {

    /**
     * Busca catalogos H por nombre o clave con paginacion.
     *
     * @param nomCatalogo Texto en nombre.
     * @param cve Texto en clave.
     * @param pageable Paginacion.
     * @return Pagina de catalogos H.
     */
    Page<CatCatalogoH> findByNomCatalogoContainingIgnoreCaseOrCveCatalogoHContainingIgnoreCase(
            String nomCatalogo, String cve, Pageable pageable);

    /**
     * Lista todos los catalogos H activos.
     *
     * @return Lista de catalogos H activos.
     */
    List<CatCatalogoH> findByBlnActivoTrue();
}
