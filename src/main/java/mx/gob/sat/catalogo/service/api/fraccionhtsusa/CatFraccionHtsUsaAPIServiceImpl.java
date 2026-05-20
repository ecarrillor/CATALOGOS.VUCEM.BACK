package mx.gob.sat.catalogo.service.api.fraccionhtsusa;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.fraccionhtsusa.CatFraccionHtsUsaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.fraccionhtsusa.CatFraccionHtsUsaResponse;
import mx.gob.sat.catalogo.model.entity.CatFraccionHtsUsa;
import mx.gob.sat.catalogo.model.entity.CatUnidadMedida;
import mx.gob.sat.catalogo.repository.CatFraccionHtsUsaRepository;
import mx.gob.sat.catalogo.repository.CatUnidadMedidaRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatFraccionHtsUsaMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatFraccionHtsUsaAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de fracciones HTS USA.</p>
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
class CatFraccionHtsUsaAPIServiceImpl implements CatFraccionHtsUsaAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idFraccionHtsUsa", "idFraccionHtsUsa",
            "cveFraccionHtsUsa", "cveFraccionHtsUsa");

    private static final String DEFAULT_SORT = "idFraccionHtsUsa";

    private final CatFraccionHtsUsaRepository fraccionHtsUsaRepository;
    private final CatUnidadMedidaRepository unidadMedidaRepository;
    private final CatFraccionHtsUsaMapper fraccionHtsUsaMapper;

    @Override
    public BaseResponse<PaginaResponse<CatFraccionHtsUsaResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatFraccionHtsUsa> paginaResultado = fraccionHtsUsaRepository.findAll(pageable);

        final List<CatFraccionHtsUsaResponse> contenido =
                fraccionHtsUsaMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatFraccionHtsUsaResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatFraccionHtsUsaResponse> findById(final Long idFraccionHtsUsa) {
        final Optional<CatFraccionHtsUsa> resultado = fraccionHtsUsaRepository.findById(idFraccionHtsUsa);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.FRACCION_HTS_USA_NO_ENCONTRADA,
                    "Fraccion HTS USA no encontrada: " + idFraccionHtsUsa);
        }
        return exito(fraccionHtsUsaMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatFraccionHtsUsaResponse> crear(final CatFraccionHtsUsaRequest request) {
        final CatFraccionHtsUsa entidad = new CatFraccionHtsUsa();
        entidad.setCveFraccionHtsUsa(request.getCveFraccionHtsUsa());
        entidad.setDescripcion(request.getDescripcion());
        entidad.setFecCaptura(request.getFecCaptura());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setIdeTipoBienFraccion(request.getIdeTipoBienFraccion());
        entidad.setBlnExenta(request.getBlnExenta());
        entidad.setBlnActivo(request.getBlnActivo());

        if (request.getCveUnidadMedida() != null) {
            final Optional<CatUnidadMedida> unidadMedida =
                    unidadMedidaRepository.findById(request.getCveUnidadMedida());
            if (unidadMedida.isEmpty()) {
                return error(CodigoErrorConst.UNIDAD_MEDIDA_NO_ENCONTRADA,
                        "Unidad de medida no encontrada: " + request.getCveUnidadMedida());
            }
            entidad.setCveUnidadMedida(unidadMedida.get());
        }

        final CatFraccionHtsUsa guardada = fraccionHtsUsaRepository.save(entidad);
        log.info("Fraccion HTS USA creada: {}", guardada.getIdFraccionHtsUsa());
        return exito(fraccionHtsUsaMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatFraccionHtsUsaResponse> actualizar(
            final Long idFraccionHtsUsa, final CatFraccionHtsUsaRequest request) {

        final Optional<CatFraccionHtsUsa> opt = fraccionHtsUsaRepository.findById(idFraccionHtsUsa);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.FRACCION_HTS_USA_NO_ENCONTRADA,
                    "Fraccion HTS USA no encontrada: " + idFraccionHtsUsa);
        }
        final CatFraccionHtsUsa entidad = opt.get();
        if (request.getCveFraccionHtsUsa() != null) {
            entidad.setCveFraccionHtsUsa(request.getCveFraccionHtsUsa());
        }
        if (request.getDescripcion() != null) {
            entidad.setDescripcion(request.getDescripcion());
        }
        if (request.getFecCaptura() != null) {
            entidad.setFecCaptura(request.getFecCaptura());
        }
        if (request.getFecIniVigencia() != null) {
            entidad.setFecIniVigencia(request.getFecIniVigencia());
        }
        if (request.getFecFinVigencia() != null) {
            entidad.setFecFinVigencia(request.getFecFinVigencia());
        }
        if (request.getIdeTipoBienFraccion() != null) {
            entidad.setIdeTipoBienFraccion(request.getIdeTipoBienFraccion());
        }
        if (request.getBlnExenta() != null) {
            entidad.setBlnExenta(request.getBlnExenta());
        }
        if (request.getBlnActivo() != null) {
            entidad.setBlnActivo(request.getBlnActivo());
        }
        if (request.getCveUnidadMedida() != null) {
            final Optional<CatUnidadMedida> unidadMedida =
                    unidadMedidaRepository.findById(request.getCveUnidadMedida());
            if (unidadMedida.isEmpty()) {
                return error(CodigoErrorConst.UNIDAD_MEDIDA_NO_ENCONTRADA,
                        "Unidad de medida no encontrada: " + request.getCveUnidadMedida());
            }
            entidad.setCveUnidadMedida(unidadMedida.get());
        }
        return exito(fraccionHtsUsaMapper.toResponse(fraccionHtsUsaRepository.save(entidad)));
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
