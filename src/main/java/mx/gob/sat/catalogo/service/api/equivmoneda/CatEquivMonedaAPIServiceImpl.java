package mx.gob.sat.catalogo.service.api.equivmoneda;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.equivmoneda.CatEquivMonedaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.equivmoneda.CatEquivMonedaResponse;
import mx.gob.sat.catalogo.model.entity.CatEquivMoneda;
import mx.gob.sat.catalogo.model.entity.CatMoneda;
import mx.gob.sat.catalogo.repository.CatEquivMonedaRepository;
import mx.gob.sat.catalogo.repository.CatMonedaRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatEquivMonedaMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatEquivMonedaAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de equivalencias de moneda.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Implementacion del servicio API
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatEquivMonedaAPIServiceImpl implements CatEquivMonedaAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idEquivMoneda", "idEquivMoneda",
            "valorConversion", "valorConversion");

    private static final String DEFAULT_SORT = "idEquivMoneda";

    private final CatEquivMonedaRepository equivMonedaRepository;
    private final CatMonedaRepository monedaRepository;
    private final CatEquivMonedaMapper equivMonedaMapper;

    @Override
    public BaseResponse<PaginaResponse<CatEquivMonedaResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatEquivMoneda> paginaResultado = equivMonedaRepository.findAll(pageable);

        final List<CatEquivMonedaResponse> contenido =
                equivMonedaMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatEquivMonedaResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatEquivMonedaResponse> findById(final Integer idEquivMoneda) {
        final Optional<CatEquivMoneda> resultado = equivMonedaRepository.findById(idEquivMoneda);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.EQUIV_MONEDA_NO_ENCONTRADA,
                    "Equivalencia de moneda no encontrada: " + idEquivMoneda);
        }
        return exito(equivMonedaMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatEquivMonedaResponse> crear(final CatEquivMonedaRequest request) {
        final CatEquivMoneda entidad = new CatEquivMoneda();
        entidad.setValorConversion(request.getValorConversion());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());

        final Optional<CatMoneda> monedaOrigen = monedaRepository.findById(request.getCveMonedaOrigen());
        if (monedaOrigen.isEmpty()) {
            return error(CodigoErrorConst.MONEDA_NO_ENCONTRADA,
                    "Moneda origen no encontrada: " + request.getCveMonedaOrigen());
        }
        entidad.setCveMonedaOrigen(monedaOrigen.get());

        final Optional<CatMoneda> monedaDestino = monedaRepository.findById(request.getCveMonedaDestino());
        if (monedaDestino.isEmpty()) {
            return error(CodigoErrorConst.MONEDA_NO_ENCONTRADA,
                    "Moneda destino no encontrada: " + request.getCveMonedaDestino());
        }
        entidad.setCveMonedaDestino(monedaDestino.get());

        final CatEquivMoneda guardada = equivMonedaRepository.save(entidad);
        log.info("Equivalencia de moneda creada: {}", guardada.getIdEquivMoneda());
        return exito(equivMonedaMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatEquivMonedaResponse> actualizar(
            final Integer idEquivMoneda, final CatEquivMonedaRequest request) {

        final Optional<CatEquivMoneda> opt = equivMonedaRepository.findById(idEquivMoneda);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.EQUIV_MONEDA_NO_ENCONTRADA,
                    "Equivalencia de moneda no encontrada: " + idEquivMoneda);
        }
        final CatEquivMoneda entidad = opt.get();
        if (request.getValorConversion() != null) {
            entidad.setValorConversion(request.getValorConversion());
        }
        if (request.getFecIniVigencia() != null) {
            entidad.setFecIniVigencia(request.getFecIniVigencia());
        }
        if (request.getFecFinVigencia() != null) {
            entidad.setFecFinVigencia(request.getFecFinVigencia());
        }
        if (request.getBlnActivo() != null) {
            entidad.setBlnActivo(request.getBlnActivo());
        }
        if (request.getCveMonedaOrigen() != null) {
            final Optional<CatMoneda> monedaOrigen = monedaRepository.findById(request.getCveMonedaOrigen());
            if (monedaOrigen.isEmpty()) {
                return error(CodigoErrorConst.MONEDA_NO_ENCONTRADA,
                        "Moneda origen no encontrada: " + request.getCveMonedaOrigen());
            }
            entidad.setCveMonedaOrigen(monedaOrigen.get());
        }
        if (request.getCveMonedaDestino() != null) {
            final Optional<CatMoneda> monedaDestino = monedaRepository.findById(request.getCveMonedaDestino());
            if (monedaDestino.isEmpty()) {
                return error(CodigoErrorConst.MONEDA_NO_ENCONTRADA,
                        "Moneda destino no encontrada: " + request.getCveMonedaDestino());
            }
            entidad.setCveMonedaDestino(monedaDestino.get());
        }
        return exito(equivMonedaMapper.toResponse(equivMonedaRepository.save(entidad)));
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
