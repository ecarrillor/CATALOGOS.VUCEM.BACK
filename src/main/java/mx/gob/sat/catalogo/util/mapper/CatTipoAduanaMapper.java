package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.tipoaduana.CatTipoAduanaRequest;
import mx.gob.sat.catalogo.controller.response.aduana.CatTipoAduanaResponse;
import mx.gob.sat.catalogo.model.entity.CatTipoAduana;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * <b>Class:</b> CatTipoAduanaMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para convertir entidades de tipo de aduana a DTOs y viceversa.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CatTipoAduanaMapper {

    /**
     * Convierte una entidad {@code CatTipoAduana} a su DTO de respuesta.
     *
     * @param entidad Entidad JPA de tipo de aduana.
     * @return DTO de respuesta.
     */
    CatTipoAduanaResponse toResponse(CatTipoAduana entidad);

    /**
     * Convierte una lista de entidades a lista de DTOs de respuesta.
     *
     * @param entidades Lista de entidades JPA.
     * @return Lista de DTOs de respuesta.
     */
    List<CatTipoAduanaResponse> toResponseList(List<CatTipoAduana> entidades);

    /**
     * Convierte un DTO de request a entidad JPA.
     *
     * @param request DTO de request con los datos del tipo de aduana.
     * @return Entidad JPA.
     */
    @Mapping(target = "cveTipoAduana", source = "cveTipoAduana")
    CatTipoAduana toEntity(CatTipoAduanaRequest request);
}
