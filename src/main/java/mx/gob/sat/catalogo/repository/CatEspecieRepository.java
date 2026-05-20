package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatEspecie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>Class:</b> CatEspecieRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de especies.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatEspecieRepository extends JpaRepository<CatEspecie, Integer> {

    /**
     * Busca especies por descripcion con paginacion.
     *
     * @param descEspecie Texto en descripcion.
     * @param pageable Paginacion.
     * @return Pagina de especies.
     */
    Page<CatEspecie> findByDescEspecieContainingIgnoreCase(String descEspecie, Pageable pageable);

    /**
     * Lista todas las especies activas.
     *
     * @return Lista de especies activas.
     */
    List<CatEspecie> findByBlnActivoTrue();
}
