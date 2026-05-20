package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatPais;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatPaisRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para la entidad {@code CatPais}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatPaisRepository extends JpaRepository<CatPais, String> {

    /**
     * Busca paises con filtro de texto libre y filtro de estado activo/inactivo.
     * El parametro {@code search} se busca en cvePais, nombre, cvePaisWco y nombreAlterno.
     * El parametro {@code activo} filtra por blnActivo; si es null no aplica filtro.
     *
     * @param search Texto de busqueda (con wildcards ya incluidos, ej: {@code "%texto%"}).
     * @param activo Filtro de estado: true=activos, false=inactivos, null=todos.
     * @param pageable Configuracion de paginacion y ordenamiento.
     * @return Pagina de paises que coinciden con los criterios.
     */
    @Query("""
            SELECT p FROM CatPais p
            WHERE (:search IS NULL OR (
                LOWER(p.cvePais) LIKE :search OR
                LOWER(p.nombre) LIKE :search OR
                LOWER(p.cvePaisWco) LIKE :search OR
                LOWER(p.nombreAlterno) LIKE :search
            ))
            AND (:activo IS NULL OR p.blnActivo = :activo)
            """)
    Page<CatPais> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable);
}
