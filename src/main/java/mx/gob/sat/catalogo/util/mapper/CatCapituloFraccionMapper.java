package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.capitulofraccion.CatCapituloFraccionRequest;
import mx.gob.sat.catalogo.controller.response.capitulofraccion.CatCapituloFraccionResponse;
import mx.gob.sat.catalogo.model.entity.CatCapituloFraccion;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatCapituloFraccionMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de CapituloFraccion.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatCapituloFraccionMapper {

    /** Convierte entidad a response DTO. */
    CatCapituloFraccionResponse toResponse(CatCapituloFraccion entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatCapituloFraccionResponse> toResponseList(List<CatCapituloFraccion> entities);

    /** Convierte request DTO a entidad. */
    CatCapituloFraccion toEntity(CatCapituloFraccionRequest request);
}
