package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.descripcionprod.CatDescripcionProdRequest;
import mx.gob.sat.catalogo.controller.response.descripcionprod.CatDescripcionProdResponse;
import mx.gob.sat.catalogo.model.entity.CatDescripcionProd;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatDescripcionProdMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de DescripcionProd.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatDescripcionProdMapper {

    /** Convierte entidad a response DTO. */
    CatDescripcionProdResponse toResponse(CatDescripcionProd entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatDescripcionProdResponse> toResponseList(List<CatDescripcionProd> entities);

    /** Convierte request DTO a entidad. */
    CatDescripcionProd toEntity(CatDescripcionProdRequest request);
}
