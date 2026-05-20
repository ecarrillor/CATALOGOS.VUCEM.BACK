package mx.gob.sat.catalogo.service.api.equivalenciaaelc;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.equivalenciaaelc.CatEquivalenciaAelcRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.equivalenciaaelc.CatEquivalenciaAelcResponse;
import mx.gob.sat.catalogo.model.entity.CatEquivalenciaAelc;
import mx.gob.sat.catalogo.model.entity.CatEquivalenciaAelcId;
import mx.gob.sat.catalogo.repository.CatEquivalenciaAelcRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatEquivalenciaAelcMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatEquivalenciaAelcAPIServiceImpl implements CatEquivalenciaAelcAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of("fecIniVigencia", "id.fecIniVigencia");
    private static final String DEFAULT_SORT = "id.fecIniVigencia";

    private final CatEquivalenciaAelcRepository equivalenciaAelcRepository;
    private final CatEquivalenciaAelcMapper equivalenciaAelcMapper;

    @Override
    public BaseResponse<PaginaResponse<CatEquivalenciaAelcResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted() ? PageRequest.of(pagina, tamano, sort) : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatEquivalenciaAelc> paginaResultado = equivalenciaAelcRepository.findAll(pageable);
        final List<CatEquivalenciaAelcResponse> contenido = equivalenciaAelcMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatEquivalenciaAelcResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatEquivalenciaAelcResponse> findById(final LocalDate fecIniVigencia, final String cveMoneda) {
        final CatEquivalenciaAelcId id = new CatEquivalenciaAelcId();
        id.setFecIniVigencia(fecIniVigencia);
        id.setCveMoneda(cveMoneda);
        final Optional<CatEquivalenciaAelc> resultado = equivalenciaAelcRepository.findById(id);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.EQUIVALENCIA_AELC_NO_ENCONTRADA, "Equivalencia AELC no encontrada: " + fecIniVigencia + "/" + cveMoneda);
        }
        return exito(equivalenciaAelcMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatEquivalenciaAelcResponse> crear(final CatEquivalenciaAelcRequest request) {
        final CatEquivalenciaAelc entidad = new CatEquivalenciaAelc();
        final CatEquivalenciaAelcId id = new CatEquivalenciaAelcId();
        id.setFecIniVigencia(request.getFecIniVigencia());
        id.setCveMoneda(request.getCveMoneda());
        entidad.setId(id);
        entidad.setValor(request.getValor());
        entidad.setFecCaptura(request.getFecCaptura());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        final CatEquivalenciaAelc guardado = equivalenciaAelcRepository.save(entidad);
        log.info("Equivalencia AELC creada: {}/{}", guardado.getId().getFecIniVigencia(), guardado.getId().getCveMoneda());
        return exito(equivalenciaAelcMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatEquivalenciaAelcResponse> actualizar(final LocalDate fecIniVigencia, final String cveMoneda, final CatEquivalenciaAelcRequest request) {
        final CatEquivalenciaAelcId id = new CatEquivalenciaAelcId();
        id.setFecIniVigencia(fecIniVigencia);
        id.setCveMoneda(cveMoneda);
        final Optional<CatEquivalenciaAelc> opt = equivalenciaAelcRepository.findById(id);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.EQUIVALENCIA_AELC_NO_ENCONTRADA, "Equivalencia AELC no encontrada: " + fecIniVigencia + "/" + cveMoneda);
        }
        final CatEquivalenciaAelc entidad = opt.get();
        if (request.getValor() != null) { entidad.setValor(request.getValor()); }
        if (request.getFecCaptura() != null) { entidad.setFecCaptura(request.getFecCaptura()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(equivalenciaAelcMapper.toResponse(equivalenciaAelcRepository.save(entidad)));
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
