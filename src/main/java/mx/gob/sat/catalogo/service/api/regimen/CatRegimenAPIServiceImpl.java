package mx.gob.sat.catalogo.service.api.regimen;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.regimen.CatRegimenRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.regimen.CatRegimenResponse;
import mx.gob.sat.catalogo.model.entity.CatRegimen;
import mx.gob.sat.catalogo.repository.CatRegimenRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatRegimenMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatRegimenAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de regimenes.</p>
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
class CatRegimenAPIServiceImpl implements CatRegimenAPIService {

    /** Columnas permitidas para ordenamiento. */
    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveRegimen", "cveRegimen",
            "nombre", "nombre"
    );

    /** Columna de ordenamiento predeterminada. */
    private static final String DEFAULT_SORT = "nombre";

    private final CatRegimenRepository regimenRepository;
    private final CatRegimenMapper regimenMapper;

    /**
     * Lista los regimenes de forma paginada con filtro de texto.
     *
     * @param pagina Numero de pagina.
     * @param tamano Tamano de pagina.
     * @param busqueda Texto de busqueda.
     * @param sortBy Columna de ordenamiento.
     * @param sortDir Direccion de ordenamiento.
     * @return Pagina de regimenes en BaseResponse.
     */
    @Override
    public BaseResponse<PaginaResponse<CatRegimenResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatRegimen> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? regimenRepository.findAll(pageable)
                : regimenRepository.findByNombreContainingIgnoreCaseOrCveRegimenContainingIgnoreCase(
                        busqueda, busqueda, pageable);

        final List<CatRegimenResponse> contenido = regimenMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatRegimenResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    /**
     * Busca un regimen por su clave.
     *
     * @param cveRegimen Clave del regimen.
     * @return Regimen en BaseResponse.
     */
    @Override
    public BaseResponse<CatRegimenResponse> findById(final String cveRegimen) {
        final Optional<CatRegimen> resultado = regimenRepository.findById(cveRegimen);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.REGIMEN_NO_ENCONTRADO,
                    "Regimen no encontrado: " + cveRegimen);
        }
        return exito(regimenMapper.toResponse(resultado.get()));
    }

    /**
     * Crea un nuevo regimen.
     *
     * @param request Datos del regimen.
     * @return Regimen creado en BaseResponse.
     */
    @Override
    public BaseResponse<CatRegimenResponse> crear(final CatRegimenRequest request) {
        final CatRegimen guardado = regimenRepository.save(regimenMapper.toEntity(request));
        log.info("Regimen creado: {}", guardado.getCveRegimen());
        return exito(regimenMapper.toResponse(guardado));
    }

    /**
     * Actualiza los campos no nulos de un regimen existente.
     *
     * @param cveRegimen Clave del regimen.
     * @param request Datos de actualizacion.
     * @return Regimen actualizado en BaseResponse.
     */
    @Override
    public BaseResponse<CatRegimenResponse> actualizar(
            final String cveRegimen, final CatRegimenRequest request) {

        final Optional<CatRegimen> opt = regimenRepository.findById(cveRegimen);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.REGIMEN_NO_ENCONTRADO,
                    "Regimen no encontrado: " + cveRegimen);
        }
        final CatRegimen entidad = opt.get();
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
        if (request.getCveEnumeracion() != null) {
            entidad.setCveEnumeracion(request.getCveEnumeracion());
        }
        return exito(regimenMapper.toResponse(regimenRepository.save(entidad)));
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
