package mx.gob.sat.catalogo.service.api.fraccionarancelaria;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.fraccionarancelaria.CatFraccionArancelariaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.fraccionarancelaria.CatFraccionArancelariaResponse;
import mx.gob.sat.catalogo.model.entity.CatFraccionArancelaria;
import mx.gob.sat.catalogo.repository.CatFraccionArancelariaRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatFraccionArancelariaMapper;
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
class CatFraccionArancelariaAPIServiceImpl implements CatFraccionArancelariaAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of("cveFraccion", "cveFraccion");
    private static final String DEFAULT_SORT = "cveFraccion";

    private final CatFraccionArancelariaRepository fraccionArancelariaRepository;
    private final CatFraccionArancelariaMapper fraccionArancelariaMapper;

    @Override
    public BaseResponse<PaginaResponse<CatFraccionArancelariaResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatFraccionArancelaria> paginaResultado = fraccionArancelariaRepository.findAll(pageable);
        final List<CatFraccionArancelariaResponse> contenido = fraccionArancelariaMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatFraccionArancelariaResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatFraccionArancelariaResponse> findById(final String cveFraccion) {
        final Optional<CatFraccionArancelaria> resultado = fraccionArancelariaRepository.findById(cveFraccion);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.FRACCION_ARANCELARIA_NO_ENCONTRADA, "Fraccion Arancelaria no encontrada: " + cveFraccion);
        }
        return exito(fraccionArancelariaMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatFraccionArancelariaResponse> crear(final CatFraccionArancelariaRequest request) {
        final CatFraccionArancelaria entidad = new CatFraccionArancelaria();
        entidad.setCveFraccion(request.getCveFraccion());
        entidad.setDescripcion(request.getDescripcion());
        entidad.setCveSubpartidaFraccion(request.getCveSubpartidaFraccion());
        entidad.setCveCapituloFraccion(request.getCveCapituloFraccion());
        entidad.setCvePartidaFraccion(request.getCvePartidaFraccion());
        entidad.setFecCaptura(request.getFecCaptura());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setCapitulo(request.getCapitulo());
        entidad.setPartida(request.getPartida());
        entidad.setSubPartida(request.getSubPartida());
        entidad.setCveUsuario(request.getCveUsuario());
        entidad.setBlnActivo(request.getBlnActivo());
        entidad.setBlnAnexo28(request.getBlnAnexo28());
        entidad.setBlnDecretoImmex(request.getBlnDecretoImmex());
        final CatFraccionArancelaria guardada = fraccionArancelariaRepository.save(entidad);
        log.info("Fraccion Arancelaria creada: {}", guardada.getCveFraccion());
        return exito(fraccionArancelariaMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatFraccionArancelariaResponse> actualizar(final String cveFraccion, final CatFraccionArancelariaRequest request) {
        final Optional<CatFraccionArancelaria> opt = fraccionArancelariaRepository.findById(cveFraccion);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.FRACCION_ARANCELARIA_NO_ENCONTRADA, "Fraccion Arancelaria no encontrada: " + cveFraccion);
        }
        final CatFraccionArancelaria entidad = opt.get();
        if (request.getDescripcion() != null) { entidad.setDescripcion(request.getDescripcion()); }
        if (request.getCveSubpartidaFraccion() != null) { entidad.setCveSubpartidaFraccion(request.getCveSubpartidaFraccion()); }
        if (request.getCveCapituloFraccion() != null) { entidad.setCveCapituloFraccion(request.getCveCapituloFraccion()); }
        if (request.getCvePartidaFraccion() != null) { entidad.setCvePartidaFraccion(request.getCvePartidaFraccion()); }
        if (request.getFecCaptura() != null) { entidad.setFecCaptura(request.getFecCaptura()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getCapitulo() != null) { entidad.setCapitulo(request.getCapitulo()); }
        if (request.getPartida() != null) { entidad.setPartida(request.getPartida()); }
        if (request.getSubPartida() != null) { entidad.setSubPartida(request.getSubPartida()); }
        if (request.getCveUsuario() != null) { entidad.setCveUsuario(request.getCveUsuario()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getBlnAnexo28() != null) { entidad.setBlnAnexo28(request.getBlnAnexo28()); }
        if (request.getBlnDecretoImmex() != null) { entidad.setBlnDecretoImmex(request.getBlnDecretoImmex()); }
        return exito(fraccionArancelariaMapper.toResponse(fraccionArancelariaRepository.save(entidad)));
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
