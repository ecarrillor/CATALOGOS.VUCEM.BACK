package mx.gob.sat.catalogo.service.api.fundamentottra;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.fundamentottra.CatFundamentoTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.fundamentottra.CatFundamentoTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatFundamentoTtra;
import mx.gob.sat.catalogo.model.entity.CatTipoTramite;
import mx.gob.sat.catalogo.repository.CatFundamentoTtraRepository;
import mx.gob.sat.catalogo.repository.CatTipoTramiteRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatFundamentoTtraMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatFundamentoTtraAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de fundamentos ttra.</p>
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
class CatFundamentoTtraAPIServiceImpl implements CatFundamentoTtraAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idFundamentoTtra", "idFundamentoTtra");
    private static final String DEFAULT_SORT = "idFundamentoTtra";

    private final CatFundamentoTtraRepository fundamentoTtraRepository;
    private final CatTipoTramiteRepository tipoTramiteRepository;
    private final CatFundamentoTtraMapper fundamentoTtraMapper;

    @Override
    public BaseResponse<PaginaResponse<CatFundamentoTtraResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatFundamentoTtra> paginaResultado = fundamentoTtraRepository.findAll(pageable);
        final List<CatFundamentoTtraResponse> contenido = fundamentoTtraMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatFundamentoTtraResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatFundamentoTtraResponse> findById(final Short idFundamentoTtra) {
        final Optional<CatFundamentoTtra> resultado = fundamentoTtraRepository.findById(idFundamentoTtra);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.FUNDAMENTO_TTRA_NO_ENCONTRADO, "Fundamento ttra no encontrado: " + idFundamentoTtra);
        }
        return exito(fundamentoTtraMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatFundamentoTtraResponse> crear(final CatFundamentoTtraRequest request) {
        final CatFundamentoTtra entidad = new CatFundamentoTtra();
        entidad.setIdeTipoFundamentoTtra(request.getIdeTipoFundamentoTtra());
        entidad.setDescFundamento(request.getDescFundamento());
        entidad.setDescContenidoFundamento(request.getDescContenidoFundamento());
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

        final CatFundamentoTtra guardada = fundamentoTtraRepository.save(entidad);
        log.info("Fundamento ttra creado: {}", guardada.getIdFundamentoTtra());
        return exito(fundamentoTtraMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatFundamentoTtraResponse> actualizar(final Short idFundamentoTtra, final CatFundamentoTtraRequest request) {
        final Optional<CatFundamentoTtra> opt = fundamentoTtraRepository.findById(idFundamentoTtra);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.FUNDAMENTO_TTRA_NO_ENCONTRADO, "Fundamento ttra no encontrado: " + idFundamentoTtra);
        }
        final CatFundamentoTtra entidad = opt.get();
        if (request.getIdeTipoFundamentoTtra() != null) { entidad.setIdeTipoFundamentoTtra(request.getIdeTipoFundamentoTtra()); }
        if (request.getDescFundamento() != null) { entidad.setDescFundamento(request.getDescFundamento()); }
        if (request.getDescContenidoFundamento() != null) { entidad.setDescContenidoFundamento(request.getDescContenidoFundamento()); }
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
        return exito(fundamentoTtraMapper.toResponse(fundamentoTtraRepository.save(entidad)));
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
