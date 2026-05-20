package mx.gob.sat.catalogo.service.api.paistratadoacuerdo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.paistratadoacuerdo.CatPaisTratadoAcuerdoRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.paistratadoacuerdo.CatPaisTratadoAcuerdoResponse;
import mx.gob.sat.catalogo.model.entity.CatPaisTratadoAcuerdo;
import mx.gob.sat.catalogo.model.entity.CatPaisTratadoAcuerdoId;
import mx.gob.sat.catalogo.repository.CatPaisTratadoAcuerdoRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatPaisTratadoAcuerdoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementacion del servicio para el catalogo de pais tratado acuerdo.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatPaisTratadoAcuerdoAPIServiceImpl implements CatPaisTratadoAcuerdoAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cvePais", "id.cvePais",
            "idTratadoAcuerdo", "id.idTratadoAcuerdo");
    private static final String DEFAULT_SORT = "id.cvePais";

    private final CatPaisTratadoAcuerdoRepository paisTratadoAcuerdoRepository;
    private final CatPaisTratadoAcuerdoMapper paisTratadoAcuerdoMapper;

    @Override
    public BaseResponse<PaginaResponse<CatPaisTratadoAcuerdoResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted() ? PageRequest.of(pagina, tamano, sort) : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatPaisTratadoAcuerdo> paginaResultado = paisTratadoAcuerdoRepository.findAll(pageable);
        final List<CatPaisTratadoAcuerdoResponse> contenido = paisTratadoAcuerdoMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatPaisTratadoAcuerdoResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatPaisTratadoAcuerdoResponse> findById(final String cvePais, final Short idTratadoAcuerdo) {
        final CatPaisTratadoAcuerdoId id = new CatPaisTratadoAcuerdoId();
        id.setCvePais(cvePais);
        id.setIdTratadoAcuerdo(idTratadoAcuerdo);
        final Optional<CatPaisTratadoAcuerdo> resultado = paisTratadoAcuerdoRepository.findById(id);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.PAIS_TRATADO_ACUERDO_NO_ENCONTRADO, "Pais Tratado Acuerdo no encontrado: " + cvePais + "/" + idTratadoAcuerdo);
        }
        return exito(paisTratadoAcuerdoMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatPaisTratadoAcuerdoResponse> crear(final CatPaisTratadoAcuerdoRequest request) {
        final CatPaisTratadoAcuerdo entidad = new CatPaisTratadoAcuerdo();
        final CatPaisTratadoAcuerdoId id = new CatPaisTratadoAcuerdoId();
        id.setCvePais(request.getCvePais());
        id.setIdTratadoAcuerdo(request.getIdTratadoAcuerdo());
        entidad.setId(id);
        entidad.setFecCaptura(request.getFecCaptura());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        entidad.setBlnEnvioElectronico(request.getBlnEnvioElectronico());
        final CatPaisTratadoAcuerdo guardado = paisTratadoAcuerdoRepository.save(entidad);
        log.info("Pais Tratado Acuerdo creado: {}/{}", guardado.getId().getCvePais(), guardado.getId().getIdTratadoAcuerdo());
        return exito(paisTratadoAcuerdoMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatPaisTratadoAcuerdoResponse> actualizar(final String cvePais, final Short idTratadoAcuerdo, final CatPaisTratadoAcuerdoRequest request) {
        final CatPaisTratadoAcuerdoId id = new CatPaisTratadoAcuerdoId();
        id.setCvePais(cvePais);
        id.setIdTratadoAcuerdo(idTratadoAcuerdo);
        final Optional<CatPaisTratadoAcuerdo> opt = paisTratadoAcuerdoRepository.findById(id);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.PAIS_TRATADO_ACUERDO_NO_ENCONTRADO, "Pais Tratado Acuerdo no encontrado: " + cvePais + "/" + idTratadoAcuerdo);
        }
        final CatPaisTratadoAcuerdo entidad = opt.get();
        if (request.getFecCaptura() != null) { entidad.setFecCaptura(request.getFecCaptura()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getBlnEnvioElectronico() != null) { entidad.setBlnEnvioElectronico(request.getBlnEnvioElectronico()); }
        return exito(paisTratadoAcuerdoMapper.toResponse(paisTratadoAcuerdoRepository.save(entidad)));
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
