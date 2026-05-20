package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.tipoempresarecif.CatTipoEmpresaRecifResponse;
import mx.gob.sat.catalogo.model.entity.CatTipoEmpresaRecif;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatTipoEmpresaRecifMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de tipos de empresa RECIF.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatTipoEmpresaRecifMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatTipoEmpresaRecif.
     * @return Response DTO.
     */
    @Mapping(source = "cveTipoEmpresaRecifR.cveTipoEmpresaRecif", target = "cveTipoEmpresaRecifR")
    CatTipoEmpresaRecifResponse toResponse(CatTipoEmpresaRecif entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatTipoEmpresaRecifResponse> toResponseList(List<CatTipoEmpresaRecif> entities);
}
