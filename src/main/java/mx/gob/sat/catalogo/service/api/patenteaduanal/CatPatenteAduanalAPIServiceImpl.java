package mx.gob.sat.catalogo.service.api.patenteaduanal;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.patenteaduanal.CatPatenteAduanalRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.patenteaduanal.CatPatenteAduanalResponse;
import mx.gob.sat.catalogo.model.entity.CatPatenteAduanal;
import mx.gob.sat.catalogo.repository.CatPatenteAduanalRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatPatenteAduanalMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatPatenteAduanalAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de patentes aduanales.</p>
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
class CatPatenteAduanalAPIServiceImpl implements CatPatenteAduanalAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cvePatenteAduanal", "cvePatenteAduanal", "rfc", "rfc");
    private static final String DEFAULT_SORT = "cvePatenteAduanal";
    private final CatPatenteAduanalRepository patenteAduanalRepository;
    private final CatPatenteAduanalMapper patenteAduanalMapper;

    @Override
    public BaseResponse<PaginaResponse<CatPatenteAduanalResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatPatenteAduanal> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? patenteAduanalRepository.findAll(pageable)
                : patenteAduanalRepository.findByCvePatenteAduanalContainingIgnoreCaseOrRfcContainingIgnoreCase(
                        busqueda, busqueda, pageable);
        final List<CatPatenteAduanalResponse> contenido = patenteAduanalMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatPatenteAduanalResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatPatenteAduanalResponse> findById(final String cvePatenteAduanal) {
        final Optional<CatPatenteAduanal> resultado = patenteAduanalRepository.findById(cvePatenteAduanal);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.PATENTE_ADUANAL_NO_ENCONTRADA, "Patente aduanal no encontrada: " + cvePatenteAduanal);
        }
        return exito(patenteAduanalMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatPatenteAduanalResponse> crear(final CatPatenteAduanalRequest request) {
        final CatPatenteAduanal guardado = patenteAduanalRepository.save(patenteAduanalMapper.toEntity(request));
        log.info("Patente aduanal creada: {}", guardado.getCvePatenteAduanal());
        return exito(patenteAduanalMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatPatenteAduanalResponse> actualizar(final String cvePatenteAduanal, final CatPatenteAduanalRequest request) {
        final Optional<CatPatenteAduanal> opt = patenteAduanalRepository.findById(cvePatenteAduanal);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.PATENTE_ADUANAL_NO_ENCONTRADA, "Patente aduanal no encontrada: " + cvePatenteAduanal);
        }
        final CatPatenteAduanal entidad = opt.get();
        if (request.getRfc() != null) { entidad.setRfc(request.getRfc()); }
        if (request.getIdeEstPatenteAut() != null) { entidad.setIdeEstPatenteAut(request.getIdeEstPatenteAut()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(patenteAduanalMapper.toResponse(patenteAduanalRepository.save(entidad)));
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
