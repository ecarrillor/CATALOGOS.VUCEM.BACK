package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.aprobcertse.CatAprobCertSeResponse;
import mx.gob.sat.catalogo.model.entity.CatAprobCertSe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatAprobCertSeMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de aprobaciones de certificado SE.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatAprobCertSeMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatAprobCertSe.
     * @return Response DTO.
     */
    @Mapping(source = "cveUnidadAdministrativa.cveUnidadAdministrativa", target = "cveUnidadAdministrativa")
    CatAprobCertSeResponse toResponse(CatAprobCertSe entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatAprobCertSeResponse> toResponseList(List<CatAprobCertSe> entities);
}
