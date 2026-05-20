package mx.gob.sat.catalogo.service.api.partidafraccion;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.partidafraccion.CatPartidaFraccionRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.partidafraccion.CatPartidaFraccionResponse;
import mx.gob.sat.catalogo.model.entity.CatPartidaFraccion;
import mx.gob.sat.catalogo.model.entity.CatPartidaFraccionId;
import mx.gob.sat.catalogo.repository.CatPartidaFraccionRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatPartidaFraccionMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementacion del servicio para el catalogo de partida fraccion.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatPartidaFraccionAPIServiceImpl implements CatPartidaFraccionAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveCapituloFraccion", "id.cveCapituloFraccion",
            "cvePartidaFraccion", "id.cvePartidaFraccion");
    private static final String DEFAULT_SORT = "id.cveCapituloFraccion";

    private final CatPartidaFraccionRepository partidaFraccionRepository;
    private final CatPartidaFraccionMapper partidaFraccionMapper;

    @Override
    public BaseResponse<PaginaResponse<CatPartidaFraccionResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted() ? PageRequest.of(pagina, tamano, sort) : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatPartidaFraccion> paginaResultado = partidaFraccionRepository.findAll(pageable);
        final List<CatPartidaFraccionResponse> contenido = partidaFraccionMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatPartidaFraccionResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatPartidaFraccionResponse> findById(final String cveCapituloFraccion, final String cvePartidaFraccion) {
        final CatPartidaFraccionId id = new CatPartidaFraccionId();
        id.setCveCapituloFraccion(cveCapituloFraccion);
        id.setCvePartidaFraccion(cvePartidaFraccion);
        final Optional<CatPartidaFraccion> resultado = partidaFraccionRepository.findById(id);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.PARTIDA_FRACCION_NO_ENCONTRADA, "Partida Fraccion no encontrada: " + cveCapituloFraccion + "/" + cvePartidaFraccion);
        }
        return exito(partidaFraccionMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatPartidaFraccionResponse> crear(final CatPartidaFraccionRequest request) {
        final CatPartidaFraccion entidad = new CatPartidaFraccion();
        final CatPartidaFraccionId id = new CatPartidaFraccionId();
        id.setCveCapituloFraccion(request.getCveCapituloFraccion());
        id.setCvePartidaFraccion(request.getCvePartidaFraccion());
        entidad.setId(id);
        entidad.setNombre(request.getNombre());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        final CatPartidaFraccion guardado = partidaFraccionRepository.save(entidad);
        log.info("Partida Fraccion creada: {}/{}", guardado.getId().getCveCapituloFraccion(), guardado.getId().getCvePartidaFraccion());
        return exito(partidaFraccionMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatPartidaFraccionResponse> actualizar(final String cveCapituloFraccion, final String cvePartidaFraccion, final CatPartidaFraccionRequest request) {
        final CatPartidaFraccionId id = new CatPartidaFraccionId();
        id.setCveCapituloFraccion(cveCapituloFraccion);
        id.setCvePartidaFraccion(cvePartidaFraccion);
        final Optional<CatPartidaFraccion> opt = partidaFraccionRepository.findById(id);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.PARTIDA_FRACCION_NO_ENCONTRADA, "Partida Fraccion no encontrada: " + cveCapituloFraccion + "/" + cvePartidaFraccion);
        }
        final CatPartidaFraccion entidad = opt.get();
        if (request.getNombre() != null) { entidad.setNombre(request.getNombre()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(partidaFraccionMapper.toResponse(partidaFraccionRepository.save(entidad)));
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
