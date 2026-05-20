package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.paistratadoacuerdo.CatPaisTratadoAcuerdoResponse;
import mx.gob.sat.catalogo.model.entity.CatPaisTratadoAcuerdo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

/**
 * Mapper para el catalogo de pais tratado acuerdo.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Mapper(componentModel = "spring")
public interface CatPaisTratadoAcuerdoMapper {
    @Mapping(source = "id.cvePais", target = "cvePais")
    @Mapping(source = "id.idTratadoAcuerdo", target = "idTratadoAcuerdo")
    CatPaisTratadoAcuerdoResponse toResponse(CatPaisTratadoAcuerdo entity);
    List<CatPaisTratadoAcuerdoResponse> toResponseList(List<CatPaisTratadoAcuerdo> entities);
}
