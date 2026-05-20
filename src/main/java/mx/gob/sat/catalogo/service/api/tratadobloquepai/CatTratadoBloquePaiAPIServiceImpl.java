package mx.gob.sat.catalogo.service.api.tratadobloquepai;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.tratadobloquepai.CatTratadoBloquePaiRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tratadobloquepai.CatTratadoBloquePaiResponse;
import mx.gob.sat.catalogo.model.entity.CatTratadoBloquePai;
import mx.gob.sat.catalogo.model.entity.CatTratadoBloquePaiId;
import mx.gob.sat.catalogo.repository.CatTratadoBloquePaiRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatTratadoBloquePaiMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementacion del servicio para el catalogo de tratado bloque pais.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatTratadoBloquePaiAPIServiceImpl implements CatTratadoBloquePaiAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cvePais", "id.cvePais",
            "idTratadoAcuerdo", "id.idTratadoAcuerdo");
    private static final String DEFAULT_SORT = "id.cvePais";

    private final CatTratadoBloquePaiRepository tratadoBloquePaiRepository;
    private final CatTratadoBloquePaiMapper tratadoBloquePaiMapper;

    @Override
    public BaseResponse<PaginaResponse<CatTratadoBloquePaiResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted() ? PageRequest.of(pagina, tamano, sort) : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatTratadoBloquePai> paginaResultado = tratadoBloquePaiRepository.findAll(pageable);
        final List<CatTratadoBloquePaiResponse> contenido = tratadoBloquePaiMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatTratadoBloquePaiResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatTratadoBloquePaiResponse> findById(final String cvePais, final Short idTratadoAcuerdo) {
        final CatTratadoBloquePaiId id = new CatTratadoBloquePaiId();
        id.setCvePais(cvePais);
        id.setIdTratadoAcuerdo(idTratadoAcuerdo);
        final Optional<CatTratadoBloquePai> resultado = tratadoBloquePaiRepository.findById(id);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.TRATADO_BLOQUE_PAI_NO_ENCONTRADO, "Tratado Bloque Pais no encontrado: " + cvePais + "/" + idTratadoAcuerdo);
        }
        return exito(tratadoBloquePaiMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatTratadoBloquePaiResponse> crear(final CatTratadoBloquePaiRequest request) {
        final CatTratadoBloquePai entidad = new CatTratadoBloquePai();
        final CatTratadoBloquePaiId id = new CatTratadoBloquePaiId();
        id.setCvePais(request.getCvePais());
        id.setIdTratadoAcuerdo(request.getIdTratadoAcuerdo());
        entidad.setId(id);
        entidad.setFecCaptura(request.getFecCaptura());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        entidad.setBlnEnvioElectronico(request.getBlnEnvioElectronico());
        entidad.setBlnMuestraCertificado(request.getBlnMuestraCertificado());
        final CatTratadoBloquePai guardado = tratadoBloquePaiRepository.save(entidad);
        log.info("Tratado Bloque Pais creado: {}/{}", guardado.getId().getCvePais(), guardado.getId().getIdTratadoAcuerdo());
        return exito(tratadoBloquePaiMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatTratadoBloquePaiResponse> actualizar(final String cvePais, final Short idTratadoAcuerdo, final CatTratadoBloquePaiRequest request) {
        final CatTratadoBloquePaiId id = new CatTratadoBloquePaiId();
        id.setCvePais(cvePais);
        id.setIdTratadoAcuerdo(idTratadoAcuerdo);
        final Optional<CatTratadoBloquePai> opt = tratadoBloquePaiRepository.findById(id);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.TRATADO_BLOQUE_PAI_NO_ENCONTRADO, "Tratado Bloque Pais no encontrado: " + cvePais + "/" + idTratadoAcuerdo);
        }
        final CatTratadoBloquePai entidad = opt.get();
        if (request.getFecCaptura() != null) { entidad.setFecCaptura(request.getFecCaptura()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getBlnEnvioElectronico() != null) { entidad.setBlnEnvioElectronico(request.getBlnEnvioElectronico()); }
        if (request.getBlnMuestraCertificado() != null) { entidad.setBlnMuestraCertificado(request.getBlnMuestraCertificado()); }
        return exito(tratadoBloquePaiMapper.toResponse(tratadoBloquePaiRepository.save(entidad)));
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
