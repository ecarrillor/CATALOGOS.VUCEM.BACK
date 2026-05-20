package mx.gob.sat.catalogo.service.api.unidadadministrativa;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.unidadadministrativa.CatUnidadAdministrativaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.unidadadministrativa.CatUnidadAdministrativaResponse;
import mx.gob.sat.catalogo.model.entity.CatDependencia;
import mx.gob.sat.catalogo.model.entity.CatEntidad;
import mx.gob.sat.catalogo.model.entity.CatUnidadAdministrativa;
import mx.gob.sat.catalogo.repository.CatDependenciaRepository;
import mx.gob.sat.catalogo.repository.CatEntidadRepository;
import mx.gob.sat.catalogo.repository.CatUnidadAdministrativaRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatUnidadAdministrativaMapper;
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
class CatUnidadAdministrativaAPIServiceImpl implements CatUnidadAdministrativaAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveUnidadAdministrativa", "cveUnidadAdministrativa",
            "nombre", "nombre"
    );
    private static final String DEFAULT_SORT = "cveUnidadAdministrativa";

    private final CatUnidadAdministrativaRepository unidadAdministrativaRepository;
    private final CatEntidadRepository entidadRepository;
    private final CatDependenciaRepository dependenciaRepository;
    private final CatUnidadAdministrativaMapper unidadAdministrativaMapper;

    @Override
    public BaseResponse<PaginaResponse<CatUnidadAdministrativaResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatUnidadAdministrativa> paginaResultado = unidadAdministrativaRepository.findAll(pageable);
        final List<CatUnidadAdministrativaResponse> contenido = unidadAdministrativaMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatUnidadAdministrativaResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatUnidadAdministrativaResponse> findById(final String cveUnidadAdministrativa) {
        final Optional<CatUnidadAdministrativa> resultado = unidadAdministrativaRepository.findById(cveUnidadAdministrativa);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.UNIDAD_ADMINISTRATIVA_NO_ENCONTRADA, "Unidad Administrativa no encontrada: " + cveUnidadAdministrativa);
        }
        return exito(unidadAdministrativaMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatUnidadAdministrativaResponse> crear(final CatUnidadAdministrativaRequest request) {
        final CatUnidadAdministrativa entidad = new CatUnidadAdministrativa();
        if (request.getIdeTipoUnidadAdministrativa() != null) { entidad.setIdeTipoUnidadAdministrativa(request.getIdeTipoUnidadAdministrativa()); }
        if (request.getAcronimo() != null) { entidad.setAcronimo(request.getAcronimo()); }
        if (request.getNombre() != null) { entidad.setNombre(request.getNombre()); }
        if (request.getDescripcion() != null) { entidad.setDescripcion(request.getDescripcion()); }
        if (request.getNivel() != null) { entidad.setNivel(request.getNivel()); }
        if (request.getBlnFronteriza() != null) { entidad.setBlnFronteriza(request.getBlnFronteriza()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getCveUnidadAdministrativaR() != null) {
            final Optional<CatUnidadAdministrativa> uaRef = unidadAdministrativaRepository.findById(request.getCveUnidadAdministrativaR());
            uaRef.ifPresent(entidad::setCveUnidadAdministrativaR);
        }
        if (request.getCveEntidad() != null) {
            final Optional<CatEntidad> entidad2 = entidadRepository.findById(request.getCveEntidad());
            entidad2.ifPresent(entidad::setCveEntidad);
        }
        if (request.getIdDependencia() != null) {
            final Optional<CatDependencia> dependencia = dependenciaRepository.findById(request.getIdDependencia().longValue());
            dependencia.ifPresent(entidad::setIdDependencia);
        }
        final CatUnidadAdministrativa guardado = unidadAdministrativaRepository.save(entidad);
        log.info("Unidad Administrativa creada: {}", guardado.getCveUnidadAdministrativa());
        return exito(unidadAdministrativaMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatUnidadAdministrativaResponse> actualizar(final String cveUnidadAdministrativa, final CatUnidadAdministrativaRequest request) {
        final Optional<CatUnidadAdministrativa> opt = unidadAdministrativaRepository.findById(cveUnidadAdministrativa);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.UNIDAD_ADMINISTRATIVA_NO_ENCONTRADA, "Unidad Administrativa no encontrada: " + cveUnidadAdministrativa);
        }
        final CatUnidadAdministrativa entidad = opt.get();
        if (request.getIdeTipoUnidadAdministrativa() != null) { entidad.setIdeTipoUnidadAdministrativa(request.getIdeTipoUnidadAdministrativa()); }
        if (request.getAcronimo() != null) { entidad.setAcronimo(request.getAcronimo()); }
        if (request.getNombre() != null) { entidad.setNombre(request.getNombre()); }
        if (request.getDescripcion() != null) { entidad.setDescripcion(request.getDescripcion()); }
        if (request.getNivel() != null) { entidad.setNivel(request.getNivel()); }
        if (request.getBlnFronteriza() != null) { entidad.setBlnFronteriza(request.getBlnFronteriza()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getCveUnidadAdministrativaR() != null) {
            final Optional<CatUnidadAdministrativa> uaRef = unidadAdministrativaRepository.findById(request.getCveUnidadAdministrativaR());
            uaRef.ifPresent(entidad::setCveUnidadAdministrativaR);
        }
        if (request.getCveEntidad() != null) {
            final Optional<CatEntidad> entidadRef = entidadRepository.findById(request.getCveEntidad());
            entidadRef.ifPresent(entidad::setCveEntidad);
        }
        if (request.getIdDependencia() != null) {
            final Optional<CatDependencia> dependencia = dependenciaRepository.findById(request.getIdDependencia().longValue());
            dependencia.ifPresent(entidad::setIdDependencia);
        }
        return exito(unidadAdministrativaMapper.toResponse(unidadAdministrativaRepository.save(entidad)));
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
