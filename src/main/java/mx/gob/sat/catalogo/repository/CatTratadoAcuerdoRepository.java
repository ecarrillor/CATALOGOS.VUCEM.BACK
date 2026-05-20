package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatTratadoAcuerdo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatTratadoAcuerdoRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de tratados y acuerdos.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatTratadoAcuerdoRepository extends JpaRepository<CatTratadoAcuerdo, Short> {

    /**
     * Busca tratados o acuerdos por nombre o clave con paginacion.
     *
     * @param nombre Texto en nombre.
     * @param cveTratadoAcuerdo Texto en clave.
     * @param pageable Paginacion.
     * @return Pagina de tratados o acuerdos.
     */
    Page<CatTratadoAcuerdo> findByNombreContainingIgnoreCaseOrCveTratadoAcuerdoContainingIgnoreCase(
            String nombre, String cveTratadoAcuerdo, Pageable pageable);
}
