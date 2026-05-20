package mx.gob.sat.catalogo.service.api.tarifa;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.tarifa.CatTarifaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tarifa.CatTarifaResponse;
import mx.gob.sat.catalogo.model.entity.CatTarifa;
import mx.gob.sat.catalogo.model.entity.CatTarifaId;
import mx.gob.sat.catalogo.repository.CatTarifaRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatTarifaMapper;
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
class CatTarifaAPIServiceImpl implements CatTarifaAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idTipoTramite", "id.idTipoTramite",
            "fecIniVigencia", "id.fecIniVigencia",
            "ideTipoTarifa", "id.ideTipoTarifa");
    private static final String DEFAULT_SORT = "id.idTipoTramite";

    private final CatTarifaRepository tarifaRepository;
    private final CatTarifaMapper tarifaMapper;

    @Override
    public BaseResponse<PaginaResponse<CatTarifaResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted() ? PageRequest.of(pagina, tamano, sort) : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatTarifa> paginaResultado = tarifaRepository.findAll(pageable);
        final List<CatTarifaResponse> contenido = tarifaMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatTarifaResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatTarifaResponse> findById(final Long idTipoTramite, final LocalDate fecIniVigencia, final String ideTipoTarifa) {
        final CatTarifaId id = new CatTarifaId();
        id.setIdTipoTramite(idTipoTramite);
        id.setFecIniVigencia(fecIniVigencia);
        id.setIdeTipoTarifa(ideTipoTarifa);
        final Optional<CatTarifa> resultado = tarifaRepository.findById(id);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.TARIFA_NO_ENCONTRADA,
                    "Tarifa no encontrada: " + idTipoTramite + "/" + fecIniVigencia + "/" + ideTipoTarifa);
        }
        return exito(tarifaMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatTarifaResponse> crear(final CatTarifaRequest request) {
        final CatTarifa entidad = new CatTarifa();
        final CatTarifaId id = new CatTarifaId();
        id.setIdTipoTramite(request.getIdTipoTramite());
        id.setFecIniVigencia(request.getFecIniVigencia());
        id.setIdeTipoTarifa(request.getIdeTipoTarifa());
        entidad.setId(id);
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setTarifa(request.getTarifa());
        entidad.setBlnActivo(request.getBlnActivo());
        final CatTarifa guardado = tarifaRepository.save(entidad);
        log.info("Tarifa creada: {}/{}/{}", guardado.getId().getIdTipoTramite(), guardado.getId().getFecIniVigencia(), guardado.getId().getIdeTipoTarifa());
        return exito(tarifaMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatTarifaResponse> actualizar(final Long idTipoTramite, final LocalDate fecIniVigencia, final String ideTipoTarifa, final CatTarifaRequest request) {
        final CatTarifaId id = new CatTarifaId();
        id.setIdTipoTramite(idTipoTramite);
        id.setFecIniVigencia(fecIniVigencia);
        id.setIdeTipoTarifa(ideTipoTarifa);
        final Optional<CatTarifa> opt = tarifaRepository.findById(id);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.TARIFA_NO_ENCONTRADA,
                    "Tarifa no encontrada: " + idTipoTramite + "/" + fecIniVigencia + "/" + ideTipoTarifa);
        }
        final CatTarifa entidad = opt.get();
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getTarifa() != null) { entidad.setTarifa(request.getTarifa()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(tarifaMapper.toResponse(tarifaRepository.save(entidad)));
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
