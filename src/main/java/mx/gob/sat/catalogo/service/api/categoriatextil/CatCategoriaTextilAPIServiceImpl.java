package mx.gob.sat.catalogo.service.api.categoriatextil;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.categoriatextil.CatCategoriaTextilRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.categoriatextil.CatCategoriaTextilResponse;
import mx.gob.sat.catalogo.model.entity.CatCategoriaTextil;
import mx.gob.sat.catalogo.model.entity.CatUnidadMedida;
import mx.gob.sat.catalogo.repository.CatCategoriaTextilRepository;
import mx.gob.sat.catalogo.repository.CatUnidadMedidaRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatCategoriaTextilMapper;
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
class CatCategoriaTextilAPIServiceImpl implements CatCategoriaTextilAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idCategoriaTextil", "idCategoriaTextil",
            "descripcion", "descripcion"
    );
    private static final String DEFAULT_SORT = "idCategoriaTextil";

    private final CatCategoriaTextilRepository categoriaTextilRepository;
    private final CatUnidadMedidaRepository unidadMedidaRepository;
    private final CatCategoriaTextilMapper categoriaTextilMapper;

    @Override
    public BaseResponse<PaginaResponse<CatCategoriaTextilResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatCategoriaTextil> paginaResultado = categoriaTextilRepository.findAll(pageable);
        final List<CatCategoriaTextilResponse> contenido = categoriaTextilMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatCategoriaTextilResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatCategoriaTextilResponse> findById(final Long idCategoriaTextil) {
        final Optional<CatCategoriaTextil> resultado = categoriaTextilRepository.findById(idCategoriaTextil);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.CATEGORIA_TEXTIL_NO_ENCONTRADO, "Categoria Textil no encontrada: " + idCategoriaTextil);
        }
        return exito(categoriaTextilMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatCategoriaTextilResponse> crear(final CatCategoriaTextilRequest request) {
        final CatCategoriaTextil entidad = new CatCategoriaTextil();
        entidad.setDescripcion(request.getDescripcion());
        entidad.setCodCategoriaTextil(request.getCodCategoriaTextil());
        entidad.setBlnNpa(request.getBlnNpa());
        entidad.setFactConversion(request.getFactConversion());
        entidad.setFecCaptura(request.getFecCaptura());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setFecActualizacion(request.getFecActualizacion());
        entidad.setBlnActivo(request.getBlnActivo());
        if (request.getCveUnidadMedida() != null) {
            final Optional<CatUnidadMedida> unidadMedida = unidadMedidaRepository.findById(request.getCveUnidadMedida());
            unidadMedida.ifPresent(entidad::setCveUnidadMedida);
        }
        if (request.getCveUnidadMedidaEquivalente() != null) {
            final Optional<CatUnidadMedida> unidadMedidaEquivalente = unidadMedidaRepository.findById(request.getCveUnidadMedidaEquivalente());
            unidadMedidaEquivalente.ifPresent(entidad::setCveUnidadMedidaEquivalente);
        }
        final CatCategoriaTextil guardado = categoriaTextilRepository.save(entidad);
        log.info("Categoria Textil creada: {}", guardado.getIdCategoriaTextil());
        return exito(categoriaTextilMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatCategoriaTextilResponse> actualizar(final Long idCategoriaTextil, final CatCategoriaTextilRequest request) {
        final Optional<CatCategoriaTextil> opt = categoriaTextilRepository.findById(idCategoriaTextil);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.CATEGORIA_TEXTIL_NO_ENCONTRADO, "Categoria Textil no encontrada: " + idCategoriaTextil);
        }
        final CatCategoriaTextil entidad = opt.get();
        if (request.getDescripcion() != null) { entidad.setDescripcion(request.getDescripcion()); }
        if (request.getCodCategoriaTextil() != null) { entidad.setCodCategoriaTextil(request.getCodCategoriaTextil()); }
        if (request.getBlnNpa() != null) { entidad.setBlnNpa(request.getBlnNpa()); }
        if (request.getFactConversion() != null) { entidad.setFactConversion(request.getFactConversion()); }
        if (request.getFecCaptura() != null) { entidad.setFecCaptura(request.getFecCaptura()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getFecActualizacion() != null) { entidad.setFecActualizacion(request.getFecActualizacion()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getCveUnidadMedida() != null) {
            final Optional<CatUnidadMedida> unidadMedida = unidadMedidaRepository.findById(request.getCveUnidadMedida());
            unidadMedida.ifPresent(entidad::setCveUnidadMedida);
        }
        if (request.getCveUnidadMedidaEquivalente() != null) {
            final Optional<CatUnidadMedida> unidadMedidaEquivalente = unidadMedidaRepository.findById(request.getCveUnidadMedidaEquivalente());
            unidadMedidaEquivalente.ifPresent(entidad::setCveUnidadMedidaEquivalente);
        }
        return exito(categoriaTextilMapper.toResponse(categoriaTextilRepository.save(entidad)));
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
