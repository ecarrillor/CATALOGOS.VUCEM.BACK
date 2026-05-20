package mx.gob.sat.catalogo.service.api.fraccionttra;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.fraccionttra.CatFraccionTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.fraccionttra.CatFraccionTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatFraccionTtra;
import mx.gob.sat.catalogo.repository.CatCategoriaTextilRepository;
import mx.gob.sat.catalogo.repository.CatFraccionArancelariaRepository;
import mx.gob.sat.catalogo.repository.CatFraccionTtraRepository;
import mx.gob.sat.catalogo.repository.CatTipoTramiteRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatFraccionTtraMapper;
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
class CatFraccionTtraAPIServiceImpl implements CatFraccionTtraAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of("idFraccionGob", "idFraccionGob");
    private static final String DEFAULT_SORT = "idFraccionGob";

    private final CatFraccionTtraRepository fraccionTtraRepository;
    private final CatFraccionArancelariaRepository fraccionArancelariaRepository;
    private final CatTipoTramiteRepository tipoTramiteRepository;
    private final CatCategoriaTextilRepository categoriaTextilRepository;
    private final CatFraccionTtraMapper fraccionTtraMapper;

    @Override
    public BaseResponse<PaginaResponse<CatFraccionTtraResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatFraccionTtra> paginaResultado = fraccionTtraRepository.findAll(pageable);
        final List<CatFraccionTtraResponse> contenido = fraccionTtraMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatFraccionTtraResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatFraccionTtraResponse> findById(final Long idFraccionGob) {
        final Optional<CatFraccionTtra> resultado = fraccionTtraRepository.findById(idFraccionGob);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.FRACCION_TTRA_NO_ENCONTRADA, "Fraccion Ttra no encontrada: " + idFraccionGob);
        }
        return exito(fraccionTtraMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatFraccionTtraResponse> crear(final CatFraccionTtraRequest request) {
        final CatFraccionTtra entidad = new CatFraccionTtra();
        entidad.setIdFraccionGob(request.getIdFraccionGob());
        if (request.getCveFraccion() != null) {
            fraccionArancelariaRepository.findById(request.getCveFraccion()).ifPresent(entidad::setCveFraccion);
        }
        if (request.getIdTipoTramite() != null) {
            tipoTramiteRepository.findById(request.getIdTipoTramite()).ifPresent(entidad::setIdTipoTramite);
        }
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        entidad.setDescFraccionAlt(request.getDescFraccionAlt());
        entidad.setIdeClasifPartida(request.getIdeClasifPartida());
        entidad.setBlnFraccionControlada(request.getBlnFraccionControlada());
        if (request.getIdCategoriaTextil() != null) {
            categoriaTextilRepository.findById(request.getIdCategoriaTextil()).ifPresent(entidad::setIdCategoriaTextil);
        }
        entidad.setFactorConversion(request.getFactorConversion());
        entidad.setValorEquivalencia(request.getValorEquivalencia());
        entidad.setCveUnidadMedida(request.getCveUnidadMedida());
        entidad.setReglaAplicable(request.getReglaAplicable());
        final CatFraccionTtra guardada = fraccionTtraRepository.save(entidad);
        log.info("Fraccion Ttra creada: {}", guardada.getIdFraccionGob());
        return exito(fraccionTtraMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatFraccionTtraResponse> actualizar(final Long idFraccionGob, final CatFraccionTtraRequest request) {
        final Optional<CatFraccionTtra> opt = fraccionTtraRepository.findById(idFraccionGob);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.FRACCION_TTRA_NO_ENCONTRADA, "Fraccion Ttra no encontrada: " + idFraccionGob);
        }
        final CatFraccionTtra entidad = opt.get();
        if (request.getCveFraccion() != null) {
            fraccionArancelariaRepository.findById(request.getCveFraccion()).ifPresent(entidad::setCveFraccion);
        }
        if (request.getIdTipoTramite() != null) {
            tipoTramiteRepository.findById(request.getIdTipoTramite()).ifPresent(entidad::setIdTipoTramite);
        }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getDescFraccionAlt() != null) { entidad.setDescFraccionAlt(request.getDescFraccionAlt()); }
        if (request.getIdeClasifPartida() != null) { entidad.setIdeClasifPartida(request.getIdeClasifPartida()); }
        if (request.getBlnFraccionControlada() != null) { entidad.setBlnFraccionControlada(request.getBlnFraccionControlada()); }
        if (request.getIdCategoriaTextil() != null) {
            categoriaTextilRepository.findById(request.getIdCategoriaTextil()).ifPresent(entidad::setIdCategoriaTextil);
        }
        if (request.getFactorConversion() != null) { entidad.setFactorConversion(request.getFactorConversion()); }
        if (request.getValorEquivalencia() != null) { entidad.setValorEquivalencia(request.getValorEquivalencia()); }
        if (request.getCveUnidadMedida() != null) { entidad.setCveUnidadMedida(request.getCveUnidadMedida()); }
        if (request.getReglaAplicable() != null) { entidad.setReglaAplicable(request.getReglaAplicable()); }
        return exito(fraccionTtraMapper.toResponse(fraccionTtraRepository.save(entidad)));
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
