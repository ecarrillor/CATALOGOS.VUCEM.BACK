package mx.gob.sat.catalogo.service.api.tipoaduana;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.tipoaduana.CatTipoAduanaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.aduana.CatTipoAduanaResponse;
import mx.gob.sat.catalogo.model.entity.CatTipoAduana;
import mx.gob.sat.catalogo.repository.CatTipoAduanaRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatTipoAduanaMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatTipoAduanaAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de tipos de aduana.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Implementacion del servicio API
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatTipoAduanaAPIServiceImpl implements CatTipoAduanaAPIService {

    /** Columnas permitidas para ordenamiento. */
    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveTipoAduana", "cveTipoAduana",
            "nombre", "nombre"
    );

    /** Columna de ordenamiento predeterminada. */
    private static final String DEFAULT_SORT = "nombre";

    private final CatTipoAduanaRepository tipoAduanaRepository;
    private final CatTipoAduanaMapper tipoAduanaMapper;

    /**
     * Lista los tipos de aduana de forma paginada.
     *
     * @param pagina Numero de pagina (base 0).
     * @param tamano Cantidad de elementos por pagina.
     * @param busqueda Texto de busqueda opcional.
     * @param sortBy Columna de ordenamiento.
     * @param sortDir Direccion de ordenamiento.
     * @return Pagina de tipos de aduana en BaseResponse.
     */
    @Override
    public BaseResponse<PaginaResponse<CatTipoAduanaResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatTipoAduana> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? tipoAduanaRepository.findAll(pageable)
                : tipoAduanaRepository.findByNombreContainingIgnoreCaseOrCveTipoAduanaContainingIgnoreCase(
                        busqueda, busqueda, pageable);

        final List<CatTipoAduanaResponse> contenido = tipoAduanaMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatTipoAduanaResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    /**
     * Crea un nuevo tipo de aduana.
     *
     * @param request Datos del tipo de aduana.
     * @return Tipo de aduana creado en BaseResponse.
     */
    @Override
    public BaseResponse<CatTipoAduanaResponse> crear(final CatTipoAduanaRequest request) {
        final CatTipoAduana guardado = tipoAduanaRepository.save(tipoAduanaMapper.toEntity(request));
        log.info("TipoAduana creado: {}", guardado.getCveTipoAduana());
        return exito(tipoAduanaMapper.toResponse(guardado));
    }

    /**
     * Busca un tipo de aduana por su clave.
     *
     * @param cveTipoAduana Clave del tipo de aduana.
     * @return Tipo de aduana en BaseResponse.
     */
    @Override
    public BaseResponse<CatTipoAduanaResponse> findById(final String cveTipoAduana) {
        final Optional<CatTipoAduana> resultado = tipoAduanaRepository.findById(cveTipoAduana);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.TIPO_ADUANA_NO_ENCONTRADO,
                    "TipoAduana no encontrado: " + cveTipoAduana);
        }
        return exito(tipoAduanaMapper.toResponse(resultado.get()));
    }

    /**
     * Actualiza los campos no nulos de un tipo de aduana existente.
     *
     * @param cveTipoAduana Clave del tipo de aduana.
     * @param request Datos de actualizacion.
     * @return Tipo de aduana actualizado en BaseResponse.
     */
    @Override
    public BaseResponse<CatTipoAduanaResponse> actualizar(
            final String cveTipoAduana, final CatTipoAduanaRequest request) {

        final Optional<CatTipoAduana> opt = tipoAduanaRepository.findById(cveTipoAduana);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.TIPO_ADUANA_NO_ENCONTRADO,
                    "TipoAduana no encontrado: " + cveTipoAduana);
        }
        final CatTipoAduana entidad = opt.get();
        if (request.getNombre() != null) {
            entidad.setNombre(request.getNombre());
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
        return exito(tipoAduanaMapper.toResponse(tipoAduanaRepository.save(entidad)));
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
