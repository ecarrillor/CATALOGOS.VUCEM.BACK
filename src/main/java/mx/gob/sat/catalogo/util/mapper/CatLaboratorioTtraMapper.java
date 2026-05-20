package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.laboratoriottra.CatLaboratorioTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatLaboratorioTtra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatLaboratorioTtraMapper {

    @Mapping(source = "idTipoTramite.idTipoTramite", target = "idTipoTramite")
    CatLaboratorioTtraResponse toResponse(CatLaboratorioTtra entity);

    List<CatLaboratorioTtraResponse> toResponseList(List<CatLaboratorioTtra> entities);
}
