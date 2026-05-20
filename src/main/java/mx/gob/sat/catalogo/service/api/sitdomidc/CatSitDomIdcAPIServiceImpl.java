package mx.gob.sat.catalogo.service.api.sitdomidc;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.sitdomidc.CatSitDomIdcRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.sitdomidc.CatSitDomIdcResponse;
import mx.gob.sat.catalogo.model.entity.CatSitDomIdc;
import mx.gob.sat.catalogo.repository.CatSitDomIdcRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatSitDomIdcMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatSitDomIdcAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de situaciones de domicilio IDC.</p>
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
class CatSitDomIdcAPIServiceImpl implements CatSitDomIdcAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idSitDomIdc", "idSitDomIdc",
            "descripcion", "descripcion");

    private static final String DEFAULT_SORT = "idSitDomIdc";

    private final CatSitDomIdcRepository sitDomIdcRepository;
    private final CatSitDomIdcMapper sitDomIdcMapper;

    @Override
    public BaseResponse<PaginaResponse<CatSitDomIdcResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatSitDomIdc> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? sitDomIdcRepository.findAll(pageable)
                : sitDomIdcRepository.findByDescripcionContainingIgnoreCase(busqueda, pageable);

        final List<CatSitDomIdcResponse> contenido =
                sitDomIdcMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatSitDomIdcResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatSitDomIdcResponse> findById(final Long idSitDomIdc) {
        final Optional<CatSitDomIdc> resultado = sitDomIdcRepository.findById(idSitDomIdc);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.SIT_DOM_IDC_NO_ENCONTRADO,
                    "Situacion domicilio IDC no encontrada: " + idSitDomIdc);
        }
        return exito(sitDomIdcMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatSitDomIdcResponse> crear(final CatSitDomIdcRequest request) {
        final CatSitDomIdc guardado = sitDomIdcRepository.save(sitDomIdcMapper.toEntity(request));
        log.info("Situacion domicilio IDC creada: {}", guardado.getIdSitDomIdc());
        return exito(sitDomIdcMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatSitDomIdcResponse> actualizar(
            final Long idSitDomIdc, final CatSitDomIdcRequest request) {

        final Optional<CatSitDomIdc> opt = sitDomIdcRepository.findById(idSitDomIdc);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.SIT_DOM_IDC_NO_ENCONTRADO,
                    "Situacion domicilio IDC no encontrada: " + idSitDomIdc);
        }
        final CatSitDomIdc entidad = opt.get();
        if (request.getDescripcion() != null) {
            entidad.setDescripcion(request.getDescripcion());
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
        return exito(sitDomIdcMapper.toResponse(sitDomIdcRepository.save(entidad)));
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
