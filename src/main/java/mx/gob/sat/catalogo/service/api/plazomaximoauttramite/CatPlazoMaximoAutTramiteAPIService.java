package mx.gob.sat.catalogo.service.api.plazomaximoauttramite;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.plazomaximoauttramite.CatPlazoMaximoAutTramiteRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.plazomaximoauttramite.CatPlazoMaximoAutTramiteResponse;
import java.time.LocalDate;

public interface CatPlazoMaximoAutTramiteAPIService {
    BaseResponse<PaginaResponse<CatPlazoMaximoAutTramiteResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);
    BaseResponse<CatPlazoMaximoAutTramiteResponse> findById(Long idTipoTramite, LocalDate fecIniVigencia);
    BaseResponse<CatPlazoMaximoAutTramiteResponse> crear(CatPlazoMaximoAutTramiteRequest request);
    BaseResponse<CatPlazoMaximoAutTramiteResponse> actualizar(Long idTipoTramite, LocalDate fecIniVigencia, CatPlazoMaximoAutTramiteRequest request);
}
