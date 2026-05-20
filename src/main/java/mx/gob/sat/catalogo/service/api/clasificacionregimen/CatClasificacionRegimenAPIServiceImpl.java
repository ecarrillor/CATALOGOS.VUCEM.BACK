package mx.gob.sat.catalogo.service.api.clasificacionregimen;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.clasificacionregimen.CatClasificacionRegimenRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.clasificacionregimen.CatClasificacionRegimenResponse;
import mx.gob.sat.catalogo.model.entity.CatClasificacionRegimen;
import mx.gob.sat.catalogo.model.entity.CatClasificacionRegimenId;
import mx.gob.sat.catalogo.repository.CatClasificacionRegimenRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatClasificacionRegimenMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementacion del servicio para el catalogo de clasificacion de regimen.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatClasificacionRegimenAPIServiceImpl implements CatClasificacionRegimenAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveClasificacionRegimen", "id.cveClasificacionRegimen",
            "cveRegimen", "id.cveRegimen");
    private static final String DEFAULT_SORT = "id.cveClasificacionRegimen";

    private final CatClasificacionRegimenRepository clasificacionRegimenRepository;
    private final CatClasificacionRegimenMapper clasificacionRegimenMapper;

    @Override
    public BaseResponse<PaginaResponse<CatClasificacionRegimenResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted() ? PageRequest.of(pagina, tamano, sort) : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatClasificacionRegimen> paginaResultado = clasificacionRegimenRepository.findAll(pageable);
        final List<CatClasificacionRegimenResponse> contenido = clasificacionRegimenMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatClasificacionRegimenResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatClasificacionRegimenResponse> findById(final String cveClasificacionRegimen, final String cveRegimen) {
        final CatClasificacionRegimenId id = new CatClasificacionRegimenId();
        id.setCveClasificacionRegimen(cveClasificacionRegimen);
        id.setCveRegimen(cveRegimen);
        final Optional<CatClasificacionRegimen> resultado = clasificacionRegimenRepository.findById(id);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.CLASIFICACION_REGIMEN_NO_ENCONTRADO, "Clasificacion Regimen no encontrado: " + cveClasificacionRegimen + "/" + cveRegimen);
        }
        return exito(clasificacionRegimenMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatClasificacionRegimenResponse> crear(final CatClasificacionRegimenRequest request) {
        final CatClasificacionRegimen entidad = new CatClasificacionRegimen();
        final CatClasificacionRegimenId id = new CatClasificacionRegimenId();
        id.setCveClasificacionRegimen(request.getCveClasificacionRegimen());
        id.setCveRegimen(request.getCveRegimen());
        entidad.setId(id);
        entidad.setNombre(request.getNombre());
        entidad.setCodRegimen(request.getCodRegimen());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        final CatClasificacionRegimen guardado = clasificacionRegimenRepository.save(entidad);
        log.info("Clasificacion Regimen creado: {}/{}", guardado.getId().getCveClasificacionRegimen(), guardado.getId().getCveRegimen());
        return exito(clasificacionRegimenMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatClasificacionRegimenResponse> actualizar(final String cveClasificacionRegimen, final String cveRegimen, final CatClasificacionRegimenRequest request) {
        final CatClasificacionRegimenId id = new CatClasificacionRegimenId();
        id.setCveClasificacionRegimen(cveClasificacionRegimen);
        id.setCveRegimen(cveRegimen);
        final Optional<CatClasificacionRegimen> opt = clasificacionRegimenRepository.findById(id);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.CLASIFICACION_REGIMEN_NO_ENCONTRADO, "Clasificacion Regimen no encontrado: " + cveClasificacionRegimen + "/" + cveRegimen);
        }
        final CatClasificacionRegimen entidad = opt.get();
        if (request.getNombre() != null) { entidad.setNombre(request.getNombre()); }
        if (request.getCodRegimen() != null) { entidad.setCodRegimen(request.getCodRegimen()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(clasificacionRegimenMapper.toResponse(clasificacionRegimenRepository.save(entidad)));
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
