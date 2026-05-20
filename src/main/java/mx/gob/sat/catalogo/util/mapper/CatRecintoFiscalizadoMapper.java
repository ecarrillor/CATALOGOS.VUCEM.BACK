package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.recintofiscalizado.CatRecintoFiscalizadoResponse;
import mx.gob.sat.catalogo.model.entity.CatRecintoFiscalizado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatRecintoFiscalizadoMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de recintos fiscalizados.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatRecintoFiscalizadoMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatRecintoFiscalizado.
     * @return Response DTO.
     */
    @Mapping(source = "cveAduana.cveAduana", target = "cveAduana")
    @Mapping(source = "cveAduana.nombre", target = "nombreAduana")
    CatRecintoFiscalizadoResponse toResponse(CatRecintoFiscalizado entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatRecintoFiscalizadoResponse> toResponseList(List<CatRecintoFiscalizado> entities);
}
