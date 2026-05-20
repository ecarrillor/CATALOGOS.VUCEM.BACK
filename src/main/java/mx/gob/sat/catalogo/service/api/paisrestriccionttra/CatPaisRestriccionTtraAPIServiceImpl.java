package mx.gob.sat.catalogo.service.api.paisrestriccionttra;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.paisrestriccionttra.CatPaisRestriccionTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.paisrestriccionttra.CatPaisRestriccionTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatPais;
import mx.gob.sat.catalogo.model.entity.CatPaisRestriccionTtra;
import mx.gob.sat.catalogo.model.entity.CatTipoTramite;
import mx.gob.sat.catalogo.repository.CatPaisRepository;
import mx.gob.sat.catalogo.repository.CatPaisRestriccionTtraRepository;
import mx.gob.sat.catalogo.repository.CatTipoTramiteRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatPaisRestriccionTtraMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatPaisRestriccionTtraAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de paises restriccion TTRA.</p>
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
class CatPaisRestriccionTtraAPIServiceImpl implements CatPaisRestriccionTtraAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idPaisRestriccionTtra", "idPaisRestriccionTtra");

    private static final String DEFAULT_SORT = "idPaisRestriccionTtra";

    private final CatPaisRestriccionTtraRepository paisRestriccionTtraRepository;
    private final CatTipoTramiteRepository tipoTramiteRepository;
    private final CatPaisRepository paisRepository;
    private final CatPaisRestriccionTtraMapper paisRestriccionTtraMapper;

    @Override
    public BaseResponse<PaginaResponse<CatPaisRestriccionTtraResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatPaisRestriccionTtra> paginaResultado = paisRestriccionTtraRepository.findAll(pageable);

        final List<CatPaisRestriccionTtraResponse> contenido =
                paisRestriccionTtraMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatPaisRestriccionTtraResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatPaisRestriccionTtraResponse> findById(final Integer idPaisRestriccionTtra) {
        final Optional<CatPaisRestriccionTtra> resultado =
                paisRestriccionTtraRepository.findById(idPaisRestriccionTtra);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.PAIS_RESTRICCION_TTRA_NO_ENCONTRADO,
                    "Pais restriccion TTRA no encontrado: " + idPaisRestriccionTtra);
        }
        return exito(paisRestriccionTtraMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatPaisRestriccionTtraResponse> crear(final CatPaisRestriccionTtraRequest request) {
        final CatPaisRestriccionTtra entidad = new CatPaisRestriccionTtra();
        entidad.setIdeTipoRestriccionPaisTtra(request.getIdeTipoRestriccionPaisTtra());
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

        if (request.getCvePais() != null) {
            final Optional<CatPais> pais = paisRepository.findById(request.getCvePais());
            if (pais.isEmpty()) {
                return error(CodigoErrorConst.PAIS_NO_ENCONTRADO,
                        "Pais no encontrado: " + request.getCvePais());
            }
            entidad.setCvePais(pais.get());
        }

        final CatPaisRestriccionTtra guardada = paisRestriccionTtraRepository.save(entidad);
        log.info("Pais restriccion TTRA creado: {}", guardada.getIdPaisRestriccionTtra());
        return exito(paisRestriccionTtraMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatPaisRestriccionTtraResponse> actualizar(
            final Integer idPaisRestriccionTtra, final CatPaisRestriccionTtraRequest request) {

        final Optional<CatPaisRestriccionTtra> opt =
                paisRestriccionTtraRepository.findById(idPaisRestriccionTtra);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.PAIS_RESTRICCION_TTRA_NO_ENCONTRADO,
                    "Pais restriccion TTRA no encontrado: " + idPaisRestriccionTtra);
        }
        final CatPaisRestriccionTtra entidad = opt.get();
        if (request.getIdeTipoRestriccionPaisTtra() != null) {
            entidad.setIdeTipoRestriccionPaisTtra(request.getIdeTipoRestriccionPaisTtra());
        }
        if (request.getFecIniVigencia() != null) {
            entidad.setFecIniVigencia(request.getFecIniVigencia());
        }
        if (request.getFecFinVigencia() != null) {
            entidad.setFecFinVigencia(request.getFecFinVigencia());
        }
        if (request.getBlnActivo() != null) {
            entidad.setBlnActivo(request.getBlnActivo());
        }
        if (request.getIdTipoTramite() != null) {
            final Optional<CatTipoTramite> tipoTramite =
                    tipoTramiteRepository.findById(request.getIdTipoTramite());
            if (tipoTramite.isEmpty()) {
                return error(CodigoErrorConst.TIPO_TRAMITE_NO_ENCONTRADO,
                        "Tipo de tramite no encontrado: " + request.getIdTipoTramite());
            }
            entidad.setIdTipoTramite(tipoTramite.get());
        }
        if (request.getCvePais() != null) {
            final Optional<CatPais> pais = paisRepository.findById(request.getCvePais());
            if (pais.isEmpty()) {
                return error(CodigoErrorConst.PAIS_NO_ENCONTRADO,
                        "Pais no encontrado: " + request.getCvePais());
            }
            entidad.setCvePais(pais.get());
        }
        return exito(paisRestriccionTtraMapper.toResponse(paisRestriccionTtraRepository.save(entidad)));
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
