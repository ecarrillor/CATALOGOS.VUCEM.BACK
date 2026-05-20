package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.fundamentottra.CatFundamentoTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatFundamentoTtra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatFundamentoTtraMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de FundamentoTtra.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatFundamentoTtraMapper {

    /** Convierte entidad a response DTO. */
    @Mapping(source = "idTipoTramite.idTipoTramite", target = "idTipoTramite")
    CatFundamentoTtraResponse toResponse(CatFundamentoTtra entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatFundamentoTtraResponse> toResponseList(List<CatFundamentoTtra> entities);
}
