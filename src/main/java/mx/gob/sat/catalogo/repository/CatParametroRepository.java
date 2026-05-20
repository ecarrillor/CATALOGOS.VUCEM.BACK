package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatParametro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatParametroRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de parametros del sistema.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatParametroRepository extends JpaRepository<CatParametro, String> {

    /**
     * Busca parametros con filtro de texto y estado activo, incluyendo datos de la dependencia.
     *
     * @param search Texto de busqueda (puede ser null).
     * @param activo Filtro de estado activo (puede ser null).
     * @param pageable Paginacion y ordenamiento.
     * @return Pagina de parametros.
     */
    @Query(value = """
            SELECT p FROM CatParametro p
            JOIN FETCH p.idDependencia d
            WHERE (:search IS NULL OR
                LOWER(p.cveParametro) LIKE :search OR
                LOWER(p.descripcion) LIKE :search OR
                LOWER(p.valor) LIKE :search OR
                LOWER(CAST(d.id AS string)) LIKE :search OR
                LOWER(d.nombre) LIKE :search)
            AND (:activo IS NULL OR p.blnActivo = :activo)
            """,
            countQuery = """
            SELECT COUNT(p) FROM CatParametro p
            JOIN p.idDependencia d
            WHERE (:search IS NULL OR
                LOWER(p.cveParametro) LIKE :search OR
                LOWER(p.descripcion) LIKE :search OR
                LOWER(p.valor) LIKE :search OR
                LOWER(CAST(d.id AS string)) LIKE :search OR
                LOWER(d.nombre) LIKE :search)
            AND (:activo IS NULL OR p.blnActivo = :activo)
            """)
    Page<CatParametro> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable);
}
