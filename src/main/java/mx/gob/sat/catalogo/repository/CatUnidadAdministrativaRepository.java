package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatUnidadAdministrativa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatUnidadAdministrativaRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de unidades administrativas.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatUnidadAdministrativaRepository extends JpaRepository<CatUnidadAdministrativa, String> {

    /**
     * Busca unidades administrativas con filtro de texto y estado activo.
     *
     * @param search Texto de busqueda (puede ser null).
     * @param activo Filtro de estado activo (puede ser null).
     * @param pageable Paginacion y ordenamiento.
     * @return Pagina de unidades administrativas.
     */
    @Query(value = """
            SELECT u FROM CatUnidadAdministrativa u
            JOIN FETCH u.idDependencia d
            WHERE (:search IS NULL OR
                LOWER(u.nombre) LIKE :search OR
                LOWER(u.cveUnidadAdministrativa) LIKE :search OR
                LOWER(u.acronimo) LIKE :search)
            AND (:activo IS NULL OR u.blnActivo = :activo)
            """,
            countQuery = """
            SELECT COUNT(u) FROM CatUnidadAdministrativa u
            WHERE (:search IS NULL OR
                LOWER(u.nombre) LIKE :search OR
                LOWER(u.cveUnidadAdministrativa) LIKE :search OR
                LOWER(u.acronimo) LIKE :search)
            AND (:activo IS NULL OR u.blnActivo = :activo)
            """)
    Page<CatUnidadAdministrativa> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable);
}
