package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.catalogoh.CatCatalogoHRequest;
import mx.gob.sat.catalogo.controller.response.catalogoh.CatCatalogoHResponse;
import mx.gob.sat.catalogo.model.entity.CatCatalogoH;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatCatalogoHMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de CatalogoH.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatCatalogoHMapper {

    /** Convierte entidad a response DTO. */
    CatCatalogoHResponse toResponse(CatCatalogoH entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatCatalogoHResponse> toResponseList(List<CatCatalogoH> entities);

    /** Convierte request DTO a entidad. */
    CatCatalogoH toEntity(CatCatalogoHRequest request);
}
