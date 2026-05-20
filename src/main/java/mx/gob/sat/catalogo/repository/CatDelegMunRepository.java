package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatDelegMun;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatDelegMunRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de delegaciones y municipios.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatDelegMunRepository extends JpaRepository<CatDelegMun, String> {

    /**
     * Busca delegaciones/municipios con filtro de texto y estado activo.
     *
     * @param search Texto de busqueda (puede ser null).
     * @param activo Filtro de estado activo (puede ser null).
     * @param pageable Paginacion y ordenamiento.
     * @return Pagina de delegaciones/municipios.
     */
    @Query(value = """
            SELECT d FROM CatDelegMun d
            LEFT JOIN FETCH d.cveEntidad e
            WHERE (:search IS NULL OR
                LOWER(d.nombre) LIKE :search OR
                LOWER(d.cveDelegMun) LIKE :search)
            AND (:activo IS NULL OR d.blnActivo = :activo)
            """,
            countQuery = """
            SELECT COUNT(d) FROM CatDelegMun d
            WHERE (:search IS NULL OR
                LOWER(d.nombre) LIKE :search OR
                LOWER(d.cveDelegMun) LIKE :search)
            AND (:activo IS NULL OR d.blnActivo = :activo)
            """)
    Page<CatDelegMun> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable);
}
