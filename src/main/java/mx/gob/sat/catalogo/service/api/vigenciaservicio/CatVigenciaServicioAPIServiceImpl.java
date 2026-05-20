package mx.gob.sat.catalogo.service.api.vigenciaservicio;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.vigenciaservicio.CatVigenciaServicioRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.vigenciaservicio.CatVigenciaServicioResponse;
import mx.gob.sat.catalogo.model.entity.CatVigenciaServicio;
import mx.gob.sat.catalogo.repository.CatCriterioOrigenRepository;
import mx.gob.sat.catalogo.repository.CatTratadoAcuerdoRepository;
import mx.gob.sat.catalogo.repository.CatVigenciaServicioRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatVigenciaServicioMapper;
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
class CatVigenciaServicioAPIServiceImpl implements CatVigenciaServicioAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of("idVigenciaServicio", "idVigenciaServicio");
    private static final String DEFAULT_SORT = "idVigenciaServicio";

    private final CatVigenciaServicioRepository vigenciaServicioRepository;
    private final CatVigenciaServicioMapper vigenciaServicioMapper;
    private final CatTratadoAcuerdoRepository tratadoAcuerdoRepository;
    private final CatCriterioOrigenRepository criterioOrigenRepository;

    @Override
    public BaseResponse<PaginaResponse<CatVigenciaServicioResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatVigenciaServicio> paginaResultado = vigenciaServicioRepository.findAll(pageable);
        final List<CatVigenciaServicioResponse> contenido = vigenciaServicioMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatVigenciaServicioResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatVigenciaServicioResponse> findById(final Short idVigenciaServicio) {
        final Optional<CatVigenciaServicio> resultado = vigenciaServicioRepository.findById(idVigenciaServicio);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.VIGENCIA_SERVICIO_NO_ENCONTRADA, "Vigencia Servicio no encontrada: " + idVigenciaServicio);
        }
        return exito(vigenciaServicioMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatVigenciaServicioResponse> crear(final CatVigenciaServicioRequest request) {
        final CatVigenciaServicio entidad = new CatVigenciaServicio();
        entidad.setIdVigenciaServicio(request.getIdVigenciaServicio());
        entidad.setNumVigencia(request.getNumVigencia());
        entidad.setIdeTipoVigencia(request.getIdeTipoVigencia());
        entidad.setIdeTipoServicioCeror(request.getIdeTipoServicioCeror());
        if (request.getIdBloque() != null) { tratadoAcuerdoRepository.findById(request.getIdBloque()).ifPresent(entidad::setIdBloque); }
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        if (request.getCveCriterioOrigen() != null) { criterioOrigenRepository.findById(request.getCveCriterioOrigen()).ifPresent(entidad::setCveCriterioOrigen); }
        entidad.setCvePais(request.getCvePais());
        entidad.setIdTratadoAcuerdo(request.getIdTratadoAcuerdo());
        final CatVigenciaServicio guardada = vigenciaServicioRepository.save(entidad);
        log.info("Vigencia Servicio creada: {}", guardada.getIdVigenciaServicio());
        return exito(vigenciaServicioMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatVigenciaServicioResponse> actualizar(final Short idVigenciaServicio, final CatVigenciaServicioRequest request) {
        final Optional<CatVigenciaServicio> opt = vigenciaServicioRepository.findById(idVigenciaServicio);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.VIGENCIA_SERVICIO_NO_ENCONTRADA, "Vigencia Servicio no encontrada: " + idVigenciaServicio);
        }
        final CatVigenciaServicio entidad = opt.get();
        if (request.getNumVigencia() != null) { entidad.setNumVigencia(request.getNumVigencia()); }
        if (request.getIdeTipoVigencia() != null) { entidad.setIdeTipoVigencia(request.getIdeTipoVigencia()); }
        if (request.getIdeTipoServicioCeror() != null) { entidad.setIdeTipoServicioCeror(request.getIdeTipoServicioCeror()); }
        if (request.getIdBloque() != null) { tratadoAcuerdoRepository.findById(request.getIdBloque()).ifPresent(entidad::setIdBloque); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getCveCriterioOrigen() != null) { criterioOrigenRepository.findById(request.getCveCriterioOrigen()).ifPresent(entidad::setCveCriterioOrigen); }
        if (request.getCvePais() != null) { entidad.setCvePais(request.getCvePais()); }
        if (request.getIdTratadoAcuerdo() != null) { entidad.setIdTratadoAcuerdo(request.getIdTratadoAcuerdo()); }
        return exito(vigenciaServicioMapper.toResponse(vigenciaServicioRepository.save(entidad)));
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
