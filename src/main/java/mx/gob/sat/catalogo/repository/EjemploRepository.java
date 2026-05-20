package mx.gob.sat.catalogo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.gob.sat.commonmodel.model.common.catalogo.AduanaEntity;

/*** 
 * <b>Class:</b> EjemploRepository.java <br>
 * <b>Description:</b>
 * <p>
 * Clase repositiorio para el manejo de los query´s <b>{@code cat_ejemplo}</b>.
 * </p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * 
 * @created 20 de junio del 2025
 * @version 1.0
 * @category Repositorio
 */
@Repository
public interface EjemploRepository extends JpaRepository<AduanaEntity, Integer> {

    /**
     * Consulta los datos de un ejemplo por su identificador
     * 
     * @param id - Representa el identificador del ejemplo
     * @return Retorna la entidadad de ejemplo con todos sus atributos
     */
    @Query("SELECT e FROM AduanaEntity e WHERE e.id = :id")
    abstract Optional<AduanaEntity> getById(@Param("id") int id);
}
