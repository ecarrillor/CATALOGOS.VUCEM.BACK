package mx.gob.sat.catalogo.service.api.tratadoacuerdo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.tratadoacuerdo.CatTratadoAcuerdoRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tratadoacuerdo.CatTratadoAcuerdoResponse;
import mx.gob.sat.catalogo.model.entity.CatTratadoAcuerdo;
import mx.gob.sat.catalogo.repository.CatTratadoAcuerdoRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatTratadoAcuerdoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatTratadoAcuerdoAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de tratados y acuerdos.</p>
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
class CatTratadoAcuerdoAPIServiceImpl implements CatTratadoAcuerdoAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idTratadoAcuerdo", "idTratadoAcuerdo",
            "nombre", "nombre",
            "cveTratadoAcuerdo", "cveTratadoAcuerdo");

    private static final String DEFAULT_SORT = "cveTratadoAcuerdo";

    private final CatTratadoAcuerdoRepository tratadoAcuerdoRepository;
    private final CatTratadoAcuerdoMapper tratadoAcuerdoMapper;

    @Override
    public BaseResponse<PaginaResponse<CatTratadoAcuerdoResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatTratadoAcuerdo> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? tratadoAcuerdoRepository.findAll(pageable)
                : tratadoAcuerdoRepository
                        .findByNombreContainingIgnoreCaseOrCveTratadoAcuerdoContainingIgnoreCase(
                                busqueda, busqueda, pageable);

        final List<CatTratadoAcuerdoResponse> contenido =
                tratadoAcuerdoMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatTratadoAcuerdoResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatTratadoAcuerdoResponse> findById(final Short idTratadoAcuerdo) {
        final Optional<CatTratadoAcuerdo> resultado = tratadoAcuerdoRepository.findById(idTratadoAcuerdo);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.TRATADO_ACUERDO_NO_ENCONTRADO,
                    "Tratado o acuerdo no encontrado: " + idTratadoAcuerdo);
        }
        return exito(tratadoAcuerdoMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatTratadoAcuerdoResponse> crear(final CatTratadoAcuerdoRequest request) {
        final CatTratadoAcuerdo guardado = tratadoAcuerdoRepository.save(tratadoAcuerdoMapper.toEntity(request));
        log.info("Tratado o acuerdo creado: {}", guardado.getIdTratadoAcuerdo());
        return exito(tratadoAcuerdoMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatTratadoAcuerdoResponse> actualizar(
            final Short idTratadoAcuerdo, final CatTratadoAcuerdoRequest request) {

        final Optional<CatTratadoAcuerdo> opt = tratadoAcuerdoRepository.findById(idTratadoAcuerdo);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.TRATADO_ACUERDO_NO_ENCONTRADO,
                    "Tratado o acuerdo no encontrado: " + idTratadoAcuerdo);
        }
        final CatTratadoAcuerdo entidad = opt.get();
        if (request.getCveTratadoAcuerdo() != null) {
            entidad.setCveTratadoAcuerdo(request.getCveTratadoAcuerdo());
        }
        if (request.getNombre() != null) {
            entidad.setNombre(request.getNombre());
        }
        if (request.getIdeTipoTratadoAcuerdo() != null) {
            entidad.setIdeTipoTratadoAcuerdo(request.getIdeTipoTratadoAcuerdo());
        }
        if (request.getBlnPexim() != null) {
            entidad.setBlnPexim(request.getBlnPexim());
        }
        if (request.getFecCaptura() != null) {
            entidad.setFecCaptura(request.getFecCaptura());
        }
        if (request.getFecIniVigencia() != null) {
            entidad.setFecIniVigencia(request.getFecIniVigencia());
        }
        if (request.getFecFinVigencia() != null) {
            entidad.setFecFinVigencia(request.getFecFinVigencia());
        }
        if (request.getBlnActivo() != null) {
            entidad.setBlnActivo(request.getBlnActivo());
        }
        if (request.getBlnEvaluarIndividual() != null) {
            entidad.setBlnEvaluarIndividual(request.getBlnEvaluarIndividual());
        }
        if (request.getIdeTipoCupoSaai() != null) {
            entidad.setIdeTipoCupoSaai(request.getIdeTipoCupoSaai());
        }
        return exito(tratadoAcuerdoMapper.toResponse(tratadoAcuerdoRepository.save(entidad)));
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
