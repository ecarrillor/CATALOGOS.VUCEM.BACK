package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatAga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatAgaRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de AGA.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatAgaRepository extends JpaRepository<CatAga, String> {

    /**
     * Busca registros AGA por descripcion o clave con paginacion.
     *
     * @param descripcion Texto en descripcion.
     * @param cve Texto en clave.
     * @param pageable Paginacion.
     * @return Pagina de registros AGA.
     */
    Page<CatAga> findByDescripcionContainingIgnoreCaseOrCveParametroContainingIgnoreCase(
            String descripcion, String cve, Pageable pageable);
}
