package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.producto.CatProductoRequest;
import mx.gob.sat.catalogo.controller.response.producto.CatProductoResponse;
import mx.gob.sat.catalogo.model.entity.CatProducto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatProductoMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de Producto.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatProductoMapper {

    /** Convierte entidad a response DTO. */
    CatProductoResponse toResponse(CatProducto entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatProductoResponse> toResponseList(List<CatProducto> entities);

    /** Convierte request DTO a entidad. */
    CatProducto toEntity(CatProductoRequest request);
}
