package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.identificadorprevalidador.CatIdentificadorPrevalidadorRequest;
import mx.gob.sat.catalogo.controller.response.identificadorprevalidador.CatIdentificadorPrevalidadorResponse;
import mx.gob.sat.catalogo.model.entity.CatIdentificadorPrevalidador;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatIdentificadorPrevalidadorMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de IdentificadorPrevalidador.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatIdentificadorPrevalidadorMapper {

    /** Convierte entidad a response DTO. */
    CatIdentificadorPrevalidadorResponse toResponse(CatIdentificadorPrevalidador entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatIdentificadorPrevalidadorResponse> toResponseList(List<CatIdentificadorPrevalidador> entities);

    /** Convierte request DTO a entidad. */
    CatIdentificadorPrevalidador toEntity(CatIdentificadorPrevalidadorRequest request);
}
