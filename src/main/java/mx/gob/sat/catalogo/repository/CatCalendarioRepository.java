package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatCalendario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatCalendarioRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de calendarios.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatCalendarioRepository extends JpaRepository<CatCalendario, String> {

    /**
     * Busca calendarios por nombre o clave con paginacion.
     *
     * @param nombre Texto en nombre.
     * @param cve Texto en clave.
     * @param pageable Paginacion.
     * @return Pagina de calendarios.
     */
    Page<CatCalendario> findByNombreContainingIgnoreCaseOrCveCalendarioContainingIgnoreCase(
            String nombre, String cve, Pageable pageable);
}
