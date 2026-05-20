package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.sectorprosec.CatSectorProsecRequest;
import mx.gob.sat.catalogo.controller.response.sectorprosec.CatSectorProsecResponse;
import mx.gob.sat.catalogo.model.entity.CatSectorProsec;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatSectorProsecMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de sectores PROSEC.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatSectorProsecMapper {

    /** Convierte entidad a response DTO. */
    CatSectorProsecResponse toResponse(CatSectorProsec entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatSectorProsecResponse> toResponseList(List<CatSectorProsec> entities);

    /** Convierte request DTO a entidad. */
    CatSectorProsec toEntity(CatSectorProsecRequest request);
}
