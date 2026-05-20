package mx.gob.sat.catalogo.service.api.tipocertificado;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.tipocertificado.CatTipoCertificadoRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tipocertificado.CatTipoCertificadoResponse;

public interface CatTipoCertificadoAPIService {

    BaseResponse<PaginaResponse<CatTipoCertificadoResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    BaseResponse<CatTipoCertificadoResponse> findById(Long idTipoCertificado);

    BaseResponse<CatTipoCertificadoResponse> crear(CatTipoCertificadoRequest request);

    BaseResponse<CatTipoCertificadoResponse> actualizar(Long idTipoCertificado, CatTipoCertificadoRequest request);
}
