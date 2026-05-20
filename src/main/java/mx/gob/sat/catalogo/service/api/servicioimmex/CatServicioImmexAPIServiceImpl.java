package mx.gob.sat.catalogo.service.api.servicioimmex;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.servicioimmex.CatServicioImmexRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.servicioimmex.CatServicioImmexResponse;
import mx.gob.sat.catalogo.model.entity.CatServicioImmex;
import mx.gob.sat.catalogo.repository.CatServicioImmexRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatServicioImmexMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatServicioImmexAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de servicios IMMEX.</p>
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
class CatServicioImmexAPIServiceImpl implements CatServicioImmexAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idServicioImmex", "idServicioImmex",
            "nombre", "nombre");

    private static final String DEFAULT_SORT = "idServicioImmex";

    private final CatServicioImmexRepository servicioImmexRepository;
    private final CatServicioImmexMapper servicioImmexMapper;

    @Override
    public BaseResponse<PaginaResponse<CatServicioImmexResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatServicioImmex> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? servicioImmexRepository.findAll(pageable)
                : servicioImmexRepository.findByNombreContainingIgnoreCase(busqueda, pageable);

        final List<CatServicioImmexResponse> contenido =
                servicioImmexMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatServicioImmexResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatServicioImmexResponse> findById(final Short idServicioImmex) {
        final Optional<CatServicioImmex> resultado = servicioImmexRepository.findById(idServicioImmex);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.SERVICIO_IMMEX_NO_ENCONTRADO,
                    "Servicio IMMEX no encontrado: " + idServicioImmex);
        }
        return exito(servicioImmexMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatServicioImmexResponse> crear(final CatServicioImmexRequest request) {
        final CatServicioImmex guardado = servicioImmexRepository.save(servicioImmexMapper.toEntity(request));
        log.info("Servicio IMMEX creado: {}", guardado.getIdServicioImmex());
        return exito(servicioImmexMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatServicioImmexResponse> actualizar(
            final Short idServicioImmex, final CatServicioImmexRequest request) {

        final Optional<CatServicioImmex> opt = servicioImmexRepository.findById(idServicioImmex);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.SERVICIO_IMMEX_NO_ENCONTRADO,
                    "Servicio IMMEX no encontrado: " + idServicioImmex);
        }
        final CatServicioImmex entidad = opt.get();
        if (request.getNombre() != null) {
            entidad.setNombre(request.getNombre());
        }
        if (request.getIdeTipoServicioImmex() != null) {
            entidad.setIdeTipoServicioImmex(request.getIdeTipoServicioImmex());
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
        return exito(servicioImmexMapper.toResponse(servicioImmexRepository.save(entidad)));
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
