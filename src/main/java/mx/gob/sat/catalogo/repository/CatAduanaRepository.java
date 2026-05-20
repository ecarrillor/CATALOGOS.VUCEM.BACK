package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatAduana;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <b>Class:</b> CatAduanaRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para la entidad {@code CatAduana}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatAduanaRepository extends JpaRepository<CatAduana, String> {

    /**
     * Busca aduanas cuyo nombre o clave contengan el texto de busqueda (sin distincion de mayusculas).
     *
     * @param nombre Texto de busqueda sobre el campo nombre.
     * @param cveAduana Texto de busqueda sobre el campo cveAduana.
     * @param pageable Configuracion de paginacion y ordenamiento.
     * @return Pagina de aduanas que coinciden con la busqueda.
     */
    Page<CatAduana> findByNombreContainingIgnoreCaseOrCveAduanaContainingIgnoreCase(
            String nombre, String cveAduana, Pageable pageable);

    /**
     * Verifica si existe una aduana con la clave indicada.
     *
     * @param cveAduana Clave de la aduana a verificar.
     * @return {@code true} si existe una aduana con esa clave.
     */
    @Query("SELECT COUNT(c) > 0 FROM CatAduana c WHERE c.cveAduana = :cveAduana")
    boolean existsByCveAduana(String cveAduana);

    /**
     * Obtiene la aduana con la mayor clave en orden descendente.
     *
     * @return Ultima aduana segun orden por cveAduana descendente.
     */
    Optional<CatAduana> findTopByOrderByCveAduanaDesc();
}
