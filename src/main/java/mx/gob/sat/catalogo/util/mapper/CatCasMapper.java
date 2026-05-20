package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.cas.CatCasRequest;
import mx.gob.sat.catalogo.controller.response.cas.CatCasResponse;
import mx.gob.sat.catalogo.model.entity.CatCas;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatCasMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de Cas.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatCasMapper {

    /** Convierte entidad a response DTO. */
    CatCasResponse toResponse(CatCas entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatCasResponse> toResponseList(List<CatCas> entities);

    /** Convierte request DTO a entidad. */
    CatCas toEntity(CatCasRequest request);
}
