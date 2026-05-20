package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatTipoEmpresaRecif;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatTipoEmpresaRecifRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de tipos de empresa RECIF.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatTipoEmpresaRecifRepository extends JpaRepository<CatTipoEmpresaRecif, String> {

    /**
     * Busca tipos de empresa RECIF por descripcion o clave con paginacion.
     *
     * @param descripcion Texto en descripcion.
     * @param cve Texto en clave.
     * @param pageable Paginacion.
     * @return Pagina de tipos de empresa RECIF.
     */
    Page<CatTipoEmpresaRecif> findByDescripcionContainingIgnoreCaseOrCveTipoEmpresaRecifContainingIgnoreCase(
            String descripcion, String cve, Pageable pageable);
}
