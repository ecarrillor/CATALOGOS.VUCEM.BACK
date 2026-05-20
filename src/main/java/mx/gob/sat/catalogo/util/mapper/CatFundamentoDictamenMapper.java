package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.fundamentodictamen.CatFundamentoDictamenRequest;
import mx.gob.sat.catalogo.controller.response.fundamentodictamen.CatFundamentoDictamenResponse;
import mx.gob.sat.catalogo.model.entity.CatFundamentoDictamen;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatFundamentoDictamenMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de FundamentoDictamen.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatFundamentoDictamenMapper {

    /** Convierte entidad a response DTO. */
    CatFundamentoDictamenResponse toResponse(CatFundamentoDictamen entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatFundamentoDictamenResponse> toResponseList(List<CatFundamentoDictamen> entities);

    /** Convierte request DTO a entidad. */
    CatFundamentoDictamen toEntity(CatFundamentoDictamenRequest request);
}
