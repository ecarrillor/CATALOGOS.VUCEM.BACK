package mx.gob.sat.catalogo.service.api.montoexportacion;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.montoexportacion.CatMontoExportacionRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.montoexportacion.CatMontoExportacionResponse;
import mx.gob.sat.catalogo.model.entity.CatMontoExportacion;
import mx.gob.sat.catalogo.model.entity.CatMontoExportacionId;
import mx.gob.sat.catalogo.repository.CatMontoExportacionRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatMontoExportacionMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatMontoExportacionAPIServiceImpl implements CatMontoExportacionAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of("rfcExportador", "id.rfcExportador");
    private static final String DEFAULT_SORT = "id.rfcExportador";

    private final CatMontoExportacionRepository montoExportacionRepository;
    private final CatMontoExportacionMapper montoExportacionMapper;

    @Override
    public BaseResponse<PaginaResponse<CatMontoExportacionResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted() ? PageRequest.of(pagina, tamano, sort) : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatMontoExportacion> paginaResultado = montoExportacionRepository.findAll(pageable);
        final List<CatMontoExportacionResponse> contenido = montoExportacionMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatMontoExportacionResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatMontoExportacionResponse> findById(final String rfcExportador, final LocalDate fecMontoExportacion) {
        final CatMontoExportacionId id = new CatMontoExportacionId();
        id.setRfcExportador(rfcExportador);
        id.setFecMontoExportacion(fecMontoExportacion);
        final Optional<CatMontoExportacion> resultado = montoExportacionRepository.findById(id);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.MONTO_EXPORTACION_NO_ENCONTRADO, "Monto Exportacion no encontrado: " + rfcExportador + "/" + fecMontoExportacion);
        }
        return exito(montoExportacionMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatMontoExportacionResponse> crear(final CatMontoExportacionRequest request) {
        final CatMontoExportacion entidad = new CatMontoExportacion();
        final CatMontoExportacionId id = new CatMontoExportacionId();
        id.setRfcExportador(request.getRfcExportador());
        id.setFecMontoExportacion(request.getFecMontoExportacion());
        entidad.setId(id);
        entidad.setRazonSocial(request.getRazonSocial());
        entidad.setMonto(request.getMonto());
        entidad.setFecModificacion(request.getFecModificacion());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        final CatMontoExportacion guardado = montoExportacionRepository.save(entidad);
        log.info("Monto Exportacion creado: {}/{}", guardado.getId().getRfcExportador(), guardado.getId().getFecMontoExportacion());
        return exito(montoExportacionMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatMontoExportacionResponse> actualizar(final String rfcExportador, final LocalDate fecMontoExportacion, final CatMontoExportacionRequest request) {
        final CatMontoExportacionId id = new CatMontoExportacionId();
        id.setRfcExportador(rfcExportador);
        id.setFecMontoExportacion(fecMontoExportacion);
        final Optional<CatMontoExportacion> opt = montoExportacionRepository.findById(id);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.MONTO_EXPORTACION_NO_ENCONTRADO, "Monto Exportacion no encontrado: " + rfcExportador + "/" + fecMontoExportacion);
        }
        final CatMontoExportacion entidad = opt.get();
        if (request.getRazonSocial() != null) { entidad.setRazonSocial(request.getRazonSocial()); }
        if (request.getMonto() != null) { entidad.setMonto(request.getMonto()); }
        if (request.getFecModificacion() != null) { entidad.setFecModificacion(request.getFecModificacion()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(montoExportacionMapper.toResponse(montoExportacionRepository.save(entidad)));
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
