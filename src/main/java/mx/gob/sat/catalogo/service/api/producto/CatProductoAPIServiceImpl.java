package mx.gob.sat.catalogo.service.api.producto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.producto.CatProductoRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.producto.CatProductoResponse;
import mx.gob.sat.catalogo.model.entity.CatProducto;
import mx.gob.sat.catalogo.repository.CatProductoRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatProductoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatProductoAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de productos.</p>
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
class CatProductoAPIServiceImpl implements CatProductoAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveProducto", "cveProducto", "nombre", "nombre", "descripcion", "descripcion");
    private static final String DEFAULT_SORT = "cveProducto";
    private final CatProductoRepository productoRepository;
    private final CatProductoMapper productoMapper;

    @Override
    public BaseResponse<PaginaResponse<CatProductoResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatProducto> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? productoRepository.findAll(pageable)
                : productoRepository.findByNombreContainingIgnoreCaseOrCveProductoContainingIgnoreCaseOrDescripcionContainingIgnoreCase(
                        busqueda, busqueda, busqueda, pageable);
        final List<CatProductoResponse> contenido = productoMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatProductoResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatProductoResponse> findById(final String cveProducto) {
        final Optional<CatProducto> resultado = productoRepository.findById(cveProducto);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.PRODUCTO_NO_ENCONTRADO, "Producto no encontrado: " + cveProducto);
        }
        return exito(productoMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatProductoResponse> crear(final CatProductoRequest request) {
        final CatProducto guardado = productoRepository.save(productoMapper.toEntity(request));
        log.info("Producto creado: {}", guardado.getCveProducto());
        return exito(productoMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatProductoResponse> actualizar(final String cveProducto, final CatProductoRequest request) {
        final Optional<CatProducto> opt = productoRepository.findById(cveProducto);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.PRODUCTO_NO_ENCONTRADO, "Producto no encontrado: " + cveProducto);
        }
        final CatProducto entidad = opt.get();
        if (request.getSigla() != null) { entidad.setSigla(request.getSigla()); }
        if (request.getNombre() != null) { entidad.setNombre(request.getNombre()); }
        if (request.getDescripcion() != null) { entidad.setDescripcion(request.getDescripcion()); }
        if (request.getFecCaptura() != null) { entidad.setFecCaptura(request.getFecCaptura()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(productoMapper.toResponse(productoRepository.save(entidad)));
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
