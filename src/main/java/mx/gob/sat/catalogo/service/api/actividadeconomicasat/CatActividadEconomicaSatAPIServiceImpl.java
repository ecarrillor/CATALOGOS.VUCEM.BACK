package mx.gob.sat.catalogo.service.api.actividadeconomicasat;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.actividadeconomicasat.CatActividadEconomicaSatRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.actividadeconomicasat.CatActividadEconomicaSatResponse;
import mx.gob.sat.catalogo.model.entity.CatActividadEconomicaSat;
import mx.gob.sat.catalogo.model.entity.CatTipoEmpresaRecif;
import mx.gob.sat.catalogo.repository.CatActividadEconomicaSatRepository;
import mx.gob.sat.catalogo.repository.CatTipoEmpresaRecifRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatActividadEconomicaSatMapper;
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
class CatActividadEconomicaSatAPIServiceImpl implements CatActividadEconomicaSatAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idActividadEconomicaSat", "idActividadEconomicaSat"
    );
    private static final String DEFAULT_SORT = "idActividadEconomicaSat";

    private final CatActividadEconomicaSatRepository actividadEconomicaSatRepository;
    private final CatTipoEmpresaRecifRepository tipoEmpresaRecifRepository;
    private final CatActividadEconomicaSatMapper actividadEconomicaSatMapper;

    @Override
    public BaseResponse<PaginaResponse<CatActividadEconomicaSatResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatActividadEconomicaSat> paginaResultado = actividadEconomicaSatRepository.findAll(pageable);
        final List<CatActividadEconomicaSatResponse> contenido = actividadEconomicaSatMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatActividadEconomicaSatResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatActividadEconomicaSatResponse> findById(final Long idActividadEconomicaSat) {
        final Optional<CatActividadEconomicaSat> resultado = actividadEconomicaSatRepository.findById(idActividadEconomicaSat);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.ACTIVIDAD_ECONOMICA_SAT_NO_ENCONTRADA, "Actividad Economica SAT no encontrada: " + idActividadEconomicaSat);
        }
        return exito(actividadEconomicaSatMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatActividadEconomicaSatResponse> crear(final CatActividadEconomicaSatRequest request) {
        final CatActividadEconomicaSat entidad = new CatActividadEconomicaSat();
        entidad.setIdActividadEconomicaSat(request.getIdActividadEconomicaSat());
        entidad.setDescripcion(request.getDescripcion());
        entidad.setDescScian(request.getDescScian());
        entidad.setDescNotas(request.getDescNotas());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setFecCaptura(request.getFecCaptura());
        entidad.setFecActualizacion(request.getFecActualizacion());
        entidad.setCveTipoIndustriaIdc(request.getCveTipoIndustriaIdc());
        entidad.setBlnActivo(request.getBlnActivo());
        if (request.getIdActividadEconomicaR() != null) {
            final Optional<CatActividadEconomicaSat> parent = actividadEconomicaSatRepository.findById(request.getIdActividadEconomicaR());
            parent.ifPresent(entidad::setIdActividadEconomicaR);
        }
        if (request.getCveTipoEmpresaRecif() != null) {
            final Optional<CatTipoEmpresaRecif> tipoEmpresaRecif = tipoEmpresaRecifRepository.findById(request.getCveTipoEmpresaRecif());
            tipoEmpresaRecif.ifPresent(entidad::setCveTipoEmpresaRecif);
        }
        final CatActividadEconomicaSat guardado = actividadEconomicaSatRepository.save(entidad);
        log.info("Actividad Economica SAT creada: {}", guardado.getIdActividadEconomicaSat());
        return exito(actividadEconomicaSatMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatActividadEconomicaSatResponse> actualizar(final Long idActividadEconomicaSat, final CatActividadEconomicaSatRequest request) {
        final Optional<CatActividadEconomicaSat> opt = actividadEconomicaSatRepository.findById(idActividadEconomicaSat);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.ACTIVIDAD_ECONOMICA_SAT_NO_ENCONTRADA, "Actividad Economica SAT no encontrada: " + idActividadEconomicaSat);
        }
        final CatActividadEconomicaSat entidad = opt.get();
        if (request.getDescripcion() != null) { entidad.setDescripcion(request.getDescripcion()); }
        if (request.getDescScian() != null) { entidad.setDescScian(request.getDescScian()); }
        if (request.getDescNotas() != null) { entidad.setDescNotas(request.getDescNotas()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getFecCaptura() != null) { entidad.setFecCaptura(request.getFecCaptura()); }
        if (request.getFecActualizacion() != null) { entidad.setFecActualizacion(request.getFecActualizacion()); }
        if (request.getCveTipoIndustriaIdc() != null) { entidad.setCveTipoIndustriaIdc(request.getCveTipoIndustriaIdc()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getIdActividadEconomicaR() != null) {
            final Optional<CatActividadEconomicaSat> parent = actividadEconomicaSatRepository.findById(request.getIdActividadEconomicaR());
            parent.ifPresent(entidad::setIdActividadEconomicaR);
        }
        if (request.getCveTipoEmpresaRecif() != null) {
            final Optional<CatTipoEmpresaRecif> tipoEmpresaRecif = tipoEmpresaRecifRepository.findById(request.getCveTipoEmpresaRecif());
            tipoEmpresaRecif.ifPresent(entidad::setCveTipoEmpresaRecif);
        }
        return exito(actividadEconomicaSatMapper.toResponse(actividadEconomicaSatRepository.save(entidad)));
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
