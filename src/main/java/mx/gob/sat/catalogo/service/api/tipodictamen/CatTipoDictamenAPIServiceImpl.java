package mx.gob.sat.catalogo.service.api.tipodictamen;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.tipodictamen.CatTipoDictamenRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tipodictamen.CatTipoDictamenResponse;
import mx.gob.sat.catalogo.model.entity.CatTipoDictamen;
import mx.gob.sat.catalogo.repository.CatTipoDictamenRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatTipoDictamenMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatTipoDictamenAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de tipos de dictamen.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Implementacion del servicio API
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatTipoDictamenAPIServiceImpl implements CatTipoDictamenAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idTipoDictamen", "idTipoDictamen",
            "nombre", "nombre",
            "orden", "orden");

    private static final String DEFAULT_SORT = "orden";

    private final CatTipoDictamenRepository tipoDictamenRepository;
    private final CatTipoDictamenMapper tipoDictamenMapper;

    @Override
    public BaseResponse<PaginaResponse<CatTipoDictamenResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatTipoDictamen> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? tipoDictamenRepository.findAll(pageable)
                : tipoDictamenRepository.findByNombreContainingIgnoreCase(busqueda, pageable);

        final List<CatTipoDictamenResponse> contenido =
                tipoDictamenMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatTipoDictamenResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatTipoDictamenResponse> findById(final Long idTipoDictamen) {
        final Optional<CatTipoDictamen> resultado = tipoDictamenRepository.findById(idTipoDictamen);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.TIPO_DICTAMEN_NO_ENCONTRADO,
                    "Tipo dictamen no encontrado: " + idTipoDictamen);
        }
        return exito(tipoDictamenMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatTipoDictamenResponse> crear(final CatTipoDictamenRequest request) {
        final CatTipoDictamen guardado = tipoDictamenRepository.save(tipoDictamenMapper.toEntity(request));
        log.info("Tipo dictamen creado: {}", guardado.getIdTipoDictamen());
        return exito(tipoDictamenMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatTipoDictamenResponse> actualizar(
            final Long idTipoDictamen, final CatTipoDictamenRequest request) {

        final Optional<CatTipoDictamen> opt = tipoDictamenRepository.findById(idTipoDictamen);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.TIPO_DICTAMEN_NO_ENCONTRADO,
                    "Tipo dictamen no encontrado: " + idTipoDictamen);
        }
        final CatTipoDictamen entidad = opt.get();
        if (request.getNombre() != null) {
            entidad.setNombre(request.getNombre());
        }
        if (request.getOrden() != null) {
            entidad.setOrden(request.getOrden());
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
        return exito(tipoDictamenMapper.toResponse(tipoDictamenRepository.save(entidad)));
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
