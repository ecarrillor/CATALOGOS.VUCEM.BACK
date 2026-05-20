package mx.gob.sat.catalogo.service.api.identificadorprevalidador;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.identificadorprevalidador.CatIdentificadorPrevalidadorRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.identificadorprevalidador.CatIdentificadorPrevalidadorResponse;
import mx.gob.sat.catalogo.model.entity.CatIdentificadorPrevalidador;
import mx.gob.sat.catalogo.repository.CatIdentificadorPrevalidadorRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatIdentificadorPrevalidadorMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatIdentificadorPrevalidadorAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de identificadores prevalidadores.</p>
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
class CatIdentificadorPrevalidadorAPIServiceImpl implements CatIdentificadorPrevalidadorAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idIdentificadorPrevalidador", "idIdentificadorPrevalidador",
            "caracterIdentificacion", "caracterIdentificacion");

    private static final String DEFAULT_SORT = "idIdentificadorPrevalidador";

    private final CatIdentificadorPrevalidadorRepository identificadorPrevalidadorRepository;
    private final CatIdentificadorPrevalidadorMapper identificadorPrevalidadorMapper;

    @Override
    public BaseResponse<PaginaResponse<CatIdentificadorPrevalidadorResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatIdentificadorPrevalidador> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? identificadorPrevalidadorRepository.findAll(pageable)
                : identificadorPrevalidadorRepository.findByCaracterIdentificacionContainingIgnoreCase(
                        busqueda, pageable);

        final List<CatIdentificadorPrevalidadorResponse> contenido =
                identificadorPrevalidadorMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatIdentificadorPrevalidadorResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatIdentificadorPrevalidadorResponse> findById(final Long idIdentificadorPrevalidador) {
        final Optional<CatIdentificadorPrevalidador> resultado =
                identificadorPrevalidadorRepository.findById(idIdentificadorPrevalidador);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.IDENTIFICADOR_PREVALIDADOR_NO_ENCONTRADO,
                    "Identificador prevalidador no encontrado: " + idIdentificadorPrevalidador);
        }
        return exito(identificadorPrevalidadorMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatIdentificadorPrevalidadorResponse> crear(
            final CatIdentificadorPrevalidadorRequest request) {

        final CatIdentificadorPrevalidador guardado =
                identificadorPrevalidadorRepository.save(identificadorPrevalidadorMapper.toEntity(request));
        log.info("Identificador prevalidador creado: {}", guardado.getIdIdentificadorPrevalidador());
        return exito(identificadorPrevalidadorMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatIdentificadorPrevalidadorResponse> actualizar(
            final Long idIdentificadorPrevalidador, final CatIdentificadorPrevalidadorRequest request) {

        final Optional<CatIdentificadorPrevalidador> opt =
                identificadorPrevalidadorRepository.findById(idIdentificadorPrevalidador);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.IDENTIFICADOR_PREVALIDADOR_NO_ENCONTRADO,
                    "Identificador prevalidador no encontrado: " + idIdentificadorPrevalidador);
        }
        final CatIdentificadorPrevalidador entidad = opt.get();
        if (request.getCaracterIdentificacion() != null) {
            entidad.setCaracterIdentificacion(request.getCaracterIdentificacion());
        }
        if (request.getFecIniVigencia() != null) {
            entidad.setFecIniVigencia(request.getFecIniVigencia());
        }
        if (request.getFecFinVigencia() != null) {
            entidad.setFecFinVigencia(request.getFecFinVigencia());
        }
        if (request.getBlnUtilizado() != null) {
            entidad.setBlnUtilizado(request.getBlnUtilizado());
        }
        if (request.getBlnActivo() != null) {
            entidad.setBlnActivo(request.getBlnActivo());
        }
        return exito(identificadorPrevalidadorMapper.toResponse(
                identificadorPrevalidadorRepository.save(entidad)));
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
