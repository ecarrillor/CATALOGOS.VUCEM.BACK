package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.servicioimmex.CatServicioImmexRequest;
import mx.gob.sat.catalogo.controller.response.servicioimmex.CatServicioImmexResponse;
import mx.gob.sat.catalogo.model.entity.CatServicioImmex;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatServicioImmexMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de ServicioImmex.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatServicioImmexMapper {

    /** Convierte entidad a response DTO. */
    CatServicioImmexResponse toResponse(CatServicioImmex entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatServicioImmexResponse> toResponseList(List<CatServicioImmex> entities);

    /** Convierte request DTO a entidad. */
    CatServicioImmex toEntity(CatServicioImmexRequest request);
}
