package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatTipoDocumento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatTipoDocumentoRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de tipos de documento.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatTipoDocumentoRepository extends JpaRepository<CatTipoDocumento, Short> {

    /**
     * Busca tipos de documento por nombre con paginacion.
     *
     * @param nombre Texto en nombre.
     * @param pageable Paginacion.
     * @return Pagina de tipos de documento.
     */
    Page<CatTipoDocumento> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
