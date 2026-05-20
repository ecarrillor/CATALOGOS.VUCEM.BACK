package mx.gob.sat.catalogo.service.api.cas;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.cas.CatCasRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.cas.CatCasResponse;
import mx.gob.sat.catalogo.model.entity.CatCas;
import mx.gob.sat.catalogo.repository.CatCasRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatCasMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatCasAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo CAS.</p>
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
class CatCasAPIServiceImpl implements CatCasAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idCas", "idCas",
            "descCas", "descCas");

    private static final String DEFAULT_SORT = "idCas";

    private final CatCasRepository casRepository;
    private final CatCasMapper casMapper;

    @Override
    public BaseResponse<PaginaResponse<CatCasResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatCas> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? casRepository.findAll(pageable)
                : casRepository.findByDescCasContainingIgnoreCase(busqueda, pageable);

        final List<CatCasResponse> contenido =
                casMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatCasResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatCasResponse> findById(final Short idCas) {
        final Optional<CatCas> resultado = casRepository.findById(idCas);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.CAS_NO_ENCONTRADO,
                    "CAS no encontrado: " + idCas);
        }
        return exito(casMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatCasResponse> crear(final CatCasRequest request) {
        final CatCas guardado = casRepository.save(casMapper.toEntity(request));
        log.info("CAS creado: {}", guardado.getIdCas());
        return exito(casMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatCasResponse> actualizar(
            final Short idCas, final CatCasRequest request) {

        final Optional<CatCas> opt = casRepository.findById(idCas);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.CAS_NO_ENCONTRADO,
                    "CAS no encontrado: " + idCas);
        }
        final CatCas entidad = opt.get();
        if (request.getDescCas() != null) {
            entidad.setDescCas(request.getDescCas());
        }
        if (request.getFecIniVigencia() != null) {
            entidad.setFecIniVigencia(request.getFecIniVigencia());
        }
        if (request.getFecFinVigencia() != null) {
            entidad.setFecFinVigencia(request.getFecFinVigencia());
        }
        if (request.getBlActivo() != null) {
            entidad.setBlActivo(request.getBlActivo());
        }
        return exito(casMapper.toResponse(casRepository.save(entidad)));
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
