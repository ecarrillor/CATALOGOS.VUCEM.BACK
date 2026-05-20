package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatDependencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>Class:</b> CatDependenciaRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de dependencias.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatDependenciaRepository extends JpaRepository<CatDependencia, Long> {

    /**
     * Busca dependencias con filtro de texto y estado activo, incluyendo datos del calendario.
     *
     * @param search Texto de busqueda (puede ser null).
     * @param activo Filtro de estado activo (puede ser null).
     * @param pageable Paginacion y ordenamiento.
     * @return Pagina de dependencias.
     */
    @Query(value = """
            SELECT d FROM CatDependencia d
            JOIN FETCH d.cveCalendario c
            WHERE (:search IS NULL OR
                LOWER(CAST(d.id AS string)) LIKE :search OR
                LOWER(d.nombre) LIKE :search OR
                LOWER(d.acronimo) LIKE :search OR
                LOWER(c.nombre) LIKE :search OR
                LOWER(c.cveCalendario) LIKE :search)
            AND (:activo IS NULL OR d.blnActivo = :activo)
            """,
            countQuery = """
            SELECT COUNT(d) FROM CatDependencia d
            JOIN d.cveCalendario c
            WHERE (:search IS NULL OR
                LOWER(CAST(d.id AS string)) LIKE :search OR
                LOWER(d.nombre) LIKE :search OR
                LOWER(d.acronimo) LIKE :search OR
                LOWER(c.nombre) LIKE :search OR
                LOWER(c.cveCalendario) LIKE :search)
            AND (:activo IS NULL OR d.blnActivo = :activo)
            """)
    Page<CatDependencia> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable);

    /**
     * Lista todas las dependencias activas.
     *
     * @return Lista de dependencias activas.
     */
    List<CatDependencia> findByBlnActivoTrue();
}
