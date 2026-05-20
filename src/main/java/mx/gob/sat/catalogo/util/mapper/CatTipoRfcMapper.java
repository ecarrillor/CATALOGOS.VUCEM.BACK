package mx.gob.sat.catalogo.util.mapper;
import mx.gob.sat.catalogo.controller.response.tiporfc.CatTipoRfcResponse;
import mx.gob.sat.catalogo.model.entity.CatTipoRfc;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CatTipoRfcMapper {
    @Mapping(source = "id.rfc", target = "rfc")
    @Mapping(source = "id.ideTipoRfc", target = "ideTipoRfc")
    @Mapping(source = "cveUnidadAdministrativa.cveUnidadAdministrativa", target = "cveUnidadAdministrativa")
    CatTipoRfcResponse toResponse(CatTipoRfc entity);
    List<CatTipoRfcResponse> toResponseList(List<CatTipoRfc> entities);
}
