package mx.gob.sat.catalogo.service.api.catalogodtr;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.catalogodtr.CatCatalogoDTrRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.catalogodtr.CatCatalogoDTrResponse;
import mx.gob.sat.catalogo.model.entity.CatCatalogoDTr;
import mx.gob.sat.catalogo.model.entity.CatCatalogoDTrId;
import mx.gob.sat.catalogo.repository.CatCatalogoDRepository;
import mx.gob.sat.catalogo.repository.CatCatalogoDTrRepository;
import mx.gob.sat.catalogo.repository.CatLenguajeRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatCatalogoDTrMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatCatalogoDTrAPIServiceImpl implements CatCatalogoDTrAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveCatalogo", "id.cveCatalogo",
            "cveLenguaje", "id.cveLenguaje");
    private static final String DEFAULT_SORT = "id.cveCatalogo";

    private final CatCatalogoDTrRepository catalogoDTrRepository;
    private final CatCatalogoDRepository catalogoDRepository;
    private final CatLenguajeRepository lenguajeRepository;
    private final CatCatalogoDTrMapper catalogoDTrMapper;

    @Override
    public BaseResponse<PaginaResponse<CatCatalogoDTrResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatCatalogoDTr> paginaResultado = catalogoDTrRepository.findAll(pageable);
        final List<CatCatalogoDTrResponse> contenido = catalogoDTrMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatCatalogoDTrResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatCatalogoDTrResponse> findById(final String cveCatalogo, final String cveLenguaje) {
        final CatCatalogoDTrId id = new CatCatalogoDTrId();
        id.setCveCatalogo(cveCatalogo);
        id.setCveLenguaje(cveLenguaje);
        final Optional<CatCatalogoDTr> resultado = catalogoDTrRepository.findById(id);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.CATALOGO_D_TR_NO_ENCONTRADO, "Catalogo D Tr no encontrado: " + cveCatalogo + "/" + cveLenguaje);
        }
        return exito(catalogoDTrMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatCatalogoDTrResponse> crear(final CatCatalogoDTrRequest request) {
        final CatCatalogoDTr entidad = new CatCatalogoDTr();
        final CatCatalogoDTrId id = new CatCatalogoDTrId();
        id.setCveCatalogo(request.getCveCatalogo());
        id.setCveLenguaje(request.getCveLenguaje());
        entidad.setId(id);
        entidad.setDescripcion(request.getDescripcion());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        final CatCatalogoDTr guardado = catalogoDTrRepository.save(entidad);
        log.info("Catalogo D Tr creado: {}/{}", guardado.getId().getCveCatalogo(), guardado.getId().getCveLenguaje());
        return exito(catalogoDTrMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatCatalogoDTrResponse> actualizar(final String cveCatalogo, final String cveLenguaje, final CatCatalogoDTrRequest request) {
        final CatCatalogoDTrId id = new CatCatalogoDTrId();
        id.setCveCatalogo(cveCatalogo);
        id.setCveLenguaje(cveLenguaje);
        final Optional<CatCatalogoDTr> opt = catalogoDTrRepository.findById(id);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.CATALOGO_D_TR_NO_ENCONTRADO, "Catalogo D Tr no encontrado: " + cveCatalogo + "/" + cveLenguaje);
        }
        final CatCatalogoDTr entidad = opt.get();
        if (request.getDescripcion() != null) { entidad.setDescripcion(request.getDescripcion()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(catalogoDTrMapper.toResponse(catalogoDTrRepository.save(entidad)));
    }

    private <T> BaseResponse<T> exito(final T resultado) {
        final BaseResponse<T> r = new BaseResponse<>();
        r.setCodigo(GeneralConst.CODIGO_EXITO);
        r.setMensaje(GeneralConst.MENSAJE_OPERACION_EXITOSA);
        r.setResultado(resultado);
        return r;
    }

    private <T> BaseResponse<T> error(final String codigo, final String mensaje) {
        final BaseResponse<T> r = new BaseResponse<>();
        r.setCodigo(codigo);
        r.setMensaje(GeneralConst.MENSAJE_OPERACION_FALLIDA);
        r.setError(mensaje);
        return r;
    }
}
