package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.entidad.CatEntidadRequest;
import mx.gob.sat.catalogo.controller.response.aduana.CatEntidadResponse;
import mx.gob.sat.catalogo.model.entity.CatEntidad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * <b>Class:</b> CatEntidadMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para convertir entidades federativas a DTOs y viceversa.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CatEntidadMapper {

    /**
     * Convierte una entidad {@code CatEntidad} a su DTO de respuesta.
     * La clave del pais se extrae del objeto relacionado.
     *
     * @param entidad Entidad JPA de entidad federativa.
     * @return DTO de respuesta.
     */
    @Mapping(target = "cvePais",
            expression = "java(entidad.getCvePais() != null ? entidad.getCvePais().getCvePais() : null)")
    CatEntidadResponse toResponse(CatEntidad entidad);

    /**
     * Convierte una lista de entidades a lista de DTOs de respuesta.
     *
     * @param entidades Lista de entidades JPA.
     * @return Lista de DTOs de respuesta.
     */
    List<CatEntidadResponse> toResponseList(List<CatEntidad> entidades);

    /**
     * Convierte un DTO de request a entidad JPA.
     * El campo {@code cvePais} (relacion ManyToOne) debe asignarse manualmente en el servicio.
     *
     * @param request DTO de request con los datos de la entidad.
     * @return Entidad JPA parcialmente poblada.
     */
    @Mapping(target = "cvePais", ignore = true)
    CatEntidad toEntity(CatEntidadRequest request);
}
