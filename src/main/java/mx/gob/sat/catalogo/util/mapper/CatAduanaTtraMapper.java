package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.aduanattra.CatAduanaTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatAduanaTtra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatAduanaTtraMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de aduanas ttra.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatAduanaTtraMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatAduanaTtra.
     * @return Response DTO.
     */
    @Mapping(source = "cveAduana.cveAduana", target = "cveAduana")
    @Mapping(source = "idTipoTramite.idTipoTramite", target = "idTipoTramite")
    CatAduanaTtraResponse toResponse(CatAduanaTtra entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatAduanaTtraResponse> toResponseList(List<CatAduanaTtra> entities);
}
