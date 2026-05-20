package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.unidadadminvecina.CatUnidadAdminVecinaResponse;
import mx.gob.sat.catalogo.model.entity.CatUnidadAdminVecina;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

/**
 * <b>Interface:</b> CatUnidadAdminVecinaMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de unidades administrativas vecinas.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatUnidadAdminVecinaMapper {
    @Mapping(source = "id.cveUnidadAdministrativa", target = "cveUnidadAdministrativa")
    @Mapping(source = "id.cveEntidad", target = "cveEntidad")
    CatUnidadAdminVecinaResponse toResponse(CatUnidadAdminVecina entity);
    List<CatUnidadAdminVecinaResponse> toResponseList(List<CatUnidadAdminVecina> entities);
}
