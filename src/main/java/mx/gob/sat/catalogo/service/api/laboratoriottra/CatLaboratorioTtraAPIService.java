package mx.gob.sat.catalogo.service.api.laboratoriottra;

import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.catalogo.controller.request.laboratoriottra.CatLaboratorioTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.laboratoriottra.CatLaboratorioTtraResponse;

public interface CatLaboratorioTtraAPIService {

    BaseResponse<PaginaResponse<CatLaboratorioTtraResponse>> listar(int pagina, int tamano, String busqueda, String sortBy, String sortDir);

    BaseResponse<CatLaboratorioTtraResponse> findById(Long idLaboratorioTtra);

    BaseResponse<CatLaboratorioTtraResponse> crear(CatLaboratorioTtraRequest request);

    BaseResponse<CatLaboratorioTtraResponse> actualizar(Long idLaboratorioTtra, CatLaboratorioTtraRequest request);
}
