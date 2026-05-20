package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatPatenteAduanal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatPatenteAduanalRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de patentes aduanales.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatPatenteAduanalRepository extends JpaRepository<CatPatenteAduanal, String> {

    /**
     * Busca patentes aduanales por clave o RFC con paginacion.
     *
     * @param cve Texto en clave.
     * @param rfc Texto en RFC.
     * @param pageable Paginacion.
     * @return Pagina de patentes aduanales.
     */
    Page<CatPatenteAduanal> findByCvePatenteAduanalContainingIgnoreCaseOrRfcContainingIgnoreCase(
            String cve, String rfc, Pageable pageable);
}
