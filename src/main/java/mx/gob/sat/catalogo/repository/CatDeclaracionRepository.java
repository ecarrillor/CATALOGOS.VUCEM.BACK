package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatDeclaracion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatDeclaracionRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de declaraciones.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatDeclaracionRepository extends JpaRepository<CatDeclaracion, String> {

    /**
     * Busca declaraciones por descripcion o clave con paginacion.
     *
     * @param descDeclaracion Texto en descripcion.
     * @param cve Texto en clave.
     * @param pageable Paginacion.
     * @return Pagina de declaraciones.
     */
    Page<CatDeclaracion> findByDescDeclaracionContainingIgnoreCaseOrCveDeclaracionContainingIgnoreCase(
            String descDeclaracion, String cve, Pageable pageable);
}
