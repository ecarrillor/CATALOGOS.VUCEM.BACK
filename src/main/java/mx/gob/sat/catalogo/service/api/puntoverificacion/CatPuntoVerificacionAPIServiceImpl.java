package mx.gob.sat.catalogo.service.api.puntoverificacion;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.puntoverificacion.CatPuntoVerificacionRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.puntoverificacion.CatPuntoVerificacionResponse;
import mx.gob.sat.catalogo.model.entity.CatPuntoVerificacion;
import mx.gob.sat.catalogo.repository.CatPuntoVerificacionRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatPuntoVerificacionMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatPuntoVerificacionAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de puntos de verificacion.</p>
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
class CatPuntoVerificacionAPIServiceImpl implements CatPuntoVerificacionAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idPuntoVerificacion", "idPuntoVerificacion", "nomPuntoVerif", "nomPuntoVerif");
    private static final String DEFAULT_SORT = "idPuntoVerificacion";
    private final CatPuntoVerificacionRepository puntoVerificacionRepository;
    private final CatPuntoVerificacionMapper puntoVerificacionMapper;

    @Override
    public BaseResponse<PaginaResponse<CatPuntoVerificacionResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatPuntoVerificacion> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? puntoVerificacionRepository.findAll(pageable)
                : puntoVerificacionRepository.findByNomPuntoVerifContainingIgnoreCase(busqueda, pageable);
        final List<CatPuntoVerificacionResponse> contenido = puntoVerificacionMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatPuntoVerificacionResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatPuntoVerificacionResponse> findById(final Integer idPuntoVerificacion) {
        final Optional<CatPuntoVerificacion> resultado = puntoVerificacionRepository.findById(idPuntoVerificacion);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.PUNTO_VERIFICACION_NO_ENCONTRADO, "Punto de verificacion no encontrado: " + idPuntoVerificacion);
        }
        return exito(puntoVerificacionMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatPuntoVerificacionResponse> crear(final CatPuntoVerificacionRequest request) {
        final CatPuntoVerificacion guardado = puntoVerificacionRepository.save(puntoVerificacionMapper.toEntity(request));
        log.info("Punto de verificacion creado: {}", guardado.getIdPuntoVerificacion());
        return exito(puntoVerificacionMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatPuntoVerificacionResponse> actualizar(final Integer idPuntoVerificacion, final CatPuntoVerificacionRequest request) {
        final Optional<CatPuntoVerificacion> opt = puntoVerificacionRepository.findById(idPuntoVerificacion);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.PUNTO_VERIFICACION_NO_ENCONTRADO, "Punto de verificacion no encontrado: " + idPuntoVerificacion);
        }
        final CatPuntoVerificacion entidad = opt.get();
        if (request.getNomPuntoVerif() != null) { entidad.setNomPuntoVerif(request.getNomPuntoVerif()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(puntoVerificacionMapper.toResponse(puntoVerificacionRepository.save(entidad)));
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
