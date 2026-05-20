package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.pais.CatPaisResponse;
import mx.gob.sat.catalogo.model.entity.CatPais;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * <b>Class:</b> CatPaisMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para convertir entidades de pais a DTOs de respuesta.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CatPaisMapper {

    /**
     * Convierte una entidad {@code CatPais} a su DTO de respuesta.
     * Extrae cveMoneda y nombreMoneda del objeto relacionado.
     *
     * @param entidad Entidad JPA de pais.
     * @return DTO de respuesta.
     */
    @Mapping(target = "cveMoneda",
            expression = "java(entidad.getCveMoneda() != null ? entidad.getCveMoneda().getCveMoneda() : null)")
    @Mapping(target = "nombreMoneda",
            expression = "java(entidad.getCveMoneda() != null ? entidad.getCveMoneda().getNombre() : null)")
    CatPaisResponse toResponse(CatPais entidad);

    /**
     * Convierte una lista de entidades a lista de DTOs de respuesta.
     *
     * @param entidades Lista de entidades JPA.
     * @return Lista de DTOs de respuesta.
     */
    List<CatPaisResponse> toResponseList(List<CatPais> entidades);
}
