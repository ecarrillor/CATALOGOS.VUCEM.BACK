package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.tipodictamen.CatTipoDictamenRequest;
import mx.gob.sat.catalogo.controller.response.tipodictamen.CatTipoDictamenResponse;
import mx.gob.sat.catalogo.model.entity.CatTipoDictamen;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatTipoDictamenMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de TipoDictamen.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatTipoDictamenMapper {

    /** Convierte entidad a response DTO. */
    CatTipoDictamenResponse toResponse(CatTipoDictamen entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatTipoDictamenResponse> toResponseList(List<CatTipoDictamen> entities);

    /** Convierte request DTO a entidad. */
    CatTipoDictamen toEntity(CatTipoDictamenRequest request);
}
