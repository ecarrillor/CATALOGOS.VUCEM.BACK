package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.tipocertificado.CatTipoCertificadoResponse;
import mx.gob.sat.catalogo.model.entity.CatTipoCertificado;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatTipoCertificadoMapper {

    CatTipoCertificadoResponse toResponse(CatTipoCertificado entity);

    List<CatTipoCertificadoResponse> toResponseList(List<CatTipoCertificado> entities);
}
