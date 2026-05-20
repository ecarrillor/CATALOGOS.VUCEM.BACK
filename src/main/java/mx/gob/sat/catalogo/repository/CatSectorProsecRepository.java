package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatSectorProsec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatSectorProsecRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de sectores PROSEC.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatSectorProsecRepository extends JpaRepository<CatSectorProsec, String> {

    /**
     * Busca sectores PROSEC por nombre o clave con paginacion.
     *
     * @param nombre Texto en nombre.
     * @param cve Texto en clave.
     * @param pageable Paginacion.
     * @return Pagina de sectores PROSEC.
     */
    Page<CatSectorProsec> findByNombreContainingIgnoreCaseOrCveSectorProsecContainingIgnoreCase(
            String nombre, String cve, Pageable pageable);
}
