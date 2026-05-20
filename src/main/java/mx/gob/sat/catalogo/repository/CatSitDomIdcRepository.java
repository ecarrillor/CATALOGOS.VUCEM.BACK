package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatSitDomIdc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatSitDomIdcRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de situacion domicilio IDC.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatSitDomIdcRepository extends JpaRepository<CatSitDomIdc, Long> {

    /**
     * Busca registros de situacion domicilio IDC por descripcion con paginacion.
     *
     * @param descripcion Texto en descripcion.
     * @param pageable Paginacion.
     * @return Pagina de registros.
     */
    Page<CatSitDomIdc> findByDescripcionContainingIgnoreCase(String descripcion, Pageable pageable);
}
