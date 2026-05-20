package mx.gob.sat.catalogo.service.api.justificacionttra;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.justificacionttra.CatJustificacionTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.justificacionttra.CatJustificacionTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatJustificacionTtra;
import mx.gob.sat.catalogo.model.entity.CatTipoTramite;
import mx.gob.sat.catalogo.repository.CatJustificacionTtraRepository;
import mx.gob.sat.catalogo.repository.CatTipoTramiteRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatJustificacionTtraMapper;
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
class CatJustificacionTtraAPIServiceImpl implements CatJustificacionTtraAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idJustificacionTtra", "idJustificacionTtra"
    );
    private static final String DEFAULT_SORT = "idJustificacionTtra";

    private final CatJustificacionTtraRepository justificacionTtraRepository;
    private final CatTipoTramiteRepository tipoTramiteRepository;
    private final CatJustificacionTtraMapper justificacionTtraMapper;

    @Override
    public BaseResponse<PaginaResponse<CatJustificacionTtraResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatJustificacionTtra> paginaResultado = justificacionTtraRepository.findAll(pageable);
        final List<CatJustificacionTtraResponse> contenido = justificacionTtraMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatJustificacionTtraResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatJustificacionTtraResponse> findById(final Long idJustificacionTtra) {
        final Optional<CatJustificacionTtra> resultado = justificacionTtraRepository.findById(idJustificacionTtra);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.JUSTIFICACION_TTRA_NO_ENCONTRADA, "Justificacion Ttra no encontrada: " + idJustificacionTtra);
        }
        return exito(justificacionTtraMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatJustificacionTtraResponse> crear(final CatJustificacionTtraRequest request) {
        final CatJustificacionTtra entidad = new CatJustificacionTtra();
        entidad.setIdJustificacionTtra(request.getIdJustificacionTtra());
        entidad.setDescJustificacion(request.getDescJustificacion());
        entidad.setDescContenidoJustificacion(request.getDescContenidoJustificacion());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        if (request.getIdTipoTramite() != null) {
            final Optional<CatTipoTramite> tipoTramite = tipoTramiteRepository.findById(request.getIdTipoTramite());
            tipoTramite.ifPresent(entidad::setIdTipoTramite);
        }
        final CatJustificacionTtra guardado = justificacionTtraRepository.save(entidad);
        log.info("Justificacion Ttra creada: {}", guardado.getIdJustificacionTtra());
        return exito(justificacionTtraMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatJustificacionTtraResponse> actualizar(final Long idJustificacionTtra, final CatJustificacionTtraRequest request) {
        final Optional<CatJustificacionTtra> opt = justificacionTtraRepository.findById(idJustificacionTtra);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.JUSTIFICACION_TTRA_NO_ENCONTRADA, "Justificacion Ttra no encontrada: " + idJustificacionTtra);
        }
        final CatJustificacionTtra entidad = opt.get();
        if (request.getDescJustificacion() != null) { entidad.setDescJustificacion(request.getDescJustificacion()); }
        if (request.getDescContenidoJustificacion() != null) { entidad.setDescContenidoJustificacion(request.getDescContenidoJustificacion()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getIdTipoTramite() != null) {
            final Optional<CatTipoTramite> tipoTramite = tipoTramiteRepository.findById(request.getIdTipoTramite());
            tipoTramite.ifPresent(entidad::setIdTipoTramite);
        }
        return exito(justificacionTtraMapper.toResponse(justificacionTtraRepository.save(entidad)));
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
