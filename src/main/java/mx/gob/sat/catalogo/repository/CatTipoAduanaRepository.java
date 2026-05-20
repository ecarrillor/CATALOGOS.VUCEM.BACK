package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatTipoAduana;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatTipoAduanaRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para la entidad {@code CatTipoAduana}.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatTipoAduanaRepository extends JpaRepository<CatTipoAduana, String> {

    /**
     * Busca tipos de aduana por nombre o clave (sin distincion de mayusculas).
     *
     * @param nombre Texto de busqueda sobre el campo nombre.
     * @param cveTipoAduana Texto de busqueda sobre la clave.
     * @param pageable Configuracion de paginacion y ordenamiento.
     * @return Pagina de tipos de aduana que coinciden.
     */
    Page<CatTipoAduana> findByNombreContainingIgnoreCaseOrCveTipoAduanaContainingIgnoreCase(
            String nombre, String cveTipoAduana, Pageable pageable);
}
