package mx.gob.sat.catalogo.service.api.plazottra;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.plazottra.CatPlazoTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.plazottra.CatPlazoTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatPlazoTtra;
import mx.gob.sat.catalogo.model.entity.CatPlazoTtraId;
import mx.gob.sat.catalogo.repository.CatPlazoTtraRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatPlazoTtraMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatPlazoTtraAPIServiceImpl implements CatPlazoTtraAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idTipoTramite", "id.idTipoTramite",
            "idePlazoVigencia", "id.idePlazoVigencia");
    private static final String DEFAULT_SORT = "id.idTipoTramite";

    private final CatPlazoTtraRepository plazoTtraRepository;
    private final CatPlazoTtraMapper plazoTtraMapper;

    @Override
    public BaseResponse<PaginaResponse<CatPlazoTtraResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted() ? PageRequest.of(pagina, tamano, sort) : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatPlazoTtra> paginaResultado = plazoTtraRepository.findAll(pageable);
        final List<CatPlazoTtraResponse> contenido = plazoTtraMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatPlazoTtraResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatPlazoTtraResponse> findById(final Long idTipoTramite, final String idePlazoVigencia) {
        final CatPlazoTtraId id = new CatPlazoTtraId();
        id.setIdTipoTramite(idTipoTramite);
        id.setIdePlazoVigencia(idePlazoVigencia);
        final Optional<CatPlazoTtra> resultado = plazoTtraRepository.findById(id);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.PLAZO_TTRA_NO_ENCONTRADO,
                    "Plazo ttra no encontrado: " + idTipoTramite + "/" + idePlazoVigencia);
        }
        return exito(plazoTtraMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatPlazoTtraResponse> crear(final CatPlazoTtraRequest request) {
        final CatPlazoTtra entidad = new CatPlazoTtra();
        final CatPlazoTtraId id = new CatPlazoTtraId();
        id.setIdTipoTramite(request.getIdTipoTramite());
        id.setIdePlazoVigencia(request.getIdePlazoVigencia());
        entidad.setId(id);
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        final CatPlazoTtra guardado = plazoTtraRepository.save(entidad);
        log.info("Plazo ttra creado: {}/{}", guardado.getId().getIdTipoTramite(), guardado.getId().getIdePlazoVigencia());
        return exito(plazoTtraMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatPlazoTtraResponse> actualizar(final Long idTipoTramite, final String idePlazoVigencia, final CatPlazoTtraRequest request) {
        final CatPlazoTtraId id = new CatPlazoTtraId();
        id.setIdTipoTramite(idTipoTramite);
        id.setIdePlazoVigencia(idePlazoVigencia);
        final Optional<CatPlazoTtra> opt = plazoTtraRepository.findById(id);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.PLAZO_TTRA_NO_ENCONTRADO,
                    "Plazo ttra no encontrado: " + idTipoTramite + "/" + idePlazoVigencia);
        }
        final CatPlazoTtra entidad = opt.get();
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(plazoTtraMapper.toResponse(plazoTtraRepository.save(entidad)));
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
