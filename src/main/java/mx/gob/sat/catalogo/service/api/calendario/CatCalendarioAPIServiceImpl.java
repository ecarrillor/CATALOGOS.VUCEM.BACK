package mx.gob.sat.catalogo.service.api.calendario;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.calendario.CatCalendarioRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.calendario.CatCalendarioResponse;
import mx.gob.sat.catalogo.model.entity.CatCalendario;
import mx.gob.sat.catalogo.repository.CatCalendarioRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatCalendarioMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatCalendarioAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de calendarios.</p>
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
class CatCalendarioAPIServiceImpl implements CatCalendarioAPIService {

    /** Columnas permitidas para ordenamiento. */
    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveCalendario", "cveCalendario",
            "nombre", "nombre"
    );

    /** Columna de ordenamiento predeterminada. */
    private static final String DEFAULT_SORT = "cveCalendario";

    private final CatCalendarioRepository calendarioRepository;
    private final CatCalendarioMapper calendarioMapper;

    /**
     * Lista los calendarios de forma paginada con filtro de texto.
     *
     * @param pagina Numero de pagina.
     * @param tamano Tamano de pagina.
     * @param busqueda Texto de busqueda.
     * @param sortBy Columna de ordenamiento.
     * @param sortDir Direccion de ordenamiento.
     * @return Pagina de calendarios en BaseResponse.
     */
    @Override
    public BaseResponse<PaginaResponse<CatCalendarioResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatCalendario> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? calendarioRepository.findAll(pageable)
                : calendarioRepository.findByNombreContainingIgnoreCaseOrCveCalendarioContainingIgnoreCase(
                        busqueda, busqueda, pageable);

        final List<CatCalendarioResponse> contenido = calendarioMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatCalendarioResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    /**
     * Busca un calendario por su clave.
     *
     * @param cveCalendario Clave del calendario.
     * @return Calendario en BaseResponse.
     */
    @Override
    public BaseResponse<CatCalendarioResponse> findById(final String cveCalendario) {
        final Optional<CatCalendario> resultado = calendarioRepository.findById(cveCalendario);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.CALENDARIO_NO_ENCONTRADO,
                    "Calendario no encontrado: " + cveCalendario);
        }
        return exito(calendarioMapper.toResponse(resultado.get()));
    }

    /**
     * Crea un nuevo calendario.
     *
     * @param request Datos del calendario.
     * @return Calendario creado en BaseResponse.
     */
    @Override
    public BaseResponse<CatCalendarioResponse> crear(final CatCalendarioRequest request) {
        final CatCalendario guardado = calendarioRepository.save(calendarioMapper.toEntity(request));
        log.info("Calendario creado: {}", guardado.getCveCalendario());
        return exito(calendarioMapper.toResponse(guardado));
    }

    /**
     * Actualiza los campos no nulos de un calendario existente.
     *
     * @param cveCalendario Clave del calendario.
     * @param request Datos de actualizacion.
     * @return Calendario actualizado en BaseResponse.
     */
    @Override
    public BaseResponse<CatCalendarioResponse> actualizar(
            final String cveCalendario, final CatCalendarioRequest request) {

        final Optional<CatCalendario> opt = calendarioRepository.findById(cveCalendario);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.CALENDARIO_NO_ENCONTRADO,
                    "Calendario no encontrado: " + cveCalendario);
        }
        final CatCalendario entidad = opt.get();
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
        return exito(calendarioMapper.toResponse(calendarioRepository.save(entidad)));
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
