package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.sitdomidc.CatSitDomIdcRequest;
import mx.gob.sat.catalogo.controller.response.sitdomidc.CatSitDomIdcResponse;
import mx.gob.sat.catalogo.model.entity.CatSitDomIdc;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatSitDomIdcMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de SitDomIdc.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatSitDomIdcMapper {

    /** Convierte entidad a response DTO. */
    CatSitDomIdcResponse toResponse(CatSitDomIdc entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatSitDomIdcResponse> toResponseList(List<CatSitDomIdc> entities);

    /** Convierte request DTO a entidad. */
    CatSitDomIdc toEntity(CatSitDomIdcRequest request);
}
