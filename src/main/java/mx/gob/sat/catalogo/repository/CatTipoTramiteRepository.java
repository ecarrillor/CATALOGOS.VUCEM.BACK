package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatTipoTramite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatTipoTramiteRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de tipos de tramite.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatTipoTramiteRepository extends JpaRepository<CatTipoTramite, Long> {

    /**
     * Busca tipos de tramite con filtro de texto y estado activo.
     *
     * @param search Texto de busqueda (puede ser null).
     * @param activo Filtro de estado activo (puede ser null).
     * @param pageable Paginacion y ordenamiento.
     * @return Pagina de tipos de tramite.
     */
    @Query(value = """
            SELECT t FROM CatTipoTramite t
            LEFT JOIN FETCH t.idDependencia d
            WHERE (:search IS NULL OR
                LOWER(t.nombre) LIKE :search OR
                LOWER(t.cveServicio) LIKE :search OR
                LOWER(t.descServicio) LIKE :search)
            AND (:activo IS NULL OR t.blnActivo = :activo)
            """,
            countQuery = """
            SELECT COUNT(t) FROM CatTipoTramite t
            WHERE (:search IS NULL OR
                LOWER(t.nombre) LIKE :search OR
                LOWER(t.cveServicio) LIKE :search OR
                LOWER(t.descServicio) LIKE :search)
            AND (:activo IS NULL OR t.blnActivo = :activo)
            """)
    Page<CatTipoTramite> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable);
}
