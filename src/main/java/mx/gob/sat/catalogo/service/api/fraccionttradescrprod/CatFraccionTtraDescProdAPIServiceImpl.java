package mx.gob.sat.catalogo.service.api.fraccionttradescrprod;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.fraccionttradescrprod.CatFraccionTtraDescProdRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.fraccionttradescrprod.CatFraccionTtraDescProdResponse;
import mx.gob.sat.catalogo.model.entity.CatFraccionTtraDescProd;
import mx.gob.sat.catalogo.repository.CatDescripcionProdRepository;
import mx.gob.sat.catalogo.repository.CatFraccionTtraDescProdRepository;
import mx.gob.sat.catalogo.repository.CatFraccionTtraRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatFraccionTtraDescProdMapper;
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
class CatFraccionTtraDescProdAPIServiceImpl implements CatFraccionTtraDescProdAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of("idFraccionTtraDescProd", "idFraccionTtraDescProd");
    private static final String DEFAULT_SORT = "idFraccionTtraDescProd";

    private final CatFraccionTtraDescProdRepository fraccionTtraDescProdRepository;
    private final CatDescripcionProdRepository descripcionProdRepository;
    private final CatFraccionTtraRepository fraccionTtraRepository;
    private final CatFraccionTtraDescProdMapper fraccionTtraDescProdMapper;

    @Override
    public BaseResponse<PaginaResponse<CatFraccionTtraDescProdResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatFraccionTtraDescProd> paginaResultado = fraccionTtraDescProdRepository.findAll(pageable);
        final List<CatFraccionTtraDescProdResponse> contenido = fraccionTtraDescProdMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatFraccionTtraDescProdResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatFraccionTtraDescProdResponse> findById(final Long idFraccionTtraDescProd) {
        final Optional<CatFraccionTtraDescProd> resultado = fraccionTtraDescProdRepository.findById(idFraccionTtraDescProd);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.FRACCION_TTRA_DESC_PROD_NO_ENCONTRADA, "Fraccion Ttra Desc Prod no encontrada: " + idFraccionTtraDescProd);
        }
        return exito(fraccionTtraDescProdMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatFraccionTtraDescProdResponse> crear(final CatFraccionTtraDescProdRequest request) {
        final CatFraccionTtraDescProd entidad = new CatFraccionTtraDescProd();
        entidad.setIdFraccionTtraDescProd(request.getIdFraccionTtraDescProd());
        if (request.getIdDescripcionProd() != null) {
            descripcionProdRepository.findById(request.getIdDescripcionProd()).ifPresent(entidad::setIdDescripcionProd);
        }
        if (request.getIdFraccionGob() != null) {
            fraccionTtraRepository.findById(request.getIdFraccionGob()).ifPresent(entidad::setIdFraccionGob);
        }
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        final CatFraccionTtraDescProd guardada = fraccionTtraDescProdRepository.save(entidad);
        log.info("Fraccion Ttra Desc Prod creada: {}", guardada.getIdFraccionTtraDescProd());
        return exito(fraccionTtraDescProdMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatFraccionTtraDescProdResponse> actualizar(final Long idFraccionTtraDescProd, final CatFraccionTtraDescProdRequest request) {
        final Optional<CatFraccionTtraDescProd> opt = fraccionTtraDescProdRepository.findById(idFraccionTtraDescProd);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.FRACCION_TTRA_DESC_PROD_NO_ENCONTRADA, "Fraccion Ttra Desc Prod no encontrada: " + idFraccionTtraDescProd);
        }
        final CatFraccionTtraDescProd entidad = opt.get();
        if (request.getIdDescripcionProd() != null) {
            descripcionProdRepository.findById(request.getIdDescripcionProd()).ifPresent(entidad::setIdDescripcionProd);
        }
        if (request.getIdFraccionGob() != null) {
            fraccionTtraRepository.findById(request.getIdFraccionGob()).ifPresent(entidad::setIdFraccionGob);
        }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(fraccionTtraDescProdMapper.toResponse(fraccionTtraDescProdRepository.save(entidad)));
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
