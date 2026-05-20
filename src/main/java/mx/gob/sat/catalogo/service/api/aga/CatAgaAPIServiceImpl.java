package mx.gob.sat.catalogo.service.api.aga;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.aga.CatAgaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.aga.CatAgaResponse;
import mx.gob.sat.catalogo.model.entity.CatAga;
import mx.gob.sat.catalogo.repository.CatAgaRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatAgaMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatAgaAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de AGA.</p>
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
class CatAgaAPIServiceImpl implements CatAgaAPIService {

    /** Columnas permitidas para ordenamiento. */
    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveParametro", "cveParametro",
            "descripcion", "descripcion"
    );

    /** Columna de ordenamiento predeterminada. */
    private static final String DEFAULT_SORT = "cveParametro";

    private final CatAgaRepository agaRepository;
    private final CatAgaMapper agaMapper;

    /**
     * Lista los registros AGA de forma paginada con filtro de texto.
     *
     * @param pagina Numero de pagina.
     * @param tamano Tamano de pagina.
     * @param busqueda Texto de busqueda.
     * @param sortBy Columna de ordenamiento.
     * @param sortDir Direccion de ordenamiento.
     * @return Pagina de registros AGA en BaseResponse.
     */
    @Override
    public BaseResponse<PaginaResponse<CatAgaResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatAga> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? agaRepository.findAll(pageable)
                : agaRepository.findByDescripcionContainingIgnoreCaseOrCveParametroContainingIgnoreCase(
                        busqueda, busqueda, pageable);

        final List<CatAgaResponse> contenido = agaMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatAgaResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    /**
     * Busca un registro AGA por su clave.
     *
     * @param cveParametro Clave del parametro AGA.
     * @return Registro AGA en BaseResponse.
     */
    @Override
    public BaseResponse<CatAgaResponse> findById(final String cveParametro) {
        final Optional<CatAga> resultado = agaRepository.findById(cveParametro);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.AGA_NO_ENCONTRADO,
                    "AGA no encontrado: " + cveParametro);
        }
        return exito(agaMapper.toResponse(resultado.get()));
    }

    /**
     * Crea un nuevo registro AGA.
     *
     * @param request Datos del registro AGA.
     * @return Registro AGA creado en BaseResponse.
     */
    @Override
    public BaseResponse<CatAgaResponse> crear(final CatAgaRequest request) {
        final CatAga guardado = agaRepository.save(agaMapper.toEntity(request));
        log.info("AGA creado: {}", guardado.getCveParametro());
        return exito(agaMapper.toResponse(guardado));
    }

    /**
     * Actualiza los campos no nulos de un registro AGA existente.
     *
     * @param cveParametro Clave del parametro AGA.
     * @param request Datos de actualizacion.
     * @return Registro AGA actualizado en BaseResponse.
     */
    @Override
    public BaseResponse<CatAgaResponse> actualizar(
            final String cveParametro, final CatAgaRequest request) {

        final Optional<CatAga> opt = agaRepository.findById(cveParametro);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.AGA_NO_ENCONTRADO,
                    "AGA no encontrado: " + cveParametro);
        }
        final CatAga entidad = opt.get();
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
        return exito(agaMapper.toResponse(agaRepository.save(entidad)));
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
