package mx.gob.sat.catalogo.service.api.subpartidafraccion;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.subpartidafraccion.CatSubpartidaFraccionRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.subpartidafraccion.CatSubpartidaFraccionResponse;
import mx.gob.sat.catalogo.model.entity.CatSubpartidaFraccion;
import mx.gob.sat.catalogo.model.entity.CatSubpartidaFraccionId;
import mx.gob.sat.catalogo.repository.CatSubpartidaFraccionRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatSubpartidaFraccionMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementacion del servicio para el catalogo de subpartida fraccion.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatSubpartidaFraccionAPIServiceImpl implements CatSubpartidaFraccionAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveSubpartidaFraccion", "id.cveSubpartidaFraccion",
            "cveCapituloFraccion", "id.cveCapituloFraccion");
    private static final String DEFAULT_SORT = "id.cveSubpartidaFraccion";

    private final CatSubpartidaFraccionRepository subpartidaFraccionRepository;
    private final CatSubpartidaFraccionMapper subpartidaFraccionMapper;

    @Override
    public BaseResponse<PaginaResponse<CatSubpartidaFraccionResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted() ? PageRequest.of(pagina, tamano, sort) : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatSubpartidaFraccion> paginaResultado = subpartidaFraccionRepository.findAll(pageable);
        final List<CatSubpartidaFraccionResponse> contenido = subpartidaFraccionMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatSubpartidaFraccionResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatSubpartidaFraccionResponse> findById(final String cveSubpartidaFraccion, final String cveCapituloFraccion, final String cvePartidaFraccion) {
        final CatSubpartidaFraccionId id = new CatSubpartidaFraccionId();
        id.setCveSubpartidaFraccion(cveSubpartidaFraccion);
        id.setCveCapituloFraccion(cveCapituloFraccion);
        id.setCvePartidaFraccion(cvePartidaFraccion);
        final Optional<CatSubpartidaFraccion> resultado = subpartidaFraccionRepository.findById(id);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.SUBPARTIDA_FRACCION_NO_ENCONTRADA, "Subpartida Fraccion no encontrada: " + cveSubpartidaFraccion + "/" + cveCapituloFraccion + "/" + cvePartidaFraccion);
        }
        return exito(subpartidaFraccionMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatSubpartidaFraccionResponse> crear(final CatSubpartidaFraccionRequest request) {
        final CatSubpartidaFraccion entidad = new CatSubpartidaFraccion();
        final CatSubpartidaFraccionId id = new CatSubpartidaFraccionId();
        id.setCveSubpartidaFraccion(request.getCveSubpartidaFraccion());
        id.setCveCapituloFraccion(request.getCveCapituloFraccion());
        id.setCvePartidaFraccion(request.getCvePartidaFraccion());
        entidad.setId(id);
        entidad.setNombre(request.getNombre());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        final CatSubpartidaFraccion guardado = subpartidaFraccionRepository.save(entidad);
        log.info("Subpartida Fraccion creada: {}/{}/{}", guardado.getId().getCveSubpartidaFraccion(), guardado.getId().getCveCapituloFraccion(), guardado.getId().getCvePartidaFraccion());
        return exito(subpartidaFraccionMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatSubpartidaFraccionResponse> actualizar(final String cveSubpartidaFraccion, final String cveCapituloFraccion, final String cvePartidaFraccion, final CatSubpartidaFraccionRequest request) {
        final CatSubpartidaFraccionId id = new CatSubpartidaFraccionId();
        id.setCveSubpartidaFraccion(cveSubpartidaFraccion);
        id.setCveCapituloFraccion(cveCapituloFraccion);
        id.setCvePartidaFraccion(cvePartidaFraccion);
        final Optional<CatSubpartidaFraccion> opt = subpartidaFraccionRepository.findById(id);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.SUBPARTIDA_FRACCION_NO_ENCONTRADA, "Subpartida Fraccion no encontrada: " + cveSubpartidaFraccion + "/" + cveCapituloFraccion + "/" + cvePartidaFraccion);
        }
        final CatSubpartidaFraccion entidad = opt.get();
        if (request.getNombre() != null) { entidad.setNombre(request.getNombre()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(subpartidaFraccionMapper.toResponse(subpartidaFraccionRepository.save(entidad)));
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
