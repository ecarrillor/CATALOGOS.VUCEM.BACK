package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.leyendatexto.CatLeyendaTextoRequest;
import mx.gob.sat.catalogo.controller.response.leyendatexto.CatLeyendaTextoResponse;
import mx.gob.sat.catalogo.model.entity.CatLeyendaTexto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatLeyendaTextoMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de LeyendaTexto.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatLeyendaTextoMapper {

    /** Convierte entidad a response DTO. */
    CatLeyendaTextoResponse toResponse(CatLeyendaTexto entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatLeyendaTextoResponse> toResponseList(List<CatLeyendaTexto> entities);

    /** Convierte request DTO a entidad. */
    CatLeyendaTexto toEntity(CatLeyendaTextoRequest request);
}
