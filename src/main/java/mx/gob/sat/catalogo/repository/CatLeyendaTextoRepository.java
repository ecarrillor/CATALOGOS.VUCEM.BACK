package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatLeyendaTexto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class:</b> CatLeyendaTextoRepository.java <br>
 * <b>Description:</b>
 * <p>Repositorio JPA para el catalogo de leyendas de texto.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface CatLeyendaTextoRepository extends JpaRepository<CatLeyendaTexto, Long> {

    /**
     * Busca leyendas de texto por leyenda o tipo con paginacion.
     *
     * @param leyenda Texto en leyenda.
     * @param ideTipoLeyendaTexto Texto en tipo.
     * @param pageable Paginacion.
     * @return Pagina de leyendas de texto.
     */
    Page<CatLeyendaTexto> findByLeyendaContainingIgnoreCaseOrIdeTipoLeyendaTextoContainingIgnoreCase(
            String leyenda, String ideTipoLeyendaTexto, Pageable pageable);
}
