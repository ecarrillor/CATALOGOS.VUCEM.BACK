package mx.gob.sat.catalogo.service.api.usomercanciattra;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.usomercanciattra.CatUsoMercanciaTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.usomercanciattra.CatUsoMercanciaTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatTipoTramite;
import mx.gob.sat.catalogo.model.entity.CatUsoMercanciaTtra;
import mx.gob.sat.catalogo.repository.CatTipoTramiteRepository;
import mx.gob.sat.catalogo.repository.CatUsoMercanciaTtraRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatUsoMercanciaTtraMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatUsoMercanciaTtraAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de uso de mercancia ttra.</p>
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
class CatUsoMercanciaTtraAPIServiceImpl implements CatUsoMercanciaTtraAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idUsoMercanciaTtra", "idUsoMercanciaTtra",
            "descUsoMercancia", "descUsoMercancia");
    private static final String DEFAULT_SORT = "idUsoMercanciaTtra";

    private final CatUsoMercanciaTtraRepository usoMercanciaTtraRepository;
    private final CatTipoTramiteRepository tipoTramiteRepository;
    private final CatUsoMercanciaTtraMapper usoMercanciaTtraMapper;

    @Override
    public BaseResponse<PaginaResponse<CatUsoMercanciaTtraResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatUsoMercanciaTtra> paginaResultado = usoMercanciaTtraRepository.findAll(pageable);
        final List<CatUsoMercanciaTtraResponse> contenido =
                usoMercanciaTtraMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatUsoMercanciaTtraResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatUsoMercanciaTtraResponse> findById(final Short idUsoMercanciaTtra) {
        final Optional<CatUsoMercanciaTtra> resultado =
                usoMercanciaTtraRepository.findById(idUsoMercanciaTtra);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.USO_MERCANCIA_TTRA_NO_ENCONTRADO,
                    "Uso de mercancia ttra no encontrado: " + idUsoMercanciaTtra);
        }
        return exito(usoMercanciaTtraMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatUsoMercanciaTtraResponse> crear(final CatUsoMercanciaTtraRequest request) {
        final CatUsoMercanciaTtra entidad = new CatUsoMercanciaTtra();
        entidad.setDescUsoMercancia(request.getDescUsoMercancia());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());

        if (request.getIdTipoTramite() != null) {
            final Optional<CatTipoTramite> tipoTramite =
                    tipoTramiteRepository.findById(request.getIdTipoTramite());
            if (tipoTramite.isEmpty()) {
                return error(CodigoErrorConst.TIPO_TRAMITE_NO_ENCONTRADO,
                        "Tipo de tramite no encontrado: " + request.getIdTipoTramite());
            }
            entidad.setIdTipoTramite(tipoTramite.get());
        }

        final CatUsoMercanciaTtra guardada = usoMercanciaTtraRepository.save(entidad);
        log.info("Uso de mercancia ttra creado: {}", guardada.getIdUsoMercanciaTtra());
        return exito(usoMercanciaTtraMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatUsoMercanciaTtraResponse> actualizar(
            final Short idUsoMercanciaTtra, final CatUsoMercanciaTtraRequest request) {
        final Optional<CatUsoMercanciaTtra> opt =
                usoMercanciaTtraRepository.findById(idUsoMercanciaTtra);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.USO_MERCANCIA_TTRA_NO_ENCONTRADO,
                    "Uso de mercancia ttra no encontrado: " + idUsoMercanciaTtra);
        }
        final CatUsoMercanciaTtra entidad = opt.get();
        if (request.getDescUsoMercancia() != null) { entidad.setDescUsoMercancia(request.getDescUsoMercancia()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getIdTipoTramite() != null) {
            final Optional<CatTipoTramite> tipoTramite =
                    tipoTramiteRepository.findById(request.getIdTipoTramite());
            if (tipoTramite.isEmpty()) {
                return error(CodigoErrorConst.TIPO_TRAMITE_NO_ENCONTRADO,
                        "Tipo de tramite no encontrado: " + request.getIdTipoTramite());
            }
            entidad.setIdTipoTramite(tipoTramite.get());
        }
        return exito(usoMercanciaTtraMapper.toResponse(usoMercanciaTtraRepository.save(entidad)));
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
