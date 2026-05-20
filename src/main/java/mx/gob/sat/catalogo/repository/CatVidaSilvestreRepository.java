package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatVidaSilvestre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatVidaSilvestreRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de vida silvestre.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatVidaSilvestreRepository extends JpaRepository<CatVidaSilvestre, Integer> {

    /**
     * Busca registros de vida silvestre por nombre comun o cientifico con paginacion.
     *
     * @param descNombreComun Texto en nombre comun.
     * @param descNombreCientifico Texto en nombre cientifico.
     * @param pageable Paginacion.
     * @return Pagina de registros de vida silvestre.
     */
    Page<CatVidaSilvestre> findByDescNombreComunContainingIgnoreCaseOrDescNombreCientificoContainingIgnoreCase(
            String descNombreComun, String descNombreCientifico, Pageable pageable);
}
