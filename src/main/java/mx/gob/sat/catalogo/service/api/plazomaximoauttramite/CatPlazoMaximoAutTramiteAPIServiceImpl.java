package mx.gob.sat.catalogo.service.api.plazomaximoauttramite;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.plazomaximoauttramite.CatPlazoMaximoAutTramiteRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.plazomaximoauttramite.CatPlazoMaximoAutTramiteResponse;
import mx.gob.sat.catalogo.model.entity.CatPlazoMaximoAutTramite;
import mx.gob.sat.catalogo.model.entity.CatPlazoMaximoAutTramiteId;
import mx.gob.sat.catalogo.repository.CatPlazoMaximoAutTramiteRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatPlazoMaximoAutTramiteMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatPlazoMaximoAutTramiteAPIServiceImpl implements CatPlazoMaximoAutTramiteAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idTipoTramite", "id.idTipoTramite",
            "fecIniVigencia", "id.fecIniVigencia");
    private static final String DEFAULT_SORT = "id.idTipoTramite";

    private final CatPlazoMaximoAutTramiteRepository plazoMaximoAutTramiteRepository;
    private final CatPlazoMaximoAutTramiteMapper plazoMaximoAutTramiteMapper;

    @Override
    public BaseResponse<PaginaResponse<CatPlazoMaximoAutTramiteResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted() ? PageRequest.of(pagina, tamano, sort) : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatPlazoMaximoAutTramite> paginaResultado = plazoMaximoAutTramiteRepository.findAll(pageable);
        final List<CatPlazoMaximoAutTramiteResponse> contenido = plazoMaximoAutTramiteMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatPlazoMaximoAutTramiteResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatPlazoMaximoAutTramiteResponse> findById(final Long idTipoTramite, final LocalDate fecIniVigencia) {
        final CatPlazoMaximoAutTramiteId id = new CatPlazoMaximoAutTramiteId();
        id.setIdTipoTramite(idTipoTramite);
        id.setFecIniVigencia(fecIniVigencia);
        final Optional<CatPlazoMaximoAutTramite> resultado = plazoMaximoAutTramiteRepository.findById(id);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.PLAZO_MAXIMO_AUT_TRAMITE_NO_ENCONTRADO,
                    "Plazo maximo aut tramite no encontrado: " + idTipoTramite + "/" + fecIniVigencia);
        }
        return exito(plazoMaximoAutTramiteMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatPlazoMaximoAutTramiteResponse> crear(final CatPlazoMaximoAutTramiteRequest request) {
        final CatPlazoMaximoAutTramite entidad = new CatPlazoMaximoAutTramite();
        final CatPlazoMaximoAutTramiteId id = new CatPlazoMaximoAutTramiteId();
        id.setIdTipoTramite(request.getIdTipoTramite());
        id.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setId(id);
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setPlazoAnios(request.getPlazoAnios());
        entidad.setIdePlazoMeses(request.getIdePlazoMeses());
        entidad.setBlnIlimitado(request.getBlnIlimitado());
        entidad.setPlazo(request.getPlazo());
        entidad.setBlnAsignacionFechaFin(request.getBlnAsignacionFechaFin());
        entidad.setBlnActivo(request.getBlnActivo());
        final CatPlazoMaximoAutTramite guardado = plazoMaximoAutTramiteRepository.save(entidad);
        log.info("Plazo maximo aut tramite creado: {}/{}", guardado.getId().getIdTipoTramite(), guardado.getId().getFecIniVigencia());
        return exito(plazoMaximoAutTramiteMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatPlazoMaximoAutTramiteResponse> actualizar(final Long idTipoTramite, final LocalDate fecIniVigencia, final CatPlazoMaximoAutTramiteRequest request) {
        final CatPlazoMaximoAutTramiteId id = new CatPlazoMaximoAutTramiteId();
        id.setIdTipoTramite(idTipoTramite);
        id.setFecIniVigencia(fecIniVigencia);
        final Optional<CatPlazoMaximoAutTramite> opt = plazoMaximoAutTramiteRepository.findById(id);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.PLAZO_MAXIMO_AUT_TRAMITE_NO_ENCONTRADO,
                    "Plazo maximo aut tramite no encontrado: " + idTipoTramite + "/" + fecIniVigencia);
        }
        final CatPlazoMaximoAutTramite entidad = opt.get();
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getPlazoAnios() != null) { entidad.setPlazoAnios(request.getPlazoAnios()); }
        if (request.getIdePlazoMeses() != null) { entidad.setIdePlazoMeses(request.getIdePlazoMeses()); }
        if (request.getBlnIlimitado() != null) { entidad.setBlnIlimitado(request.getBlnIlimitado()); }
        if (request.getPlazo() != null) { entidad.setPlazo(request.getPlazo()); }
        if (request.getBlnAsignacionFechaFin() != null) { entidad.setBlnAsignacionFechaFin(request.getBlnAsignacionFechaFin()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(plazoMaximoAutTramiteMapper.toResponse(plazoMaximoAutTramiteRepository.save(entidad)));
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
