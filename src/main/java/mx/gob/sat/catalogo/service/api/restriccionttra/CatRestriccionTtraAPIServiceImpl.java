package mx.gob.sat.catalogo.service.api.restriccionttra;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.restriccionttra.CatRestriccionTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.restriccionttra.CatRestriccionTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatRestriccionTtra;
import mx.gob.sat.catalogo.model.entity.CatTipoTramite;
import mx.gob.sat.catalogo.repository.CatRestriccionTtraRepository;
import mx.gob.sat.catalogo.repository.CatTipoTramiteRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatRestriccionTtraMapper;
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
class CatRestriccionTtraAPIServiceImpl implements CatRestriccionTtraAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idRestriccionTtra", "idRestriccionTtra"
    );
    private static final String DEFAULT_SORT = "idRestriccionTtra";

    private final CatRestriccionTtraRepository restriccionTtraRepository;
    private final CatTipoTramiteRepository tipoTramiteRepository;
    private final CatRestriccionTtraMapper restriccionTtraMapper;

    @Override
    public BaseResponse<PaginaResponse<CatRestriccionTtraResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatRestriccionTtra> paginaResultado = restriccionTtraRepository.findAll(pageable);
        final List<CatRestriccionTtraResponse> contenido = restriccionTtraMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatRestriccionTtraResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatRestriccionTtraResponse> findById(final Short idRestriccionTtra) {
        final Optional<CatRestriccionTtra> resultado = restriccionTtraRepository.findById(idRestriccionTtra);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.RESTRICCION_TTRA_NO_ENCONTRADA, "Restriccion Ttra no encontrada: " + idRestriccionTtra);
        }
        return exito(restriccionTtraMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatRestriccionTtraResponse> crear(final CatRestriccionTtraRequest request) {
        final CatRestriccionTtra entidad = new CatRestriccionTtra();
        entidad.setIdRestriccionTtra(request.getIdRestriccionTtra());
        entidad.setDescRestriccion(request.getDescRestriccion());
        entidad.setDescContenidoRestriccion(request.getDescContenidoRestriccion());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        entidad.setIdeSentDictamen(request.getIdeSentDictamen());
        entidad.setIdeTipoRestriccionTtra(request.getIdeTipoRestriccionTtra());
        entidad.setIdeMotivoRechazoDict(request.getIdeMotivoRechazoDict());
        if (request.getIdTipoTramite() != null) {
            final Optional<CatTipoTramite> tipoTramite = tipoTramiteRepository.findById(request.getIdTipoTramite());
            tipoTramite.ifPresent(entidad::setIdTipoTramite);
        }
        final CatRestriccionTtra guardado = restriccionTtraRepository.save(entidad);
        log.info("Restriccion Ttra creada: {}", guardado.getIdRestriccionTtra());
        return exito(restriccionTtraMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatRestriccionTtraResponse> actualizar(final Short idRestriccionTtra, final CatRestriccionTtraRequest request) {
        final Optional<CatRestriccionTtra> opt = restriccionTtraRepository.findById(idRestriccionTtra);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.RESTRICCION_TTRA_NO_ENCONTRADA, "Restriccion Ttra no encontrada: " + idRestriccionTtra);
        }
        final CatRestriccionTtra entidad = opt.get();
        if (request.getDescRestriccion() != null) { entidad.setDescRestriccion(request.getDescRestriccion()); }
        if (request.getDescContenidoRestriccion() != null) { entidad.setDescContenidoRestriccion(request.getDescContenidoRestriccion()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getIdeSentDictamen() != null) { entidad.setIdeSentDictamen(request.getIdeSentDictamen()); }
        if (request.getIdeTipoRestriccionTtra() != null) { entidad.setIdeTipoRestriccionTtra(request.getIdeTipoRestriccionTtra()); }
        if (request.getIdeMotivoRechazoDict() != null) { entidad.setIdeMotivoRechazoDict(request.getIdeMotivoRechazoDict()); }
        if (request.getIdTipoTramite() != null) {
            final Optional<CatTipoTramite> tipoTramite = tipoTramiteRepository.findById(request.getIdTipoTramite());
            tipoTramite.ifPresent(entidad::setIdTipoTramite);
        }
        return exito(restriccionTtraMapper.toResponse(restriccionTtraRepository.save(entidad)));
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
