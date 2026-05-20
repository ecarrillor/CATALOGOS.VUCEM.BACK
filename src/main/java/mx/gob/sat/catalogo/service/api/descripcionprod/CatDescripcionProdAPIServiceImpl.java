package mx.gob.sat.catalogo.service.api.descripcionprod;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.descripcionprod.CatDescripcionProdRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.descripcionprod.CatDescripcionProdResponse;
import mx.gob.sat.catalogo.model.entity.CatDescripcionProd;
import mx.gob.sat.catalogo.repository.CatDescripcionProdRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatDescripcionProdMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatDescripcionProdAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de descripciones de producto.</p>
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
class CatDescripcionProdAPIServiceImpl implements CatDescripcionProdAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idDescripcionProd", "idDescripcionProd", "descripcionProducto", "descripcionProducto");
    private static final String DEFAULT_SORT = "idDescripcionProd";
    private final CatDescripcionProdRepository descripcionProdRepository;
    private final CatDescripcionProdMapper descripcionProdMapper;

    @Override
    public BaseResponse<PaginaResponse<CatDescripcionProdResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatDescripcionProd> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? descripcionProdRepository.findAll(pageable)
                : descripcionProdRepository.findByDescripcionProductoContainingIgnoreCase(busqueda, pageable);
        final List<CatDescripcionProdResponse> contenido = descripcionProdMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatDescripcionProdResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatDescripcionProdResponse> findById(final Integer idDescripcionProd) {
        final Optional<CatDescripcionProd> resultado = descripcionProdRepository.findById(idDescripcionProd);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.DESCRIPCION_PROD_NO_ENCONTRADA, "Descripcion producto no encontrada: " + idDescripcionProd);
        }
        return exito(descripcionProdMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatDescripcionProdResponse> crear(final CatDescripcionProdRequest request) {
        final CatDescripcionProd guardada = descripcionProdRepository.save(descripcionProdMapper.toEntity(request));
        log.info("Descripcion producto creada: {}", guardada.getIdDescripcionProd());
        return exito(descripcionProdMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatDescripcionProdResponse> actualizar(final Integer idDescripcionProd, final CatDescripcionProdRequest request) {
        final Optional<CatDescripcionProd> opt = descripcionProdRepository.findById(idDescripcionProd);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.DESCRIPCION_PROD_NO_ENCONTRADA, "Descripcion producto no encontrada: " + idDescripcionProd);
        }
        final CatDescripcionProd entidad = opt.get();
        if (request.getDescripcionProducto() != null) { entidad.setDescripcionProducto(request.getDescripcionProducto()); }
        if (request.getFecCaptura() != null) { entidad.setFecCaptura(request.getFecCaptura()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(descripcionProdMapper.toResponse(descripcionProdRepository.save(entidad)));
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
