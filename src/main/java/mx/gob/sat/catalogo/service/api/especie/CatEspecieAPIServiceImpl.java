package mx.gob.sat.catalogo.service.api.especie;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.especie.CatEspecieRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.especie.CatEspecieResponse;
import mx.gob.sat.catalogo.model.entity.CatEspecie;
import mx.gob.sat.catalogo.repository.CatEspecieRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatEspecieMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatEspecieAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de especies.</p>
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
class CatEspecieAPIServiceImpl implements CatEspecieAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idEspecie", "idEspecie", "descEspecie", "descEspecie");
    private static final String DEFAULT_SORT = "idEspecie";
    private final CatEspecieRepository especieRepository;
    private final CatEspecieMapper especieMapper;

    @Override
    public BaseResponse<PaginaResponse<CatEspecieResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatEspecie> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? especieRepository.findAll(pageable)
                : especieRepository.findByDescEspecieContainingIgnoreCase(busqueda, pageable);
        final List<CatEspecieResponse> contenido = especieMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatEspecieResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatEspecieResponse> findById(final Integer idEspecie) {
        final Optional<CatEspecie> resultado = especieRepository.findById(idEspecie);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.ESPECIE_NO_ENCONTRADA, "Especie no encontrada: " + idEspecie);
        }
        return exito(especieMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatEspecieResponse> crear(final CatEspecieRequest request) {
        final CatEspecie guardada = especieRepository.save(especieMapper.toEntity(request));
        log.info("Especie creada: {}", guardada.getIdEspecie());
        return exito(especieMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatEspecieResponse> actualizar(final Integer idEspecie, final CatEspecieRequest request) {
        final Optional<CatEspecie> opt = especieRepository.findById(idEspecie);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.ESPECIE_NO_ENCONTRADA, "Especie no encontrada: " + idEspecie);
        }
        final CatEspecie entidad = opt.get();
        if (request.getDescEspecie() != null) { entidad.setDescEspecie(request.getDescEspecie()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(especieMapper.toResponse(especieRepository.save(entidad)));
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
