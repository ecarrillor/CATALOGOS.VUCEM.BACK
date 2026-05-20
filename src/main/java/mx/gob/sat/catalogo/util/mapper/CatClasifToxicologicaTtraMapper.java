package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.clasiftoxicologicattra.CatClasifToxicologicaTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatClasifToxicologicaTtra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatClasifToxicologicaTtraMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de ClasifToxicologicaTtra.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatClasifToxicologicaTtraMapper {

    /** Convierte entidad a response DTO. */
    @Mapping(source = "idTipoTramite.idTipoTramite", target = "idTipoTramite")
    CatClasifToxicologicaTtraResponse toResponse(CatClasifToxicologicaTtra entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatClasifToxicologicaTtraResponse> toResponseList(List<CatClasifToxicologicaTtra> entities);
}
