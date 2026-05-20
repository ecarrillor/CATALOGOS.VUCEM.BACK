package mx.gob.sat.catalogo.service.api.isotopofraccion;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.isotopofraccion.CatIsotopoFraccionRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.isotopofraccion.CatIsotopoFraccionResponse;
import mx.gob.sat.catalogo.model.entity.CatIsotopoFraccion;
import mx.gob.sat.catalogo.repository.CatFraccionArancelariaRepository;
import mx.gob.sat.catalogo.repository.CatIsotopoFraccionRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatIsotopoFraccionMapper;
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
class CatIsotopoFraccionAPIServiceImpl implements CatIsotopoFraccionAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of("idIsotopo", "idIsotopo");
    private static final String DEFAULT_SORT = "idIsotopo";

    private final CatIsotopoFraccionRepository isotopoFraccionRepository;
    private final CatFraccionArancelariaRepository fraccionArancelariaRepository;
    private final CatIsotopoFraccionMapper isotopoFraccionMapper;

    @Override
    public BaseResponse<PaginaResponse<CatIsotopoFraccionResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatIsotopoFraccion> paginaResultado = isotopoFraccionRepository.findAll(pageable);
        final List<CatIsotopoFraccionResponse> contenido = isotopoFraccionMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatIsotopoFraccionResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatIsotopoFraccionResponse> findById(final Short idIsotopo) {
        final Optional<CatIsotopoFraccion> resultado = isotopoFraccionRepository.findById(idIsotopo);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.ISOTOPO_FRACCION_NO_ENCONTRADO, "Isotopo Fraccion no encontrado: " + idIsotopo);
        }
        return exito(isotopoFraccionMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatIsotopoFraccionResponse> crear(final CatIsotopoFraccionRequest request) {
        final CatIsotopoFraccion entidad = new CatIsotopoFraccion();
        entidad.setIdIsotopo(request.getIdIsotopo());
        entidad.setNombre(request.getNombre());
        if (request.getCveFraccion() != null) {
            fraccionArancelariaRepository.findById(request.getCveFraccion()).ifPresent(entidad::setCveFraccion);
        }
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        final CatIsotopoFraccion guardada = isotopoFraccionRepository.save(entidad);
        log.info("Isotopo Fraccion creado: {}", guardada.getIdIsotopo());
        return exito(isotopoFraccionMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatIsotopoFraccionResponse> actualizar(final Short idIsotopo, final CatIsotopoFraccionRequest request) {
        final Optional<CatIsotopoFraccion> opt = isotopoFraccionRepository.findById(idIsotopo);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.ISOTOPO_FRACCION_NO_ENCONTRADO, "Isotopo Fraccion no encontrado: " + idIsotopo);
        }
        final CatIsotopoFraccion entidad = opt.get();
        if (request.getNombre() != null) { entidad.setNombre(request.getNombre()); }
        if (request.getCveFraccion() != null) {
            fraccionArancelariaRepository.findById(request.getCveFraccion()).ifPresent(entidad::setCveFraccion);
        }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(isotopoFraccionMapper.toResponse(isotopoFraccionRepository.save(entidad)));
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
