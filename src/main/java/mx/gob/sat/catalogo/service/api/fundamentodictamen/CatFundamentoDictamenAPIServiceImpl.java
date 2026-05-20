package mx.gob.sat.catalogo.service.api.fundamentodictamen;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.fundamentodictamen.CatFundamentoDictamenRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.fundamentodictamen.CatFundamentoDictamenResponse;
import mx.gob.sat.catalogo.model.entity.CatFundamentoDictamen;
import mx.gob.sat.catalogo.repository.CatFundamentoDictamenRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatFundamentoDictamenMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatFundamentoDictamenAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de fundamentos de dictamen.</p>
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
class CatFundamentoDictamenAPIServiceImpl implements CatFundamentoDictamenAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idFundamentoDictamen", "idFundamentoDictamen",
            "descripcion", "descripcion");

    private static final String DEFAULT_SORT = "idFundamentoDictamen";

    private final CatFundamentoDictamenRepository fundamentoDictamenRepository;
    private final CatFundamentoDictamenMapper fundamentoDictamenMapper;

    @Override
    public BaseResponse<PaginaResponse<CatFundamentoDictamenResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatFundamentoDictamen> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? fundamentoDictamenRepository.findAll(pageable)
                : fundamentoDictamenRepository.findByDescripcionContainingIgnoreCase(busqueda, pageable);

        final List<CatFundamentoDictamenResponse> contenido =
                fundamentoDictamenMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatFundamentoDictamenResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatFundamentoDictamenResponse> findById(final Long idFundamentoDictamen) {
        final Optional<CatFundamentoDictamen> resultado =
                fundamentoDictamenRepository.findById(idFundamentoDictamen);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.FUNDAMENTO_DICTAMEN_NO_ENCONTRADO,
                    "Fundamento dictamen no encontrado: " + idFundamentoDictamen);
        }
        return exito(fundamentoDictamenMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatFundamentoDictamenResponse> crear(final CatFundamentoDictamenRequest request) {
        final CatFundamentoDictamen guardado =
                fundamentoDictamenRepository.save(fundamentoDictamenMapper.toEntity(request));
        log.info("Fundamento dictamen creado: {}", guardado.getIdFundamentoDictamen());
        return exito(fundamentoDictamenMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatFundamentoDictamenResponse> actualizar(
            final Long idFundamentoDictamen, final CatFundamentoDictamenRequest request) {

        final Optional<CatFundamentoDictamen> opt = fundamentoDictamenRepository.findById(idFundamentoDictamen);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.FUNDAMENTO_DICTAMEN_NO_ENCONTRADO,
                    "Fundamento dictamen no encontrado: " + idFundamentoDictamen);
        }
        final CatFundamentoDictamen entidad = opt.get();
        if (request.getDescripcion() != null) {
            entidad.setDescripcion(request.getDescripcion());
        }
        if (request.getBlnSentidoFundamento() != null) {
            entidad.setBlnSentidoFundamento(request.getBlnSentidoFundamento());
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
        return exito(fundamentoDictamenMapper.toResponse(fundamentoDictamenRepository.save(entidad)));
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
