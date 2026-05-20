package mx.gob.sat.catalogo.service.api.subdivisionfraccion;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.subdivisionfraccion.CatSubdivisionFraccionRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.subdivisionfraccion.CatSubdivisionFraccionResponse;
import mx.gob.sat.catalogo.model.entity.CatSubdivisionFraccion;
import mx.gob.sat.catalogo.repository.CatFraccionArancelariaRepository;
import mx.gob.sat.catalogo.repository.CatSubdivisionFraccionRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatSubdivisionFraccionMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatSubdivisionFraccionAPIServiceImpl implements CatSubdivisionFraccionAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of("cveSubdivision", "cveSubdivision");
    private static final String DEFAULT_SORT = "cveSubdivision";

    private final CatSubdivisionFraccionRepository subdivisionFraccionRepository;
    private final CatFraccionArancelariaRepository fraccionArancelariaRepository;
    private final CatSubdivisionFraccionMapper subdivisionFraccionMapper;

    @Override
    public BaseResponse<PaginaResponse<CatSubdivisionFraccionResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatSubdivisionFraccion> paginaResultado = subdivisionFraccionRepository.findAll(pageable);
        final List<CatSubdivisionFraccionResponse> contenido = subdivisionFraccionMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatSubdivisionFraccionResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatSubdivisionFraccionResponse> findById(final String cveSubdivision) {
        final Optional<CatSubdivisionFraccion> resultado = subdivisionFraccionRepository.findById(cveSubdivision);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.SUBDIVISION_FRACCION_NO_ENCONTRADA, "Subdivision Fraccion no encontrada: " + cveSubdivision);
        }
        return exito(subdivisionFraccionMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatSubdivisionFraccionResponse> crear(final CatSubdivisionFraccionRequest request) {
        final CatSubdivisionFraccion entidad = new CatSubdivisionFraccion();
        entidad.setCveSubdivision(request.getCveSubdivision());
        entidad.setCodSubdivision(request.getCodSubdivision());
        entidad.setDescripcion(request.getDescripcion());
        entidad.setPrecioEstimado(request.getPrecioEstimado());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        if (request.getCveFraccion() != null) {
            fraccionArancelariaRepository.findById(request.getCveFraccion()).ifPresent(entidad::setCveFraccion);
        }
        final CatSubdivisionFraccion guardada = subdivisionFraccionRepository.save(entidad);
        log.info("Subdivision Fraccion creada: {}", guardada.getCveSubdivision());
        return exito(subdivisionFraccionMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatSubdivisionFraccionResponse> actualizar(final String cveSubdivision, final CatSubdivisionFraccionRequest request) {
        final Optional<CatSubdivisionFraccion> opt = subdivisionFraccionRepository.findById(cveSubdivision);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.SUBDIVISION_FRACCION_NO_ENCONTRADA, "Subdivision Fraccion no encontrada: " + cveSubdivision);
        }
        final CatSubdivisionFraccion entidad = opt.get();
        if (request.getCodSubdivision() != null) { entidad.setCodSubdivision(request.getCodSubdivision()); }
        if (request.getDescripcion() != null) { entidad.setDescripcion(request.getDescripcion()); }
        if (request.getPrecioEstimado() != null) { entidad.setPrecioEstimado(request.getPrecioEstimado()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getCveFraccion() != null) {
            fraccionArancelariaRepository.findById(request.getCveFraccion()).ifPresent(entidad::setCveFraccion);
        }
        return exito(subdivisionFraccionMapper.toResponse(subdivisionFraccionRepository.save(entidad)));
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
