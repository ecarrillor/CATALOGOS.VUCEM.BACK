package mx.gob.sat.catalogo.service.api.genero;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.genero.CatGeneroRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.genero.CatGeneroResponse;
import mx.gob.sat.catalogo.model.entity.CatGenero;
import mx.gob.sat.catalogo.repository.CatGeneroRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatGeneroMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatGeneroAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de generos.</p>
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
class CatGeneroAPIServiceImpl implements CatGeneroAPIService {

    /** Columnas permitidas para ordenamiento. */
    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idGenero", "idGenero",
            "descGenero", "descGenero"
    );

    /** Columna de ordenamiento predeterminada. */
    private static final String DEFAULT_SORT = "descGenero";

    private final CatGeneroRepository generoRepository;
    private final CatGeneroMapper generoMapper;

    /**
     * Lista los generos de forma paginada con filtro de texto.
     *
     * @param pagina Numero de pagina.
     * @param tamano Tamano de pagina.
     * @param busqueda Texto de busqueda.
     * @param sortBy Columna de ordenamiento.
     * @param sortDir Direccion de ordenamiento.
     * @return Pagina de generos en BaseResponse.
     */
    @Override
    public BaseResponse<PaginaResponse<CatGeneroResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatGenero> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? generoRepository.findAll(pageable)
                : generoRepository.findByDescGeneroContainingIgnoreCase(busqueda, pageable);

        final List<CatGeneroResponse> contenido = generoMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatGeneroResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    /**
     * Busca un genero por su identificador.
     *
     * @param idGenero Identificador del genero.
     * @return Genero en BaseResponse.
     */
    @Override
    public BaseResponse<CatGeneroResponse> findById(final Integer idGenero) {
        final Optional<CatGenero> resultado = generoRepository.findById(idGenero);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.GENERO_NO_ENCONTRADO,
                    "Genero no encontrado: " + idGenero);
        }
        return exito(generoMapper.toResponse(resultado.get()));
    }

    /**
     * Crea un nuevo genero.
     *
     * @param request Datos del genero.
     * @return Genero creado en BaseResponse.
     */
    @Override
    public BaseResponse<CatGeneroResponse> crear(final CatGeneroRequest request) {
        final CatGenero guardado = generoRepository.save(generoMapper.toEntity(request));
        log.info("Genero creado: {}", guardado.getIdGenero());
        return exito(generoMapper.toResponse(guardado));
    }

    /**
     * Actualiza los campos no nulos de un genero existente.
     *
     * @param idGenero Identificador del genero.
     * @param request Datos de actualizacion.
     * @return Genero actualizado en BaseResponse.
     */
    @Override
    public BaseResponse<CatGeneroResponse> actualizar(final Integer idGenero, final CatGeneroRequest request) {
        final Optional<CatGenero> opt = generoRepository.findById(idGenero);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.GENERO_NO_ENCONTRADO,
                    "Genero no encontrado: " + idGenero);
        }
        final CatGenero entidad = opt.get();
        if (request.getDescGenero() != null) {
            entidad.setDescGenero(request.getDescGenero());
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
        return exito(generoMapper.toResponse(generoRepository.save(entidad)));
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
