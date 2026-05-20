package mx.gob.sat.catalogo.service.api.capitulofraccion;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.capitulofraccion.CatCapituloFraccionRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.capitulofraccion.CatCapituloFraccionResponse;
import mx.gob.sat.catalogo.model.entity.CatCapituloFraccion;
import mx.gob.sat.catalogo.repository.CatCapituloFraccionRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatCapituloFraccionMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatCapituloFraccionAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de capitulos de fraccion.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Implementacion del servicio API
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatCapituloFraccionAPIServiceImpl implements CatCapituloFraccionAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveCapituloFraccion", "cveCapituloFraccion",
            "nombre", "nombre"
    );
    private static final String DEFAULT_SORT = "cveCapituloFraccion";
    private final CatCapituloFraccionRepository capituloFraccionRepository;
    private final CatCapituloFraccionMapper capituloFraccionMapper;

    @Override
    public BaseResponse<PaginaResponse<CatCapituloFraccionResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatCapituloFraccion> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? capituloFraccionRepository.findAll(pageable)
                : capituloFraccionRepository.findByNombreContainingIgnoreCaseOrCveCapituloFraccionContainingIgnoreCase(
                        busqueda, busqueda, pageable);
        final List<CatCapituloFraccionResponse> contenido =
                capituloFraccionMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatCapituloFraccionResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatCapituloFraccionResponse> findById(final String cveCapituloFraccion) {
        final Optional<CatCapituloFraccion> resultado = capituloFraccionRepository.findById(cveCapituloFraccion);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.CAPITULO_FRACCION_NO_ENCONTRADO,
                    "Capitulo fraccion no encontrado: " + cveCapituloFraccion);
        }
        return exito(capituloFraccionMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatCapituloFraccionResponse> crear(final CatCapituloFraccionRequest request) {
        final CatCapituloFraccion guardado = capituloFraccionRepository.save(capituloFraccionMapper.toEntity(request));
        log.info("Capitulo fraccion creado: {}", guardado.getCveCapituloFraccion());
        return exito(capituloFraccionMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatCapituloFraccionResponse> actualizar(
            final String cveCapituloFraccion, final CatCapituloFraccionRequest request) {
        final Optional<CatCapituloFraccion> opt = capituloFraccionRepository.findById(cveCapituloFraccion);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.CAPITULO_FRACCION_NO_ENCONTRADO,
                    "Capitulo fraccion no encontrado: " + cveCapituloFraccion);
        }
        final CatCapituloFraccion entidad = opt.get();
        if (request.getNombre() != null) { entidad.setNombre(request.getNombre()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(capituloFraccionMapper.toResponse(capituloFraccionRepository.save(entidad)));
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
