package mx.gob.sat.catalogo.service.api.dependencia;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.dependencia.CatDependenciaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.SelectResponse;
import mx.gob.sat.catalogo.controller.response.dependencia.CatDependenciaResponse;
import mx.gob.sat.catalogo.model.entity.CatCalendario;
import mx.gob.sat.catalogo.model.entity.CatDependencia;
import mx.gob.sat.catalogo.repository.CatCalendarioRepository;
import mx.gob.sat.catalogo.repository.CatDependenciaRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatDependenciaMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatDependenciaAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de dependencias.</p>
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
class CatDependenciaAPIServiceImpl implements CatDependenciaAPIService {

    /** Columnas permitidas para ordenamiento. */
    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveDependencia", "id",
            "nombreDependencia", "nombre",
            "acronimo", "acronimo"
    );

    /** Columna de ordenamiento predeterminada. */
    private static final String DEFAULT_SORT = "nombre";

    private final CatDependenciaRepository dependenciaRepository;
    private final CatCalendarioRepository calendarioRepository;
    private final CatDependenciaMapper dependenciaMapper;

    /**
     * Lista las dependencias paginadas con filtro de texto y estado activo.
     *
     * @param pagina Numero de pagina.
     * @param tamano Tamano de pagina.
     * @param busqueda Texto de busqueda o "activo"/"inactivo".
     * @param sortBy Columna de ordenamiento.
     * @param sortDir Direccion de ordenamiento.
     * @return Pagina de dependencias en BaseResponse.
     */
    @Override
    public BaseResponse<PaginaResponse<CatDependenciaResponse>> listar(
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

        final Page<CatDependencia> paginaResultado =
                dependenciaRepository.search(texto, activo, pageable);
        final List<CatDependenciaResponse> contenido =
                dependenciaMapper.toResponseList(paginaResultado.getContent());

        return exito(PaginaResponse.<CatDependenciaResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    /**
     * Busca una dependencia por su identificador.
     *
     * @param id Identificador de la dependencia.
     * @return Dependencia en BaseResponse.
     */
    @Override
    public BaseResponse<CatDependenciaResponse> findById(final Long id) {
        final Optional<CatDependencia> resultado = dependenciaRepository.findById(id);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.DEPENDENCIA_NO_ENCONTRADA,
                    "Dependencia no encontrada: " + id);
        }
        return exito(dependenciaMapper.toResponse(resultado.get()));
    }

    /**
     * Crea una nueva dependencia.
     *
     * @param request Datos de la dependencia.
     * @return Dependencia creada en BaseResponse.
     */
    @Override
    public BaseResponse<CatDependenciaResponse> crear(final CatDependenciaRequest request) {
        final CatDependencia entidad = new CatDependencia();
        entidad.setId(request.getCveDependencia());
        entidad.setNombre(request.getNombreDependencia());
        entidad.setAcronimo(request.getAcronimo());
        entidad.setBlnTramitesVu(request.getTramiteVU());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());

        if (request.getCveCalendario() != null) {
            final Optional<CatCalendario> calendario =
                    calendarioRepository.findById(request.getCveCalendario());
            if (calendario.isEmpty()) {
                return error(CodigoErrorConst.CALENDARIO_NO_ENCONTRADO,
                        "Calendario no encontrado: " + request.getCveCalendario());
            }
            entidad.setCveCalendario(calendario.get());
        }

        final CatDependencia guardada = dependenciaRepository.save(entidad);
        log.info("Dependencia creada: {}", guardada.getId());
        return exito(dependenciaMapper.toResponse(guardada));
    }

    /**
     * Actualiza los campos de una dependencia existente.
     *
     * @param id Identificador de la dependencia.
     * @param request Datos de actualizacion.
     * @return Dependencia actualizada en BaseResponse.
     */
    @Override
    public BaseResponse<CatDependenciaResponse> actualizar(
            final Long id, final CatDependenciaRequest request) {

        final Optional<CatDependencia> opt = dependenciaRepository.findById(id);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.DEPENDENCIA_NO_ENCONTRADA,
                    "Dependencia no encontrada: " + id);
        }
        final CatDependencia entidad = opt.get();
        if (request.getNombreDependencia() != null) {
            entidad.setNombre(request.getNombreDependencia());
        }
        if (request.getAcronimo() != null) {
            entidad.setAcronimo(request.getAcronimo());
        }
        if (request.getTramiteVU() != null) {
            entidad.setBlnTramitesVu(request.getTramiteVU());
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
        if (request.getCveCalendario() != null) {
            final Optional<CatCalendario> calendario =
                    calendarioRepository.findById(request.getCveCalendario());
            if (calendario.isEmpty()) {
                return error(CodigoErrorConst.CALENDARIO_NO_ENCONTRADO,
                        "Calendario no encontrado: " + request.getCveCalendario());
            }
            entidad.setCveCalendario(calendario.get());
        }
        return exito(dependenciaMapper.toResponse(dependenciaRepository.save(entidad)));
    }

    /**
     * Retorna lista de calendarios en formato clave-nombre para dropdowns.
     *
     * @return Lista de seleccion de calendarios en BaseResponse.
     */
    @Override
    public BaseResponse<List<SelectResponse>> listarCalendarios() {
        final List<SelectResponse> lista = calendarioRepository.findAll().stream()
                .map(c -> SelectResponse.builder()
                        .cve(c.getCveCalendario())
                        .nombre(c.getNombre())
                        .build())
                .toList();
        return exito(lista);
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
