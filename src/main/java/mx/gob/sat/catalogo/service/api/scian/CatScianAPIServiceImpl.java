package mx.gob.sat.catalogo.service.api.scian;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.scian.CatScianRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.scian.CatScianResponse;
import mx.gob.sat.catalogo.model.entity.CatScian;
import mx.gob.sat.catalogo.repository.CatScianRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatScianMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatScianAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo SCIAN.</p>
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
class CatScianAPIServiceImpl implements CatScianAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveScian", "cveScian",
            "descScian", "descScian"
    );

    private static final String DEFAULT_SORT = "cveScian";

    private final CatScianRepository scianRepository;
    private final CatScianMapper scianMapper;

    @Override
    public BaseResponse<PaginaResponse<CatScianResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatScian> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? scianRepository.findAll(pageable)
                : scianRepository.findByDescScianContainingIgnoreCaseOrCveScianContainingIgnoreCase(
                        busqueda, busqueda, pageable);

        final List<CatScianResponse> contenido = scianMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatScianResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatScianResponse> findById(final String cveScian) {
        final Optional<CatScian> resultado = scianRepository.findById(cveScian);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.SCIAN_NO_ENCONTRADO, "SCIAN no encontrado: " + cveScian);
        }
        return exito(scianMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatScianResponse> crear(final CatScianRequest request) {
        final CatScian guardado = scianRepository.save(scianMapper.toEntity(request));
        log.info("SCIAN creado: {}", guardado.getCveScian());
        return exito(scianMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatScianResponse> actualizar(final String cveScian, final CatScianRequest request) {
        final Optional<CatScian> opt = scianRepository.findById(cveScian);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.SCIAN_NO_ENCONTRADO, "SCIAN no encontrado: " + cveScian);
        }
        final CatScian entidad = opt.get();
        if (request.getDescScian() != null) {
            entidad.setDescScian(request.getDescScian());
        }
        if (request.getGiro() != null) {
            entidad.setGiro(request.getGiro());
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
        return exito(scianMapper.toResponse(scianRepository.save(entidad)));
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
