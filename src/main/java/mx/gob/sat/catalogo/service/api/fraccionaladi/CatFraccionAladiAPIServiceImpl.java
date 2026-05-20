package mx.gob.sat.catalogo.service.api.fraccionaladi;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.fraccionaladi.CatFraccionAladiRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.fraccionaladi.CatFraccionAladiResponse;
import mx.gob.sat.catalogo.model.entity.CatFraccionAladi;
import mx.gob.sat.catalogo.repository.CatFraccionAladiRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatFraccionAladiMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatFraccionAladiAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de fracciones ALADI.</p>
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
class CatFraccionAladiAPIServiceImpl implements CatFraccionAladiAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idFraccionAladi", "idFraccionAladi", "cveFraccion", "cveFraccion");
    private static final String DEFAULT_SORT = "idFraccionAladi";

    private final CatFraccionAladiRepository fraccionAladiRepository;
    private final CatFraccionAladiMapper fraccionAladiMapper;

    @Override
    public BaseResponse<PaginaResponse<CatFraccionAladiResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatFraccionAladi> paginaResultado = fraccionAladiRepository.findAll(pageable);
        final List<CatFraccionAladiResponse> contenido = fraccionAladiMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatFraccionAladiResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatFraccionAladiResponse> findById(final Long idFraccionAladi) {
        final Optional<CatFraccionAladi> resultado = fraccionAladiRepository.findById(idFraccionAladi);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.FRACCION_ALADI_NO_ENCONTRADA, "Fraccion ALADI no encontrada: " + idFraccionAladi);
        }
        return exito(fraccionAladiMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatFraccionAladiResponse> crear(final CatFraccionAladiRequest request) {
        final CatFraccionAladi entidad = new CatFraccionAladi();
        entidad.setIdeTipoFraccionAladi(request.getIdeTipoFraccionAladi());
        entidad.setCveFraccion(request.getCveFraccion());
        entidad.setDescripcion(request.getDescripcion());
        entidad.setFecCaptura(request.getFecCaptura());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        final CatFraccionAladi guardada = fraccionAladiRepository.save(entidad);
        log.info("Fraccion ALADI creada: {}", guardada.getIdFraccionAladi());
        return exito(fraccionAladiMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatFraccionAladiResponse> actualizar(final Long idFraccionAladi, final CatFraccionAladiRequest request) {
        final Optional<CatFraccionAladi> opt = fraccionAladiRepository.findById(idFraccionAladi);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.FRACCION_ALADI_NO_ENCONTRADA, "Fraccion ALADI no encontrada: " + idFraccionAladi);
        }
        final CatFraccionAladi entidad = opt.get();
        if (request.getIdeTipoFraccionAladi() != null) { entidad.setIdeTipoFraccionAladi(request.getIdeTipoFraccionAladi()); }
        if (request.getCveFraccion() != null) { entidad.setCveFraccion(request.getCveFraccion()); }
        if (request.getDescripcion() != null) { entidad.setDescripcion(request.getDescripcion()); }
        if (request.getFecCaptura() != null) { entidad.setFecCaptura(request.getFecCaptura()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(fraccionAladiMapper.toResponse(fraccionAladiRepository.save(entidad)));
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
