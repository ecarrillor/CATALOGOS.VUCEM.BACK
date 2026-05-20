package mx.gob.sat.catalogo.service.api.unidadmedidattra;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.unidadmedidattra.CatUnidadMedidaTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.unidadmedidattra.CatUnidadMedidaTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatTipoTramite;
import mx.gob.sat.catalogo.model.entity.CatUnidadMedida;
import mx.gob.sat.catalogo.model.entity.CatUnidadMedidaTtra;
import mx.gob.sat.catalogo.repository.CatTipoTramiteRepository;
import mx.gob.sat.catalogo.repository.CatUnidadMedidaRepository;
import mx.gob.sat.catalogo.repository.CatUnidadMedidaTtraRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatUnidadMedidaTtraMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatUnidadMedidaTtraAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de unidad de medida ttra.</p>
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
class CatUnidadMedidaTtraAPIServiceImpl implements CatUnidadMedidaTtraAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idUnidadMedidaTtra", "idUnidadMedidaTtra");
    private static final String DEFAULT_SORT = "idUnidadMedidaTtra";

    private final CatUnidadMedidaTtraRepository unidadMedidaTtraRepository;
    private final CatUnidadMedidaRepository unidadMedidaRepository;
    private final CatTipoTramiteRepository tipoTramiteRepository;
    private final CatUnidadMedidaTtraMapper unidadMedidaTtraMapper;

    @Override
    public BaseResponse<PaginaResponse<CatUnidadMedidaTtraResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatUnidadMedidaTtra> paginaResultado = unidadMedidaTtraRepository.findAll(pageable);
        final List<CatUnidadMedidaTtraResponse> contenido =
                unidadMedidaTtraMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatUnidadMedidaTtraResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatUnidadMedidaTtraResponse> findById(final Integer idUnidadMedidaTtra) {
        final Optional<CatUnidadMedidaTtra> resultado =
                unidadMedidaTtraRepository.findById(idUnidadMedidaTtra);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.UNIDAD_MEDIDA_TTRA_NO_ENCONTRADA,
                    "Unidad de medida ttra no encontrada: " + idUnidadMedidaTtra);
        }
        return exito(unidadMedidaTtraMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatUnidadMedidaTtraResponse> crear(final CatUnidadMedidaTtraRequest request) {
        final CatUnidadMedidaTtra entidad = new CatUnidadMedidaTtra();
        entidad.setIdUnidadMedidaTtra(request.getIdUnidadMedidaTtra());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());

        if (request.getCveUnidadMedida() != null) {
            final Optional<CatUnidadMedida> unidadMedida =
                    unidadMedidaRepository.findById(request.getCveUnidadMedida());
            if (unidadMedida.isEmpty()) {
                return error(CodigoErrorConst.UNIDAD_MEDIDA_NO_ENCONTRADA,
                        "Unidad de medida no encontrada: " + request.getCveUnidadMedida());
            }
            entidad.setCveUnidadMedida(unidadMedida.get());
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

        final CatUnidadMedidaTtra guardada = unidadMedidaTtraRepository.save(entidad);
        log.info("Unidad de medida ttra creada: {}", guardada.getIdUnidadMedidaTtra());
        return exito(unidadMedidaTtraMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatUnidadMedidaTtraResponse> actualizar(
            final Integer idUnidadMedidaTtra, final CatUnidadMedidaTtraRequest request) {
        final Optional<CatUnidadMedidaTtra> opt =
                unidadMedidaTtraRepository.findById(idUnidadMedidaTtra);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.UNIDAD_MEDIDA_TTRA_NO_ENCONTRADA,
                    "Unidad de medida ttra no encontrada: " + idUnidadMedidaTtra);
        }
        final CatUnidadMedidaTtra entidad = opt.get();
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getCveUnidadMedida() != null) {
            final Optional<CatUnidadMedida> unidadMedida =
                    unidadMedidaRepository.findById(request.getCveUnidadMedida());
            if (unidadMedida.isEmpty()) {
                return error(CodigoErrorConst.UNIDAD_MEDIDA_NO_ENCONTRADA,
                        "Unidad de medida no encontrada: " + request.getCveUnidadMedida());
            }
            entidad.setCveUnidadMedida(unidadMedida.get());
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
        return exito(unidadMedidaTtraMapper.toResponse(unidadMedidaTtraRepository.save(entidad)));
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
