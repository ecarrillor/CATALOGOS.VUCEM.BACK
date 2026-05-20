package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.condicionuso.CatCondicionUsoRequest;
import mx.gob.sat.catalogo.controller.response.condicionuso.CatCondicionUsoResponse;
import mx.gob.sat.catalogo.model.entity.CatCondicionUso;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatCondicionUsoMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de CondicionUso.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatCondicionUsoMapper {

    /** Convierte entidad a response DTO. */
    CatCondicionUsoResponse toResponse(CatCondicionUso entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatCondicionUsoResponse> toResponseList(List<CatCondicionUso> entities);

    /** Convierte request DTO a entidad. */
    CatCondicionUso toEntity(CatCondicionUsoRequest request);
}
