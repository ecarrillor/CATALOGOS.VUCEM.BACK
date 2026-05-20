package mx.gob.sat.catalogo.service.api.seccionttra;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.seccionttra.CatSeccionTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.seccionttra.CatSeccionTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatSeccionTtra;
import mx.gob.sat.catalogo.model.entity.CatTipoTramite;
import mx.gob.sat.catalogo.repository.CatSeccionTtraRepository;
import mx.gob.sat.catalogo.repository.CatTipoTramiteRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatSeccionTtraMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatSeccionTtraAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de secciones ttra.</p>
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
class CatSeccionTtraAPIServiceImpl implements CatSeccionTtraAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idSeccionTtra", "idSeccionTtra");
    private static final String DEFAULT_SORT = "idSeccionTtra";

    private final CatSeccionTtraRepository seccionTtraRepository;
    private final CatTipoTramiteRepository tipoTramiteRepository;
    private final CatSeccionTtraMapper seccionTtraMapper;

    @Override
    public BaseResponse<PaginaResponse<CatSeccionTtraResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatSeccionTtra> paginaResultado = seccionTtraRepository.findAll(pageable);
        final List<CatSeccionTtraResponse> contenido = seccionTtraMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatSeccionTtraResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatSeccionTtraResponse> findById(final Integer idSeccionTtra) {
        final Optional<CatSeccionTtra> resultado = seccionTtraRepository.findById(idSeccionTtra);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.SECCION_TTRA_NO_ENCONTRADA, "Seccion ttra no encontrada: " + idSeccionTtra);
        }
        return exito(seccionTtraMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatSeccionTtraResponse> crear(final CatSeccionTtraRequest request) {
        final CatSeccionTtra entidad = new CatSeccionTtra();
        entidad.setDescSeccion(request.getDescSeccion());
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

        final CatSeccionTtra guardada = seccionTtraRepository.save(entidad);
        log.info("Seccion ttra creada: {}", guardada.getIdSeccionTtra());
        return exito(seccionTtraMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatSeccionTtraResponse> actualizar(final Integer idSeccionTtra, final CatSeccionTtraRequest request) {
        final Optional<CatSeccionTtra> opt = seccionTtraRepository.findById(idSeccionTtra);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.SECCION_TTRA_NO_ENCONTRADA, "Seccion ttra no encontrada: " + idSeccionTtra);
        }
        final CatSeccionTtra entidad = opt.get();
        if (request.getDescSeccion() != null) { entidad.setDescSeccion(request.getDescSeccion()); }
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
        return exito(seccionTtraMapper.toResponse(seccionTtraRepository.save(entidad)));
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
