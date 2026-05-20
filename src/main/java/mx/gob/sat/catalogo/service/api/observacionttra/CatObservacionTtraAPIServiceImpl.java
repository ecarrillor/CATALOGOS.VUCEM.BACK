package mx.gob.sat.catalogo.service.api.observacionttra;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.observacionttra.CatObservacionTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.observacionttra.CatObservacionTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatObservacionTtra;
import mx.gob.sat.catalogo.model.entity.CatTipoTramite;
import mx.gob.sat.catalogo.repository.CatObservacionTtraRepository;
import mx.gob.sat.catalogo.repository.CatTipoTramiteRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatObservacionTtraMapper;
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
class CatObservacionTtraAPIServiceImpl implements CatObservacionTtraAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idObservacionTtra", "idObservacionTtra"
    );
    private static final String DEFAULT_SORT = "idObservacionTtra";

    private final CatObservacionTtraRepository observacionTtraRepository;
    private final CatTipoTramiteRepository tipoTramiteRepository;
    private final CatObservacionTtraMapper observacionTtraMapper;

    @Override
    public BaseResponse<PaginaResponse<CatObservacionTtraResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatObservacionTtra> paginaResultado = observacionTtraRepository.findAll(pageable);
        final List<CatObservacionTtraResponse> contenido = observacionTtraMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatObservacionTtraResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatObservacionTtraResponse> findById(final Long idObservacionTtra) {
        final Optional<CatObservacionTtra> resultado = observacionTtraRepository.findById(idObservacionTtra);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.OBSERVACION_TTRA_NO_ENCONTRADA, "Observacion Ttra no encontrada: " + idObservacionTtra);
        }
        return exito(observacionTtraMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatObservacionTtraResponse> crear(final CatObservacionTtraRequest request) {
        final CatObservacionTtra entidad = new CatObservacionTtra();
        entidad.setIdObservacionTtra(request.getIdObservacionTtra());
        entidad.setDescObservacionDict(request.getDescObservacionDict());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        if (request.getIdTipoTramite() != null) {
            final Optional<CatTipoTramite> tipoTramite = tipoTramiteRepository.findById(request.getIdTipoTramite());
            tipoTramite.ifPresent(entidad::setIdTipoTramite);
        }
        final CatObservacionTtra guardado = observacionTtraRepository.save(entidad);
        log.info("Observacion Ttra creada: {}", guardado.getIdObservacionTtra());
        return exito(observacionTtraMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatObservacionTtraResponse> actualizar(final Long idObservacionTtra, final CatObservacionTtraRequest request) {
        final Optional<CatObservacionTtra> opt = observacionTtraRepository.findById(idObservacionTtra);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.OBSERVACION_TTRA_NO_ENCONTRADA, "Observacion Ttra no encontrada: " + idObservacionTtra);
        }
        final CatObservacionTtra entidad = opt.get();
        if (request.getDescObservacionDict() != null) { entidad.setDescObservacionDict(request.getDescObservacionDict()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getIdTipoTramite() != null) {
            final Optional<CatTipoTramite> tipoTramite = tipoTramiteRepository.findById(request.getIdTipoTramite());
            tipoTramite.ifPresent(entidad::setIdTipoTramite);
        }
        return exito(observacionTtraMapper.toResponse(observacionTtraRepository.save(entidad)));
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
