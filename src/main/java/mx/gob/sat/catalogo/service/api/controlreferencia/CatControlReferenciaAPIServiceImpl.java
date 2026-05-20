package mx.gob.sat.catalogo.service.api.controlreferencia;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.controlreferencia.CatControlReferenciaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.controlreferencia.CatControlReferenciaResponse;
import mx.gob.sat.catalogo.model.entity.CatControlReferencia;
import mx.gob.sat.catalogo.repository.CatControlReferenciaRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatControlReferenciaMapper;
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
class CatControlReferenciaAPIServiceImpl implements CatControlReferenciaAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idControlReferencia", "idControlReferencia"
    );
    private static final String DEFAULT_SORT = "idControlReferencia";

    private final CatControlReferenciaRepository controlReferenciaRepository;
    private final CatControlReferenciaMapper controlReferenciaMapper;

    @Override
    public BaseResponse<PaginaResponse<CatControlReferenciaResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatControlReferencia> paginaResultado = controlReferenciaRepository.findAll(pageable);
        final List<CatControlReferenciaResponse> contenido = controlReferenciaMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatControlReferenciaResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatControlReferenciaResponse> findById(final Integer idControlReferencia) {
        final Optional<CatControlReferencia> resultado = controlReferenciaRepository.findById(idControlReferencia);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.CONTROL_REFERENCIA_NO_ENCONTRADO, "Control de Referencia no encontrado: " + idControlReferencia);
        }
        return exito(controlReferenciaMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatControlReferenciaResponse> crear(final CatControlReferenciaRequest request) {
        final CatControlReferencia entidad = new CatControlReferencia();
        entidad.setIdControlReferencia(request.getIdControlReferencia());
        entidad.setIdeTipoPresentacion(request.getIdeTipoPresentacion());
        entidad.setIdeSubtipoPresentacion(request.getIdeSubtipoPresentacion());
        entidad.setMinimo(request.getMinimo());
        entidad.setMaximo(request.getMaximo());
        entidad.setCantidadPresentacion(request.getCantidadPresentacion());
        entidad.setTamanioMuestra(request.getTamanioMuestra());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        final CatControlReferencia guardado = controlReferenciaRepository.save(entidad);
        log.info("Control de Referencia creado: {}", guardado.getIdControlReferencia());
        return exito(controlReferenciaMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatControlReferenciaResponse> actualizar(final Integer idControlReferencia, final CatControlReferenciaRequest request) {
        final Optional<CatControlReferencia> opt = controlReferenciaRepository.findById(idControlReferencia);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.CONTROL_REFERENCIA_NO_ENCONTRADO, "Control de Referencia no encontrado: " + idControlReferencia);
        }
        final CatControlReferencia entidad = opt.get();
        if (request.getIdeTipoPresentacion() != null) { entidad.setIdeTipoPresentacion(request.getIdeTipoPresentacion()); }
        if (request.getIdeSubtipoPresentacion() != null) { entidad.setIdeSubtipoPresentacion(request.getIdeSubtipoPresentacion()); }
        if (request.getMinimo() != null) { entidad.setMinimo(request.getMinimo()); }
        if (request.getMaximo() != null) { entidad.setMaximo(request.getMaximo()); }
        if (request.getCantidadPresentacion() != null) { entidad.setCantidadPresentacion(request.getCantidadPresentacion()); }
        if (request.getTamanioMuestra() != null) { entidad.setTamanioMuestra(request.getTamanioMuestra()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(controlReferenciaMapper.toResponse(controlReferenciaRepository.save(entidad)));
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
