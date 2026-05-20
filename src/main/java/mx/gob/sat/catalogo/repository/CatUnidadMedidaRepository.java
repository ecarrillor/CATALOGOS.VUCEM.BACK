package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatUnidadMedida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>Class:</b> CatUnidadMedidaRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de unidades de medida.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatUnidadMedidaRepository extends JpaRepository<CatUnidadMedida, String> {

    /**
     * Busca unidades de medida por descripcion, clave o sigla con paginacion.
     *
     * @param descripcion Texto en descripcion.
     * @param cve Texto en clave.
     * @param sigla Texto en sigla.
     * @param pageable Paginacion.
     * @return Pagina de unidades de medida.
     */
    Page<CatUnidadMedida> findByDescripcionContainingIgnoreCaseOrCveUnidadMedidaContainingIgnoreCaseOrSiglaContainingIgnoreCase(
            String descripcion, String cve, String sigla, Pageable pageable);

    /**
     * Lista todas las unidades de medida activas.
     *
     * @return Lista de unidades de medida activas.
     */
    List<CatUnidadMedida> findByBlnActivoTrue();
}
