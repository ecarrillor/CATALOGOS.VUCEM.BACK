package mx.gob.sat.catalogo.service.api.parametro;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.parametro.CatParametroRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.SelectResponse;
import mx.gob.sat.catalogo.controller.response.parametro.CatParametroResponse;
import mx.gob.sat.catalogo.model.entity.CatDependencia;
import mx.gob.sat.catalogo.model.entity.CatParametro;
import mx.gob.sat.catalogo.repository.CatDependenciaRepository;
import mx.gob.sat.catalogo.repository.CatParametroRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatParametroMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatParametroAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de parametros del sistema.</p>
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
class CatParametroAPIServiceImpl implements CatParametroAPIService {

    /** Columnas permitidas para ordenamiento. */
    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveParametro", "cveParametro",
            "descripcion", "descripcion",
            "valor", "valor"
    );

    /** Columna de ordenamiento predeterminada. */
    private static final String DEFAULT_SORT = "cveParametro";

    private final CatParametroRepository parametroRepository;
    private final CatDependenciaRepository dependenciaRepository;
    private final CatParametroMapper parametroMapper;

    /**
     * Lista los parametros paginados con filtro de texto y estado activo.
     *
     * @param pagina Numero de pagina.
     * @param tamano Tamano de pagina.
     * @param busqueda Texto de busqueda o "activo"/"inactivo".
     * @param sortBy Columna de ordenamiento.
     * @param sortDir Direccion de ordenamiento.
     * @return Pagina de parametros en BaseResponse.
     */
    @Override
    public BaseResponse<PaginaResponse<CatParametroResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        Boolean activo = null;
        String texto = null;

        if (busqueda != null && !busqueda.isBlank()) {
            final String termino = busqueda.trim().toLowerCase();
            if ("activo".equals(termino)) {
                activo = Boolean.TRUE;
            } else if ("inactivo".equals(termino)) {
                activo = Boolean.FALSE;
            } else {
                texto = "%" + termino + "%";
            }
        }

        final Page<CatParametro> paginaResultado =
                parametroRepository.search(texto, activo, pageable);
        final List<CatParametroResponse> contenido =
                parametroMapper.toResponseList(paginaResultado.getContent());

        return exito(PaginaResponse.<CatParametroResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    /**
     * Busca un parametro por su clave.
     *
     * @param cveParametro Clave del parametro.
     * @return Parametro en BaseResponse.
     */
    @Override
    public BaseResponse<CatParametroResponse> findById(final String cveParametro) {
        final Optional<CatParametro> resultado = parametroRepository.findById(cveParametro);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.PARAMETRO_NO_ENCONTRADO,
                    "Parametro no encontrado: " + cveParametro);
        }
        return exito(parametroMapper.toResponse(resultado.get()));
    }

    /**
     * Crea un nuevo parametro.
     *
     * @param request Datos del parametro.
     * @return Parametro creado en BaseResponse.
     */
    @Override
    public BaseResponse<CatParametroResponse> crear(final CatParametroRequest request) {
        final CatParametro entidad = new CatParametro();
        entidad.setCveParametro(request.getCveParametro());
        entidad.setDescripcion(request.getDescripcion());
        entidad.setValor(request.getValor());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());

        if (request.getIdDependencia() != null) {
            final Optional<CatDependencia> dependencia =
                    dependenciaRepository.findById(request.getIdDependencia());
            if (dependencia.isEmpty()) {
                return error(CodigoErrorConst.DEPENDENCIA_NO_ENCONTRADA,
                        "Dependencia no encontrada: " + request.getIdDependencia());
            }
            entidad.setIdDependencia(dependencia.get());
        }

        final CatParametro guardado = parametroRepository.save(entidad);
        log.info("Parametro creado: {}", guardado.getCveParametro());
        return exito(parametroMapper.toResponse(guardado));
    }

    /**
     * Actualiza los campos de un parametro existente.
     *
     * @param cveParametro Clave del parametro.
     * @param request Datos de actualizacion.
     * @return Parametro actualizado en BaseResponse.
     */
    @Override
    public BaseResponse<CatParametroResponse> actualizar(
            final String cveParametro, final CatParametroRequest request) {

        final Optional<CatParametro> opt = parametroRepository.findById(cveParametro);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.PARAMETRO_NO_ENCONTRADO,
                    "Parametro no encontrado: " + cveParametro);
        }
        final CatParametro entidad = opt.get();
        if (request.getDescripcion() != null) {
            entidad.setDescripcion(request.getDescripcion());
        }
        if (request.getValor() != null) {
            entidad.setValor(request.getValor());
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
        if (request.getIdDependencia() != null) {
            final Optional<CatDependencia> dependencia =
                    dependenciaRepository.findById(request.getIdDependencia());
            if (dependencia.isEmpty()) {
                return error(CodigoErrorConst.DEPENDENCIA_NO_ENCONTRADA,
                        "Dependencia no encontrada: " + request.getIdDependencia());
            }
            entidad.setIdDependencia(dependencia.get());
        }
        return exito(parametroMapper.toResponse(parametroRepository.save(entidad)));
    }

    /**
     * Retorna lista de dependencias activas en formato id-nombre para dropdowns.
     *
     * @return Lista de seleccion de dependencias en BaseResponse.
     */
    @Override
    public BaseResponse<List<SelectResponse>> listarDependencias() {
        final List<SelectResponse> lista = dependenciaRepository.findByBlnActivoTrue().stream()
                .filter(d -> d.getNombre() != null && !d.getNombre().isBlank())
                .map(d -> SelectResponse.builder()
                        .cve(String.valueOf(d.getId()))
                        .nombre(d.getNombre())
                        .build())
                .toList();
        return exito(lista);
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
