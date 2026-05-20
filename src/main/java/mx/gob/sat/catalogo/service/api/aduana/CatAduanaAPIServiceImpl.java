package mx.gob.sat.catalogo.service.api.aduana;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.aduana.CatAduanaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.aduana.CatAduanaResponse;
import mx.gob.sat.catalogo.controller.response.aduana.CatEntidadResponse;
import mx.gob.sat.catalogo.controller.response.aduana.CatTipoAduanaResponse;
import mx.gob.sat.catalogo.model.entity.CatAduana;
import mx.gob.sat.catalogo.model.entity.CatEntidad;
import mx.gob.sat.catalogo.model.entity.CatTipoAduana;
import mx.gob.sat.catalogo.repository.CatAduanaRepository;
import mx.gob.sat.catalogo.repository.CatEntidadRepository;
import mx.gob.sat.catalogo.repository.CatTipoAduanaRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatAduanaMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatAduanaAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de aduanas.
 * Contiene la logica de negocio para consulta y mantenimiento de aduanas,
 * portada desde {@code CatAduanaServiceImpl} del proyecto vucem-catalogos-service.</p>
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
class CatAduanaAPIServiceImpl implements CatAduanaAPIService {

    /** Columnas permitidas para ordenamiento. Previene inyeccion de columnas arbitrarias. */
    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveAduana", "cveAduana",
            "nombre", "nombre",
            "tipoAduana.nombre", "tipoAduana.nombre",
            "entidad.nombre", "entidad.nombre",
            "correoElectronico", "correoElectronico"
    );

    /** Columna de ordenamiento predeterminada. */
    private static final String DEFAULT_SORT_COLUMN = "nombre";

    /****** REPOSITORIOS ******/

    /** Repositorio para operaciones sobre cat_aduana. */
    private final CatAduanaRepository aduanaRepository;

    /** Repositorio para operaciones sobre cat_tipo_aduana. */
    private final CatTipoAduanaRepository tipoAduanaRepository;

    /** Repositorio para operaciones sobre cat_entidad. */
    private final CatEntidadRepository entidadRepository;

    /****** UTILIDADES ******/

    /** Mapper para convertir entidades a DTOs y viceversa. */
    private final CatAduanaMapper aduanaMapper;

    /**
     * Lista las aduanas de forma paginada con soporte de busqueda y ordenamiento.
     *
     * @param pagina Numero de pagina (base 0).
     * @param tamano Cantidad de elementos por pagina.
     * @param busqueda Texto a buscar en nombre o clave.
     * @param sortBy Columna de ordenamiento.
     * @param sortDir Direccion de ordenamiento.
     * @return Pagina de aduanas envuelta en BaseResponse.
     */
    @Override
    public BaseResponse<PaginaResponse<CatAduanaResponse>> listarAduanas(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT_COLUMN));

        final Page<CatAduana> paginaAduanas = (busqueda == null || busqueda.isBlank())
                ? aduanaRepository.findAll(pageable)
                : aduanaRepository.findByNombreContainingIgnoreCaseOrCveAduanaContainingIgnoreCase(
                        busqueda, busqueda, pageable);

        final List<CatAduanaResponse> contenido = aduanaMapper.toResponseList(paginaAduanas.getContent());

        final PaginaResponse<CatAduanaResponse> paginaResponse = PaginaResponse.<CatAduanaResponse>builder()
                .contenido(contenido)
                .pagina(paginaAduanas.getNumber())
                .tamano(paginaAduanas.getSize())
                .total(paginaAduanas.getTotalElements())
                .totalPaginas(paginaAduanas.getTotalPages())
                .ultima(paginaAduanas.isLast())
                .build();

        return exito(paginaResponse);
    }

    /**
     * Crea una nueva aduana validando que no exista duplicado por clave
     * y cargando las relaciones de tipoAduana y entidad desde la base de datos.
     *
     * @param request Datos de la aduana a crear.
     * @return Aduana creada envuelta en BaseResponse.
     */
    @Override
    public BaseResponse<CatAduanaResponse> crearAduana(final CatAduanaRequest request) {
        if (aduanaRepository.existsByCveAduana(request.getCveAduana())) {
            log.warn("Intento de creacion de aduana con clave duplicada: {}", request.getCveAduana());
            return error(CodigoErrorConst.ADUANA_YA_EXISTE,
                    "La clave de aduana ya existe: " + request.getCveAduana());
        }

        final Optional<CatTipoAduana> tipoAduana = tipoAduanaRepository.findById(request.getCveTipoAduana());
        if (tipoAduana.isEmpty()) {
            return error(CodigoErrorConst.TIPO_ADUANA_NO_ENCONTRADO,
                    "TipoAduana no encontrado: " + request.getCveTipoAduana());
        }

        final Optional<CatEntidad> entidad = entidadRepository.findById(request.getCveEntidad());
        if (entidad.isEmpty()) {
            return error(CodigoErrorConst.ENTIDAD_NO_ENCONTRADA,
                    "Entidad no encontrada: " + request.getCveEntidad());
        }

        final CatAduana aduana = aduanaMapper.toEntity(request);
        aduana.setTipoAduana(tipoAduana.get());
        aduana.setEntidad(entidad.get());

        final CatAduana guardada = aduanaRepository.save(aduana);
        log.info("Aduana creada con clave: {}", guardada.getCveAduana());
        return exito(aduanaMapper.toResponse(guardada));
    }

    /**
     * Busca una aduana por su clave.
     *
     * @param cveAduana Clave de la aduana.
     * @return Aduana encontrada o respuesta de error si no existe.
     */
    @Override
    public BaseResponse<CatAduanaResponse> findByCveAduana(final String cveAduana) {
        final Optional<CatAduana> aduana = aduanaRepository.findById(cveAduana);
        if (aduana.isEmpty()) {
            return error(CodigoErrorConst.ADUANA_NO_ENCONTRADA,
                    "Aduana no encontrada con clave: " + cveAduana);
        }
        return exito(aduanaMapper.toResponse(aduana.get()));
    }

    /**
     * Actualiza los campos no nulos de una aduana existente (actualizacion parcial).
     *
     * @param cveAduana Clave de la aduana a actualizar.
     * @param request Datos a actualizar.
     * @return Aduana actualizada o respuesta de error si no existe.
     */
    @Override
    public BaseResponse<CatAduanaResponse> actualizarAduana(
            final String cveAduana, final CatAduanaRequest request) {

        final Optional<CatAduana> optAduana = aduanaRepository.findById(cveAduana);
        if (optAduana.isEmpty()) {
            return error(CodigoErrorConst.ADUANA_NO_ENCONTRADA,
                    "Aduana no encontrada con clave: " + cveAduana);
        }

        final CatAduana aduana = optAduana.get();
        aplicarCambios(aduana, request);

        final CatAduana actualizada = aduanaRepository.save(aduana);
        log.info("Aduana actualizada con clave: {}", actualizada.getCveAduana());
        return exito(aduanaMapper.toResponse(actualizada));
    }

    /**
     * Retorna todos los tipos de aduana disponibles.
     *
     * @return Lista de tipos de aduana envuelta en BaseResponse.
     */
    @Override
    public BaseResponse<List<CatTipoAduanaResponse>> listarTiposAduana() {
        final List<CatTipoAduana> tipos = tipoAduanaRepository.findAll();
        return exito(aduanaMapper.toTipoAduanaResponseList(tipos));
    }

    /**
     * Retorna todas las entidades federativas disponibles.
     *
     * @return Lista de entidades envuelta en BaseResponse.
     */
    @Override
    public BaseResponse<List<CatEntidadResponse>> listarEntidades() {
        final List<CatEntidad> entidades = entidadRepository.findAll();
        return exito(aduanaMapper.toEntidadResponseList(entidades));
    }

    /**
     * Retorna la ultima clave de aduana registrada segun orden descendente.
     *
     * @return Ultima clave de aduana o respuesta de error si no hay registros.
     */
    @Override
    public BaseResponse<String> obtenerUltimaCveAduana() {
        final Optional<CatAduana> ultima = aduanaRepository.findTopByOrderByCveAduanaDesc();
        if (ultima.isEmpty()) {
            return error(CodigoErrorConst.ADUANA_NO_ENCONTRADA, "No existen registros en el catalogo de aduanas");
        }
        return exito(ultima.get().getCveAduana());
    }

    /**
     * Aplica los campos no nulos del request sobre la entidad existente (logica de actualizacion parcial).
     * Preserva la logica original de CatAduanaServiceImpl.updateAduana().
     *
     * @param aduana Entidad JPA a modificar.
     * @param request Datos de actualizacion.
     */
    private void aplicarCambios(final CatAduana aduana, final CatAduanaRequest request) {
        if (request.getNombre() != null) {
            aduana.setNombre(request.getNombre());
        }
        if (request.getFecCaptura() != null) {
            aduana.setFecCaptura(request.getFecCaptura());
        }
        if (request.getFecIniVigencia() != null) {
            aduana.setFecIniVigencia(request.getFecIniVigencia());
        }
        if (request.getFecFinVigencia() != null) {
            aduana.setFecFinVigencia(request.getFecFinVigencia());
        }
        if (request.getBlnActivo() != null) {
            aduana.setBlnActivo(request.getBlnActivo());
        }
        if (request.getCorreoElectronico() != null) {
            aduana.setCorreoElectronico(request.getCorreoElectronico());
        }
        if (request.getCveTipoAduana() != null) {
            final CatTipoAduana tipo = new CatTipoAduana();
            tipo.setCveTipoAduana(request.getCveTipoAduana());
            aduana.setTipoAduana(tipo);
        }
        if (request.getCveEntidad() != null) {
            final CatEntidad entidad = new CatEntidad();
            entidad.setCveEntidad(request.getCveEntidad());
            aduana.setEntidad(entidad);
        }
    }

    /**
     * Construye una respuesta exitosa con el resultado dado.
     *
     * @param resultado Objeto resultado de la operacion.
     * @param <T> Tipo del resultado.
     * @return BaseResponse con codigo de exito y resultado.
     */
    private <T> BaseResponse<T> exito(final T resultado) {
        final BaseResponse<T> response = new BaseResponse<>();
        response.setCodigo(GeneralConst.CODIGO_EXITO);
        response.setMensaje(GeneralConst.MENSAJE_OPERACION_EXITOSA);
        response.setResultado(resultado);
        return response;
    }

    /**
     * Construye una respuesta de error con el codigo y mensaje dados.
     *
     * @param codigo Codigo de error definido en CodigoErrorConst.
     * @param mensaje Descripcion del error.
     * @param <T> Tipo del resultado (null en error).
     * @return BaseResponse con codigo y mensaje de error.
     */
    private <T> BaseResponse<T> error(final String codigo, final String mensaje) {
        final BaseResponse<T> response = new BaseResponse<>();
        response.setCodigo(codigo);
        response.setMensaje(GeneralConst.MENSAJE_OPERACION_FALLIDA);
        response.setError(mensaje);
        return response;
    }
}
