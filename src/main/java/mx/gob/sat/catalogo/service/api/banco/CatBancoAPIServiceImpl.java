package mx.gob.sat.catalogo.service.api.banco;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.banco.CatBancoRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.banco.CatBancoResponse;
import mx.gob.sat.catalogo.model.entity.CatBanco;
import mx.gob.sat.catalogo.repository.CatBancoRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatBancoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatBancoAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de bancos.
 * El listado siempre filtra por bancos activos (blnActivo = true).</p>
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
class CatBancoAPIServiceImpl implements CatBancoAPIService {

    /** Columnas permitidas para ordenamiento. */
    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveBanco", "cveBanco",
            "nombre", "nombre"
    );

    /** Columna de ordenamiento predeterminada. */
    private static final String DEFAULT_SORT = "cveBanco";

    private final CatBancoRepository bancoRepository;
    private final CatBancoMapper bancoMapper;

    /**
     * Lista los bancos activos de forma paginada con filtro de texto.
     * Preserva la logica original: solo muestra bancos con blnActivo = true.
     *
     * @param pagina Numero de pagina.
     * @param tamano Tamano de pagina.
     * @param busqueda Texto de busqueda.
     * @param sortBy Columna de ordenamiento.
     * @param sortDir Direccion de ordenamiento.
     * @return Pagina de bancos activos en BaseResponse.
     */
    @Override
    public BaseResponse<PaginaResponse<CatBancoResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatBanco> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? bancoRepository.findByBlnActivoTrue(pageable)
                : bancoRepository
                        .findByBlnActivoTrueAndNombreContainingIgnoreCaseOrBlnActivoTrueAndCveBancoContainingIgnoreCase(
                                busqueda, busqueda, pageable);

        final List<CatBancoResponse> contenido = bancoMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatBancoResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    /**
     * Busca un banco por su clave.
     *
     * @param cveBanco Clave del banco.
     * @return Banco en BaseResponse.
     */
    @Override
    public BaseResponse<CatBancoResponse> findById(final String cveBanco) {
        final Optional<CatBanco> resultado = bancoRepository.findById(cveBanco);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.BANCO_NO_ENCONTRADO,
                    "Banco no encontrado: " + cveBanco);
        }
        return exito(bancoMapper.toResponse(resultado.get()));
    }

    /**
     * Crea un nuevo banco.
     *
     * @param request Datos del banco.
     * @return Banco creado en BaseResponse.
     */
    @Override
    public BaseResponse<CatBancoResponse> crear(final CatBancoRequest request) {
        final CatBanco guardado = bancoRepository.save(bancoMapper.toEntity(request));
        log.info("Banco creado: {}", guardado.getCveBanco());
        return exito(bancoMapper.toResponse(guardado));
    }

    /**
     * Actualiza los campos no nulos de un banco existente.
     *
     * @param cveBanco Clave del banco.
     * @param request Datos de actualizacion.
     * @return Banco actualizado en BaseResponse.
     */
    @Override
    public BaseResponse<CatBancoResponse> actualizar(final String cveBanco, final CatBancoRequest request) {
        final Optional<CatBanco> opt = bancoRepository.findById(cveBanco);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.BANCO_NO_ENCONTRADO,
                    "Banco no encontrado: " + cveBanco);
        }
        final CatBanco entidad = opt.get();
        if (request.getNombre() != null) {
            entidad.setNombre(request.getNombre());
        }
        if (request.getFecCaptura() != null) {
            entidad.setFecCaptura(request.getFecCaptura());
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
        if (request.getBlnE5() != null) {
            entidad.setBlnE5(request.getBlnE5());
        }
        return exito(bancoMapper.toResponse(bancoRepository.save(entidad)));
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
