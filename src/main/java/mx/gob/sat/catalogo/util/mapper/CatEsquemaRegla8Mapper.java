package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.esquemaregla8.CatEsquemaRegla8Request;
import mx.gob.sat.catalogo.controller.response.esquemaregla8.CatEsquemaRegla8Response;
import mx.gob.sat.catalogo.model.entity.CatEsquemaRegla8;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatEsquemaRegla8Mapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de EsquemaRegla8.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatEsquemaRegla8Mapper {

    /** Convierte entidad a response DTO. */
    CatEsquemaRegla8Response toResponse(CatEsquemaRegla8 entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatEsquemaRegla8Response> toResponseList(List<CatEsquemaRegla8> entities);

    /** Convierte request DTO a entidad. */
    CatEsquemaRegla8 toEntity(CatEsquemaRegla8Request request);
}
