package mx.gob.sat.catalogo.service.api.tipotramite;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.tipotramite.CatTipoTramiteRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tipotramite.CatTipoTramiteResponse;
import mx.gob.sat.catalogo.model.entity.CatDependencia;
import mx.gob.sat.catalogo.model.entity.CatTipoTramite;
import mx.gob.sat.catalogo.repository.CatDependenciaRepository;
import mx.gob.sat.catalogo.repository.CatTipoTramiteRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatTipoTramiteMapper;
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
class CatTipoTramiteAPIServiceImpl implements CatTipoTramiteAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idTipoTramite", "idTipoTramite",
            "nombre", "nombre"
    );
    private static final String DEFAULT_SORT = "idTipoTramite";

    private final CatTipoTramiteRepository tipoTramiteRepository;
    private final CatDependenciaRepository dependenciaRepository;
    private final CatTipoTramiteMapper tipoTramiteMapper;

    @Override
    public BaseResponse<PaginaResponse<CatTipoTramiteResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatTipoTramite> paginaResultado = tipoTramiteRepository.findAll(pageable);
        final List<CatTipoTramiteResponse> contenido = tipoTramiteMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatTipoTramiteResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatTipoTramiteResponse> findById(final Long idTipoTramite) {
        final Optional<CatTipoTramite> resultado = tipoTramiteRepository.findById(idTipoTramite);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.TIPO_TRAMITE_NO_ENCONTRADO, "Tipo Tramite no encontrado: " + idTipoTramite);
        }
        return exito(tipoTramiteMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatTipoTramiteResponse> crear(final CatTipoTramiteRequest request) {
        final CatTipoTramite entidad = new CatTipoTramite();
        if (request.getCveServicio() != null) { entidad.setCveServicio(request.getCveServicio()); }
        if (request.getDescServicio() != null) { entidad.setDescServicio(request.getDescServicio()); }
        if (request.getCveSubservicio() != null) { entidad.setCveSubservicio(request.getCveSubservicio()); }
        if (request.getDescSubservicio() != null) { entidad.setDescSubservicio(request.getDescSubservicio()); }
        if (request.getCveModalidad() != null) { entidad.setCveModalidad(request.getCveModalidad()); }
        if (request.getDescModalidad() != null) { entidad.setDescModalidad(request.getDescModalidad()); }
        if (request.getCveFlujo() != null) { entidad.setCveFlujo(request.getCveFlujo()); }
        if (request.getDescFlujo() != null) { entidad.setDescFlujo(request.getDescFlujo()); }
        if (request.getNivelServicio() != null) { entidad.setNivelServicio(request.getNivelServicio()); }
        if (request.getNomServAxway() != null) { entidad.setNomServAxway(request.getNomServAxway()); }
        if (request.getNomMensajeAxway() != null) { entidad.setNomMensajeAxway(request.getNomMensajeAxway()); }
        if (request.getUrlAxway() != null) { entidad.setUrlAxway(request.getUrlAxway()); }
        if (request.getFecCaptura() != null) { entidad.setFecCaptura(request.getFecCaptura()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getNombre() != null) { entidad.setNombre(request.getNombre()); }
        if (request.getBlnReplicaInfo() != null) { entidad.setBlnReplicaInfo(request.getBlnReplicaInfo()); }
        if (request.getBlnAutomatico() != null) { entidad.setBlnAutomatico(request.getBlnAutomatico()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getBlnAsignacion() != null) { entidad.setBlnAsignacion(request.getBlnAsignacion()); }
        if (request.getCveModulo() != null) { entidad.setCveModulo(request.getCveModulo()); }
        if (request.getIdDependencia() != null) {
            final Optional<CatDependencia> dependencia = dependenciaRepository.findById(request.getIdDependencia().longValue());
            dependencia.ifPresent(entidad::setIdDependencia);
        }
        final CatTipoTramite guardado = tipoTramiteRepository.save(entidad);
        log.info("Tipo Tramite creado: {}", guardado.getIdTipoTramite());
        return exito(tipoTramiteMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatTipoTramiteResponse> actualizar(final Long idTipoTramite, final CatTipoTramiteRequest request) {
        final Optional<CatTipoTramite> opt = tipoTramiteRepository.findById(idTipoTramite);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.TIPO_TRAMITE_NO_ENCONTRADO, "Tipo Tramite no encontrado: " + idTipoTramite);
        }
        final CatTipoTramite entidad = opt.get();
        if (request.getCveServicio() != null) { entidad.setCveServicio(request.getCveServicio()); }
        if (request.getDescServicio() != null) { entidad.setDescServicio(request.getDescServicio()); }
        if (request.getCveSubservicio() != null) { entidad.setCveSubservicio(request.getCveSubservicio()); }
        if (request.getDescSubservicio() != null) { entidad.setDescSubservicio(request.getDescSubservicio()); }
        if (request.getCveModalidad() != null) { entidad.setCveModalidad(request.getCveModalidad()); }
        if (request.getDescModalidad() != null) { entidad.setDescModalidad(request.getDescModalidad()); }
        if (request.getCveFlujo() != null) { entidad.setCveFlujo(request.getCveFlujo()); }
        if (request.getDescFlujo() != null) { entidad.setDescFlujo(request.getDescFlujo()); }
        if (request.getNivelServicio() != null) { entidad.setNivelServicio(request.getNivelServicio()); }
        if (request.getNomServAxway() != null) { entidad.setNomServAxway(request.getNomServAxway()); }
        if (request.getNomMensajeAxway() != null) { entidad.setNomMensajeAxway(request.getNomMensajeAxway()); }
        if (request.getUrlAxway() != null) { entidad.setUrlAxway(request.getUrlAxway()); }
        if (request.getFecCaptura() != null) { entidad.setFecCaptura(request.getFecCaptura()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getNombre() != null) { entidad.setNombre(request.getNombre()); }
        if (request.getBlnReplicaInfo() != null) { entidad.setBlnReplicaInfo(request.getBlnReplicaInfo()); }
        if (request.getBlnAutomatico() != null) { entidad.setBlnAutomatico(request.getBlnAutomatico()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getBlnAsignacion() != null) { entidad.setBlnAsignacion(request.getBlnAsignacion()); }
        if (request.getCveModulo() != null) { entidad.setCveModulo(request.getCveModulo()); }
        if (request.getIdDependencia() != null) {
            final Optional<CatDependencia> dependencia = dependenciaRepository.findById(request.getIdDependencia().longValue());
            dependencia.ifPresent(entidad::setIdDependencia);
        }
        return exito(tipoTramiteMapper.toResponse(tipoTramiteRepository.save(entidad)));
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
