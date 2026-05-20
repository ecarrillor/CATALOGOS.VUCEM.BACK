package mx.gob.sat.catalogo.service.api.condicionuso;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.condicionuso.CatCondicionUsoRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.condicionuso.CatCondicionUsoResponse;
import mx.gob.sat.catalogo.model.entity.CatCondicionUso;
import mx.gob.sat.catalogo.repository.CatCondicionUsoRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatCondicionUsoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatCondicionUsoAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de condiciones de uso.</p>
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
class CatCondicionUsoAPIServiceImpl implements CatCondicionUsoAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idCondicionUso", "idCondicionUso");

    private static final String DEFAULT_SORT = "idCondicionUso";

    private final CatCondicionUsoRepository condicionUsoRepository;
    private final CatCondicionUsoMapper condicionUsoMapper;

    @Override
    public BaseResponse<PaginaResponse<CatCondicionUsoResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatCondicionUso> paginaResultado = condicionUsoRepository.findAll(pageable);

        final List<CatCondicionUsoResponse> contenido =
                condicionUsoMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatCondicionUsoResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatCondicionUsoResponse> findById(final Short idCondicionUso) {
        final Optional<CatCondicionUso> resultado = condicionUsoRepository.findById(idCondicionUso);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.CONDICION_USO_NO_ENCONTRADA,
                    "Condicion de uso no encontrada: " + idCondicionUso);
        }
        return exito(condicionUsoMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatCondicionUsoResponse> crear(final CatCondicionUsoRequest request) {
        final CatCondicionUso guardada = condicionUsoRepository.save(condicionUsoMapper.toEntity(request));
        log.info("Condicion de uso creada: {}", guardada.getIdCondicionUso());
        return exito(condicionUsoMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatCondicionUsoResponse> actualizar(
            final Short idCondicionUso, final CatCondicionUsoRequest request) {

        final Optional<CatCondicionUso> opt = condicionUsoRepository.findById(idCondicionUso);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.CONDICION_USO_NO_ENCONTRADA,
                    "Condicion de uso no encontrada: " + idCondicionUso);
        }
        final CatCondicionUso entidad = opt.get();
        if (request.getDescripcion() != null) {
            entidad.setDescripcion(request.getDescripcion());
        }
        if (request.getDescripcionHtml() != null) {
            entidad.setDescripcionHtml(request.getDescripcionHtml());
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
        return exito(condicionUsoMapper.toResponse(condicionUsoRepository.save(entidad)));
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
