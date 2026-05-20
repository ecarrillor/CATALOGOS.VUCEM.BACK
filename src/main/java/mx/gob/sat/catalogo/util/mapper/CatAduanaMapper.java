package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.aduana.CatAduanaRequest;
import mx.gob.sat.catalogo.controller.response.aduana.CatAduanaResponse;
import mx.gob.sat.catalogo.controller.response.aduana.CatEntidadResponse;
import mx.gob.sat.catalogo.controller.response.aduana.CatTipoAduanaResponse;
import mx.gob.sat.catalogo.model.entity.CatAduana;
import mx.gob.sat.catalogo.model.entity.CatEntidad;
import mx.gob.sat.catalogo.model.entity.CatTipoAduana;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * <b>Class:</b> CatAduanaMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para convertir entidades del catalogo de aduanas a DTOs de respuesta
 * y de request a entidad JPA.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CatAduanaMapper {

    /**
     * Convierte una entidad {@code CatAduana} a su DTO de respuesta.
     *
     * @param entidad Entidad JPA de aduana.
     * @return DTO de respuesta con la informacion de la aduana.
     */
    CatAduanaResponse toResponse(CatAduana entidad);

    /**
     * Convierte una lista de entidades {@code CatAduana} a lista de DTOs de respuesta.
     *
     * @param entidades Lista de entidades JPA de aduana.
     * @return Lista de DTOs de respuesta.
     */
    List<CatAduanaResponse> toResponseList(List<CatAduana> entidades);

    /**
     * Convierte una entidad {@code CatTipoAduana} a su DTO de respuesta.
     *
     * @param entidad Entidad JPA de tipo de aduana.
     * @return DTO de respuesta con la informacion del tipo de aduana.
     */
    CatTipoAduanaResponse toTipoAduanaResponse(CatTipoAduana entidad);

    /**
     * Convierte una lista de entidades {@code CatTipoAduana} a lista de DTOs de respuesta.
     *
     * @param entidades Lista de entidades JPA de tipo de aduana.
     * @return Lista de DTOs de respuesta.
     */
    List<CatTipoAduanaResponse> toTipoAduanaResponseList(List<CatTipoAduana> entidades);

    /**
     * Convierte una entidad {@code CatEntidad} a su DTO de respuesta.
     * La clave del pais se extrae del objeto relacionado {@code CatPais}.
     *
     * @param entidad Entidad JPA de entidad federativa.
     * @return DTO de respuesta con la informacion de la entidad.
     */
    @Mapping(target = "cvePais",
            expression = "java(entidad.getCvePais() != null ? entidad.getCvePais().getCvePais() : null)")
    CatEntidadResponse toEntidadResponse(CatEntidad entidad);

    /**
     * Convierte una lista de entidades {@code CatEntidad} a lista de DTOs de respuesta.
     *
     * @param entidades Lista de entidades JPA de entidades federativas.
     * @return Lista de DTOs de respuesta.
     */
    List<CatEntidadResponse> toEntidadResponseList(List<CatEntidad> entidades);

    /**
     * Convierte un DTO de request a entidad JPA {@code CatAduana}.
     * Los campos {@code tipoAduana} y {@code entidad} deben asignarse manualmente en el servicio.
     *
     * @param request DTO de request con los datos de la aduana.
     * @return Entidad JPA parcialmente poblada.
     */
    @Mapping(target = "tipoAduana", ignore = true)
    @Mapping(target = "entidad", ignore = true)
    CatAduana toEntity(CatAduanaRequest request);
}
