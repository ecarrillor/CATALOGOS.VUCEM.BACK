package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.fraccionaladi.CatFraccionAladiResponse;
import mx.gob.sat.catalogo.model.entity.CatFraccionAladi;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatFraccionAladiMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de FraccionAladi.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatFraccionAladiMapper {

    /** Convierte entidad a response DTO. */
    CatFraccionAladiResponse toResponse(CatFraccionAladi entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatFraccionAladiResponse> toResponseList(List<CatFraccionAladi> entities);
}
