package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.banco.CatBancoRequest;
import mx.gob.sat.catalogo.controller.response.banco.CatBancoResponse;
import mx.gob.sat.catalogo.model.entity.CatBanco;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatBancoMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de bancos.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatBancoMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatBanco.
     * @return Response DTO.
     */
    CatBancoResponse toResponse(CatBanco entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatBancoResponse> toResponseList(List<CatBanco> entities);

    /**
     * Convierte request DTO a entidad.
     *
     * @param request Request DTO.
     * @return Entidad CatBanco.
     */
    CatBanco toEntity(CatBancoRequest request);
}
