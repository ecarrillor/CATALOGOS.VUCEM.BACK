package mx.gob.sat.catalogo.service.api.restricdescprod;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.restricdescprod.CatRestricDescProdRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.restricdescprod.CatRestricDescProdResponse;
import mx.gob.sat.catalogo.model.entity.CatRestricDescProd;
import mx.gob.sat.catalogo.repository.CatDescripcionProdRepository;
import mx.gob.sat.catalogo.repository.CatRestricDescProdRepository;
import mx.gob.sat.catalogo.repository.CatRestriccionTtraRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatRestricDescProdMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatRestricDescProdAPIServiceImpl implements CatRestricDescProdAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of("idRestricDescProd", "idRestricDescProd");
    private static final String DEFAULT_SORT = "idRestricDescProd";

    private final CatRestricDescProdRepository restricDescProdRepository;
    private final CatRestricDescProdMapper restricDescProdMapper;
    private final CatRestriccionTtraRepository restriccionTtraRepository;
    private final CatDescripcionProdRepository descripcionProdRepository;

    @Override
    public BaseResponse<PaginaResponse<CatRestricDescProdResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatRestricDescProd> paginaResultado = restricDescProdRepository.findAll(pageable);
        final List<CatRestricDescProdResponse> contenido = restricDescProdMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatRestricDescProdResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatRestricDescProdResponse> findById(final Long idRestricDescProd) {
        final Optional<CatRestricDescProd> resultado = restricDescProdRepository.findById(idRestricDescProd);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.RESTRIC_DESC_PROD_NO_ENCONTRADO, "Restric Desc Prod no encontrado: " + idRestricDescProd);
        }
        return exito(restricDescProdMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatRestricDescProdResponse> crear(final CatRestricDescProdRequest request) {
        final CatRestricDescProd entidad = new CatRestricDescProd();
        entidad.setIdRestricDescProd(request.getIdRestricDescProd());
        if (request.getIdRestriccionTtra() != null) { restriccionTtraRepository.findById(request.getIdRestriccionTtra()).ifPresent(entidad::setIdRestriccionTtra); }
        if (request.getIdDescripcionProd() != null) { descripcionProdRepository.findById(request.getIdDescripcionProd()).ifPresent(entidad::setIdDescripcionProd); }
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        final CatRestricDescProd guardado = restricDescProdRepository.save(entidad);
        log.info("Restric Desc Prod creado: {}", guardado.getIdRestricDescProd());
        return exito(restricDescProdMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatRestricDescProdResponse> actualizar(final Long idRestricDescProd, final CatRestricDescProdRequest request) {
        final Optional<CatRestricDescProd> opt = restricDescProdRepository.findById(idRestricDescProd);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.RESTRIC_DESC_PROD_NO_ENCONTRADO, "Restric Desc Prod no encontrado: " + idRestricDescProd);
        }
        final CatRestricDescProd entidad = opt.get();
        if (request.getIdRestriccionTtra() != null) { restriccionTtraRepository.findById(request.getIdRestriccionTtra()).ifPresent(entidad::setIdRestriccionTtra); }
        if (request.getIdDescripcionProd() != null) { descripcionProdRepository.findById(request.getIdDescripcionProd()).ifPresent(entidad::setIdDescripcionProd); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(restricDescProdMapper.toResponse(restricDescProdRepository.save(entidad)));
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
