package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatRecintoFiscalizado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatRecintoFiscalizadoRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de recintos fiscalizados.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatRecintoFiscalizadoRepository extends JpaRepository<CatRecintoFiscalizado, Long> {

    /**
     * Busca recintos fiscalizados por nombre o tipo con paginacion.
     *
     * @param nombre Texto en nombre.
     * @param ideTipoRecintoFiscalizado Texto en tipo.
     * @param pageable Paginacion.
     * @return Pagina de recintos fiscalizados.
     */
    Page<CatRecintoFiscalizado> findByNombreContainingIgnoreCaseOrIdeTipoRecintoFiscalizadoContainingIgnoreCase(
            String nombre, String ideTipoRecintoFiscalizado, Pageable pageable);
}
