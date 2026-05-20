package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.unidadadminaduana.CatUnidadAdminAduanaResponse;
import mx.gob.sat.catalogo.model.entity.CatUnidadAdminAduana;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

/**
 * <b>Interface:</b> CatUnidadAdminAduanaMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de unidades administrativas de aduana.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatUnidadAdminAduanaMapper {
    @Mapping(source = "id.cveUnidadAdministrativa", target = "cveUnidadAdministrativa")
    @Mapping(source = "id.cveAduana", target = "cveAduana")
    CatUnidadAdminAduanaResponse toResponse(CatUnidadAdminAduana entity);
    List<CatUnidadAdminAduanaResponse> toResponseList(List<CatUnidadAdminAduana> entities);
}
