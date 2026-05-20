package mx.gob.sat.catalogo.service.api.mediotransportettra;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.mediotransportettra.CatMedioTransporteTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.mediotransportettra.CatMedioTransporteTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatMedioTransporteTtra;
import mx.gob.sat.catalogo.model.entity.CatTipoTramite;
import mx.gob.sat.catalogo.repository.CatMedioTransporteTtraRepository;
import mx.gob.sat.catalogo.repository.CatTipoTramiteRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatMedioTransporteTtraMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatMedioTransporteTtraAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de medios de transporte ttra.</p>
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
class CatMedioTransporteTtraAPIServiceImpl implements CatMedioTransporteTtraAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idMedioTransporteTtra", "idMedioTransporteTtra",
            "ideMedioTransporteGob", "ideMedioTransporteGob");
    private static final String DEFAULT_SORT = "idMedioTransporteTtra";

    private final CatMedioTransporteTtraRepository medioTransporteTtraRepository;
    private final CatTipoTramiteRepository tipoTramiteRepository;
    private final CatMedioTransporteTtraMapper medioTransporteTtraMapper;

    @Override
    public BaseResponse<PaginaResponse<CatMedioTransporteTtraResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatMedioTransporteTtra> paginaResultado = medioTransporteTtraRepository.findAll(pageable);
        final List<CatMedioTransporteTtraResponse> contenido =
                medioTransporteTtraMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatMedioTransporteTtraResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatMedioTransporteTtraResponse> findById(final Integer idMedioTransporteTtra) {
        final Optional<CatMedioTransporteTtra> resultado =
                medioTransporteTtraRepository.findById(idMedioTransporteTtra);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.MEDIO_TRANSPORTE_TTRA_NO_ENCONTRADO,
                    "Medio de transporte ttra no encontrado: " + idMedioTransporteTtra);
        }
        return exito(medioTransporteTtraMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatMedioTransporteTtraResponse> crear(final CatMedioTransporteTtraRequest request) {
        final CatMedioTransporteTtra entidad = new CatMedioTransporteTtra();
        entidad.setIdeMedioTransporteGob(request.getIdeMedioTransporteGob());
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

        final CatMedioTransporteTtra guardada = medioTransporteTtraRepository.save(entidad);
        log.info("Medio de transporte ttra creado: {}", guardada.getIdMedioTransporteTtra());
        return exito(medioTransporteTtraMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatMedioTransporteTtraResponse> actualizar(
            final Integer idMedioTransporteTtra, final CatMedioTransporteTtraRequest request) {
        final Optional<CatMedioTransporteTtra> opt =
                medioTransporteTtraRepository.findById(idMedioTransporteTtra);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.MEDIO_TRANSPORTE_TTRA_NO_ENCONTRADO,
                    "Medio de transporte ttra no encontrado: " + idMedioTransporteTtra);
        }
        final CatMedioTransporteTtra entidad = opt.get();
        if (request.getIdeMedioTransporteGob() != null) { entidad.setIdeMedioTransporteGob(request.getIdeMedioTransporteGob()); }
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
        return exito(medioTransporteTtraMapper.toResponse(medioTransporteTtraRepository.save(entidad)));
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
