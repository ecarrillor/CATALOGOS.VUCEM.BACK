package mx.gob.sat.catalogo.service.api.suplencia;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.suplencia.CatSuplenciaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.suplencia.CatSuplenciaResponse;
import mx.gob.sat.catalogo.model.entity.CatDependencia;
import mx.gob.sat.catalogo.model.entity.CatSuplencia;
import mx.gob.sat.catalogo.repository.CatDependenciaRepository;
import mx.gob.sat.catalogo.repository.CatSuplenciaRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatSuplenciaMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatSuplenciaAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de suplencias.</p>
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
class CatSuplenciaAPIServiceImpl implements CatSuplenciaAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idSuplencia", "idSuplencia");

    private static final String DEFAULT_SORT = "idSuplencia";

    private final CatSuplenciaRepository suplenciaRepository;
    private final CatDependenciaRepository dependenciaRepository;
    private final CatSuplenciaMapper suplenciaMapper;

    @Override
    public BaseResponse<PaginaResponse<CatSuplenciaResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatSuplencia> paginaResultado = suplenciaRepository.findAll(pageable);

        final List<CatSuplenciaResponse> contenido =
                suplenciaMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatSuplenciaResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatSuplenciaResponse> findById(final Short idSuplencia) {
        final Optional<CatSuplencia> resultado = suplenciaRepository.findById(idSuplencia);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.SUPLENCIA_NO_ENCONTRADA,
                    "Suplencia no encontrada: " + idSuplencia);
        }
        return exito(suplenciaMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatSuplenciaResponse> crear(final CatSuplenciaRequest request) {
        final CatSuplencia entidad = new CatSuplencia();
        entidad.setTexto(request.getTexto());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());

        if (request.getIdDependencia() != null) {
            final Optional<CatDependencia> dependencia =
                    dependenciaRepository.findById(request.getIdDependencia().longValue());
            if (dependencia.isEmpty()) {
                return error(CodigoErrorConst.DEPENDENCIA_NO_ENCONTRADA,
                        "Dependencia no encontrada: " + request.getIdDependencia());
            }
            entidad.setIdDependencia(dependencia.get());
        }

        final CatSuplencia guardada = suplenciaRepository.save(entidad);
        log.info("Suplencia creada: {}", guardada.getIdSuplencia());
        return exito(suplenciaMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatSuplenciaResponse> actualizar(
            final Short idSuplencia, final CatSuplenciaRequest request) {

        final Optional<CatSuplencia> opt = suplenciaRepository.findById(idSuplencia);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.SUPLENCIA_NO_ENCONTRADA,
                    "Suplencia no encontrada: " + idSuplencia);
        }
        final CatSuplencia entidad = opt.get();
        if (request.getTexto() != null) {
            entidad.setTexto(request.getTexto());
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
        if (request.getIdDependencia() != null) {
            final Optional<CatDependencia> dependencia =
                    dependenciaRepository.findById(request.getIdDependencia().longValue());
            if (dependencia.isEmpty()) {
                return error(CodigoErrorConst.DEPENDENCIA_NO_ENCONTRADA,
                        "Dependencia no encontrada: " + request.getIdDependencia());
            }
            entidad.setIdDependencia(dependencia.get());
        }
        return exito(suplenciaMapper.toResponse(suplenciaRepository.save(entidad)));
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
