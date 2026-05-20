package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.tratadobloque.CatTratadoBloqueResponse;
import mx.gob.sat.catalogo.model.entity.CatTratadoBloque;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

/**
 * Mapper para el catalogo de tratado bloque.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Mapper(componentModel = "spring")
public interface CatTratadoBloqueMapper {
    @Mapping(source = "id.idTratadoAcuerdo", target = "idTratadoAcuerdo")
    @Mapping(source = "id.idBloque", target = "idBloque")
    CatTratadoBloqueResponse toResponse(CatTratadoBloque entity);
    List<CatTratadoBloqueResponse> toResponseList(List<CatTratadoBloque> entities);
}
