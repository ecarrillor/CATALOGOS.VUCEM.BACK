package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatIdentificadorPrevalidador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatIdentificadorPrevalidadorRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de identificadores de prevalidador.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatIdentificadorPrevalidadorRepository extends JpaRepository<CatIdentificadorPrevalidador, Long> {

    /**
     * Busca identificadores de prevalidador por caracter con paginacion.
     *
     * @param caracterIdentificacion Texto en caracter de identificacion.
     * @param pageable Paginacion.
     * @return Pagina de identificadores de prevalidador.
     */
    Page<CatIdentificadorPrevalidador> findByCaracterIdentificacionContainingIgnoreCase(
            String caracterIdentificacion, Pageable pageable);
}
