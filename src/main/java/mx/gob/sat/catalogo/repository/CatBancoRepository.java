package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatBanco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatBancoRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de bancos.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatBancoRepository extends JpaRepository<CatBanco, String> {

    /**
     * Busca bancos activos por nombre o clave con paginacion.
     *
     * @param nombre Texto en nombre.
     * @param cve Texto en clave.
     * @param pageable Paginacion.
     * @return Pagina de bancos activos.
     */
    Page<CatBanco> findByBlnActivoTrueAndNombreContainingIgnoreCaseOrBlnActivoTrueAndCveBancoContainingIgnoreCase(
            String nombre, String cve, Pageable pageable);

    /**
     * Lista todos los bancos activos paginados.
     *
     * @param pageable Paginacion.
     * @return Pagina de bancos activos.
     */
    Page<CatBanco> findByBlnActivoTrue(Pageable pageable);
}
