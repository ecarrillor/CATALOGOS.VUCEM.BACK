package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatCas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatCasRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo CAS.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatCasRepository extends JpaRepository<CatCas, Short> {

    /**
     * Busca registros CAS por descripcion con paginacion.
     *
     * @param descCas Texto en descripcion.
     * @param pageable Paginacion.
     * @return Pagina de registros CAS.
     */
    Page<CatCas> findByDescCasContainingIgnoreCase(String descCas, Pageable pageable);
}
