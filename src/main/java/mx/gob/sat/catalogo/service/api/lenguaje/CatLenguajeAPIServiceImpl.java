package mx.gob.sat.catalogo.service.api.lenguaje;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.lenguaje.CatLenguajeRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.lenguaje.CatLenguajeResponse;
import mx.gob.sat.catalogo.model.entity.CatLenguaje;
import mx.gob.sat.catalogo.repository.CatLenguajeRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatLenguajeMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatLenguajeAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de lenguajes.</p>
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
class CatLenguajeAPIServiceImpl implements CatLenguajeAPIService {

    /** Columnas permitidas para ordenamiento. */
    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveLenguaje", "cveLenguaje",
            "nombre", "nombre"
    );

    /** Columna de ordenamiento predeterminada. */
    private static final String DEFAULT_SORT = "cveLenguaje";

    private final CatLenguajeRepository lenguajeRepository;
    private final CatLenguajeMapper lenguajeMapper;

    /**
     * Lista los lenguajes de forma paginada con filtro de texto.
     *
     * @param pagina Numero de pagina.
     * @param tamano Tamano de pagina.
     * @param busqueda Texto de busqueda.
     * @param sortBy Columna de ordenamiento.
     * @param sortDir Direccion de ordenamiento.
     * @return Pagina de lenguajes en BaseResponse.
     */
    @Override
    public BaseResponse<PaginaResponse<CatLenguajeResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatLenguaje> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? lenguajeRepository.findAll(pageable)
                : lenguajeRepository.findByNombreContainingIgnoreCaseOrCveLenguajeContainingIgnoreCase(
                        busqueda, busqueda, pageable);

        final List<CatLenguajeResponse> contenido = lenguajeMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatLenguajeResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    /**
     * Busca un lenguaje por su clave.
     *
     * @param cveLenguaje Clave del lenguaje.
     * @return Lenguaje en BaseResponse.
     */
    @Override
    public BaseResponse<CatLenguajeResponse> findById(final String cveLenguaje) {
        final Optional<CatLenguaje> resultado = lenguajeRepository.findById(cveLenguaje);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.LENGUAJE_NO_ENCONTRADO,
                    "Lenguaje no encontrado: " + cveLenguaje);
        }
        return exito(lenguajeMapper.toResponse(resultado.get()));
    }

    /**
     * Crea un nuevo lenguaje.
     *
     * @param request Datos del lenguaje.
     * @return Lenguaje creado en BaseResponse.
     */
    @Override
    public BaseResponse<CatLenguajeResponse> crear(final CatLenguajeRequest request) {
        final CatLenguaje guardado = lenguajeRepository.save(lenguajeMapper.toEntity(request));
        log.info("Lenguaje creado: {}", guardado.getCveLenguaje());
        return exito(lenguajeMapper.toResponse(guardado));
    }

    /**
     * Actualiza los campos no nulos de un lenguaje existente.
     *
     * @param cveLenguaje Clave del lenguaje.
     * @param request Datos de actualizacion.
     * @return Lenguaje actualizado en BaseResponse.
     */
    @Override
    public BaseResponse<CatLenguajeResponse> actualizar(
            final String cveLenguaje, final CatLenguajeRequest request) {

        final Optional<CatLenguaje> opt = lenguajeRepository.findById(cveLenguaje);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.LENGUAJE_NO_ENCONTRADO,
                    "Lenguaje no encontrado: " + cveLenguaje);
        }
        final CatLenguaje entidad = opt.get();
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
        return exito(lenguajeMapper.toResponse(lenguajeRepository.save(entidad)));
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
