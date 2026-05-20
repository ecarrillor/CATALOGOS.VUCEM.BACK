package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatSemanaLaboral;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatSemanaLaboralRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de semanas laborales.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatSemanaLaboralRepository extends JpaRepository<CatSemanaLaboral, Integer> {

    /**
     * Busca semanas laborales por descripcion con paginacion.
     *
     * @param descripcion Texto en descripcion.
     * @param pageable Paginacion.
     * @return Pagina de semanas laborales.
     */
    Page<CatSemanaLaboral> findByDescripcionContainingIgnoreCase(String descripcion, Pageable pageable);
}
