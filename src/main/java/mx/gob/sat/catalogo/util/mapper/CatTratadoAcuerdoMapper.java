package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.tratadoacuerdo.CatTratadoAcuerdoRequest;
import mx.gob.sat.catalogo.controller.response.tratadoacuerdo.CatTratadoAcuerdoResponse;
import mx.gob.sat.catalogo.model.entity.CatTratadoAcuerdo;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatTratadoAcuerdoMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de TratadoAcuerdo.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatTratadoAcuerdoMapper {

    /** Convierte entidad a response DTO. */
    CatTratadoAcuerdoResponse toResponse(CatTratadoAcuerdo entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatTratadoAcuerdoResponse> toResponseList(List<CatTratadoAcuerdo> entities);

    /** Convierte request DTO a entidad. */
    CatTratadoAcuerdo toEntity(CatTratadoAcuerdoRequest request);
}
