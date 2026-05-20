package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.tipodocumento.CatTipoDocumentoRequest;
import mx.gob.sat.catalogo.controller.response.tipodocumento.CatTipoDocumentoResponse;
import mx.gob.sat.catalogo.model.entity.CatTipoDocumento;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatTipoDocumentoMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de TipoDocumento.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatTipoDocumentoMapper {

    /** Convierte entidad a response DTO. */
    CatTipoDocumentoResponse toResponse(CatTipoDocumento entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatTipoDocumentoResponse> toResponseList(List<CatTipoDocumento> entities);

    /** Convierte request DTO a entidad. */
    CatTipoDocumento toEntity(CatTipoDocumentoRequest request);
}
