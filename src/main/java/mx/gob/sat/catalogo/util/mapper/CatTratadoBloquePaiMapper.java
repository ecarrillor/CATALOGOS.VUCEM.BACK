package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.tratadobloquepai.CatTratadoBloquePaiResponse;
import mx.gob.sat.catalogo.model.entity.CatTratadoBloquePai;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

/**
 * Mapper para el catalogo de tratado bloque pais.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Mapper(componentModel = "spring")
public interface CatTratadoBloquePaiMapper {
    @Mapping(source = "id.cvePais", target = "cvePais")
    @Mapping(source = "id.idTratadoAcuerdo", target = "idTratadoAcuerdo")
    CatTratadoBloquePaiResponse toResponse(CatTratadoBloquePai entity);
    List<CatTratadoBloquePaiResponse> toResponseList(List<CatTratadoBloquePai> entities);
}
