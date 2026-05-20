package mx.gob.sat.catalogo.service.api.tratadobloque;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.tratadobloque.CatTratadoBloqueRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tratadobloque.CatTratadoBloqueResponse;
import mx.gob.sat.catalogo.model.entity.CatTratadoBloque;
import mx.gob.sat.catalogo.model.entity.CatTratadoBloqueId;
import mx.gob.sat.catalogo.repository.CatTratadoBloqueRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatTratadoBloqueMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementacion del servicio para el catalogo de tratado bloque.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatTratadoBloqueAPIServiceImpl implements CatTratadoBloqueAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idTratadoAcuerdo", "id.idTratadoAcuerdo",
            "idBloque", "id.idBloque");
    private static final String DEFAULT_SORT = "id.idTratadoAcuerdo";

    private final CatTratadoBloqueRepository tratadoBloqueRepository;
    private final CatTratadoBloqueMapper tratadoBloqueMapper;

    @Override
    public BaseResponse<PaginaResponse<CatTratadoBloqueResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted() ? PageRequest.of(pagina, tamano, sort) : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatTratadoBloque> paginaResultado = tratadoBloqueRepository.findAll(pageable);
        final List<CatTratadoBloqueResponse> contenido = tratadoBloqueMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatTratadoBloqueResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatTratadoBloqueResponse> findById(final Short idTratadoAcuerdo, final Short idBloque) {
        final CatTratadoBloqueId id = new CatTratadoBloqueId();
        id.setIdTratadoAcuerdo(idTratadoAcuerdo);
        id.setIdBloque(idBloque);
        final Optional<CatTratadoBloque> resultado = tratadoBloqueRepository.findById(id);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.TRATADO_BLOQUE_NO_ENCONTRADO, "Tratado Bloque no encontrado: " + idTratadoAcuerdo + "/" + idBloque);
        }
        return exito(tratadoBloqueMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatTratadoBloqueResponse> crear(final CatTratadoBloqueRequest request) {
        final CatTratadoBloque entidad = new CatTratadoBloque();
        final CatTratadoBloqueId id = new CatTratadoBloqueId();
        id.setIdTratadoAcuerdo(request.getIdTratadoAcuerdo());
        id.setIdBloque(request.getIdBloque());
        entidad.setId(id);
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        final CatTratadoBloque guardado = tratadoBloqueRepository.save(entidad);
        log.info("Tratado Bloque creado: {}/{}", guardado.getId().getIdTratadoAcuerdo(), guardado.getId().getIdBloque());
        return exito(tratadoBloqueMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatTratadoBloqueResponse> actualizar(final Short idTratadoAcuerdo, final Short idBloque, final CatTratadoBloqueRequest request) {
        final CatTratadoBloqueId id = new CatTratadoBloqueId();
        id.setIdTratadoAcuerdo(idTratadoAcuerdo);
        id.setIdBloque(idBloque);
        final Optional<CatTratadoBloque> opt = tratadoBloqueRepository.findById(id);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.TRATADO_BLOQUE_NO_ENCONTRADO, "Tratado Bloque no encontrado: " + idTratadoAcuerdo + "/" + idBloque);
        }
        final CatTratadoBloque entidad = opt.get();
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(tratadoBloqueMapper.toResponse(tratadoBloqueRepository.save(entidad)));
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
