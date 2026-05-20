package mx.gob.sat.catalogo.service.api.usoespecmercanciattra;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.usoespecmercanciattra.CatUsoEspecMercanciaTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.usoespecmercanciattra.CatUsoEspecMercanciaTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatTipoTramite;
import mx.gob.sat.catalogo.model.entity.CatUsoEspecMercanciaTtra;
import mx.gob.sat.catalogo.repository.CatTipoTramiteRepository;
import mx.gob.sat.catalogo.repository.CatUsoEspecMercanciaTtraRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatUsoEspecMercanciaTtraMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatUsoEspecMercanciaTtraAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de uso especial de mercancia ttra.</p>
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
class CatUsoEspecMercanciaTtraAPIServiceImpl implements CatUsoEspecMercanciaTtraAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idUsoEspecMercanciaTtra", "idUsoEspecMercanciaTtra");
    private static final String DEFAULT_SORT = "idUsoEspecMercanciaTtra";

    private final CatUsoEspecMercanciaTtraRepository usoEspecMercanciaTtraRepository;
    private final CatTipoTramiteRepository tipoTramiteRepository;
    private final CatUsoEspecMercanciaTtraMapper usoEspecMercanciaTtraMapper;

    @Override
    public BaseResponse<PaginaResponse<CatUsoEspecMercanciaTtraResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatUsoEspecMercanciaTtra> paginaResultado = usoEspecMercanciaTtraRepository.findAll(pageable);
        final List<CatUsoEspecMercanciaTtraResponse> contenido =
                usoEspecMercanciaTtraMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatUsoEspecMercanciaTtraResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatUsoEspecMercanciaTtraResponse> findById(final Short idUsoEspecMercanciaTtra) {
        final Optional<CatUsoEspecMercanciaTtra> resultado =
                usoEspecMercanciaTtraRepository.findById(idUsoEspecMercanciaTtra);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.USO_ESPEC_MERCANCIA_TTRA_NO_ENCONTRADO,
                    "Uso especial de mercancia ttra no encontrado: " + idUsoEspecMercanciaTtra);
        }
        return exito(usoEspecMercanciaTtraMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatUsoEspecMercanciaTtraResponse> crear(final CatUsoEspecMercanciaTtraRequest request) {
        final CatUsoEspecMercanciaTtra entidad = new CatUsoEspecMercanciaTtra();
        entidad.setDescUsoEspMercancia(request.getDescUsoEspMercancia());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        entidad.setBlnReqRegistroSanitario(request.getBlnReqRegistroSanitario());

        if (request.getIdTipoTramite() != null) {
            final Optional<CatTipoTramite> tipoTramite =
                    tipoTramiteRepository.findById(request.getIdTipoTramite());
            if (tipoTramite.isEmpty()) {
                return error(CodigoErrorConst.TIPO_TRAMITE_NO_ENCONTRADO,
                        "Tipo de tramite no encontrado: " + request.getIdTipoTramite());
            }
            entidad.setIdTipoTramite(tipoTramite.get());
        }

        final CatUsoEspecMercanciaTtra guardada = usoEspecMercanciaTtraRepository.save(entidad);
        log.info("Uso especial de mercancia ttra creado: {}", guardada.getIdUsoEspecMercanciaTtra());
        return exito(usoEspecMercanciaTtraMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatUsoEspecMercanciaTtraResponse> actualizar(
            final Short idUsoEspecMercanciaTtra, final CatUsoEspecMercanciaTtraRequest request) {
        final Optional<CatUsoEspecMercanciaTtra> opt =
                usoEspecMercanciaTtraRepository.findById(idUsoEspecMercanciaTtra);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.USO_ESPEC_MERCANCIA_TTRA_NO_ENCONTRADO,
                    "Uso especial de mercancia ttra no encontrado: " + idUsoEspecMercanciaTtra);
        }
        final CatUsoEspecMercanciaTtra entidad = opt.get();
        if (request.getDescUsoEspMercancia() != null) { entidad.setDescUsoEspMercancia(request.getDescUsoEspMercancia()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getBlnReqRegistroSanitario() != null) { entidad.setBlnReqRegistroSanitario(request.getBlnReqRegistroSanitario()); }
        if (request.getIdTipoTramite() != null) {
            final Optional<CatTipoTramite> tipoTramite =
                    tipoTramiteRepository.findById(request.getIdTipoTramite());
            if (tipoTramite.isEmpty()) {
                return error(CodigoErrorConst.TIPO_TRAMITE_NO_ENCONTRADO,
                        "Tipo de tramite no encontrado: " + request.getIdTipoTramite());
            }
            entidad.setIdTipoTramite(tipoTramite.get());
        }
        return exito(usoEspecMercanciaTtraMapper.toResponse(usoEspecMercanciaTtraRepository.save(entidad)));
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
