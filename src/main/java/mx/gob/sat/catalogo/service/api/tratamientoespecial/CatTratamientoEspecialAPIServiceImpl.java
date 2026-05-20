package mx.gob.sat.catalogo.service.api.tratamientoespecial;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.tratamientoespecial.CatTratamientoEspecialRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tratamientoespecial.CatTratamientoEspecialResponse;
import mx.gob.sat.catalogo.model.entity.CatTratamientoEspecial;
import mx.gob.sat.catalogo.repository.CatTratamientoEspecialRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatTratamientoEspecialMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatTratamientoEspecialAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de tratamientos especiales.</p>
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
class CatTratamientoEspecialAPIServiceImpl implements CatTratamientoEspecialAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idTratamientoEspecial", "idTratamientoEspecial",
            "nombre", "nombre");

    private static final String DEFAULT_SORT = "idTratamientoEspecial";

    private final CatTratamientoEspecialRepository tratamientoEspecialRepository;
    private final CatTratamientoEspecialMapper tratamientoEspecialMapper;

    @Override
    public BaseResponse<PaginaResponse<CatTratamientoEspecialResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatTratamientoEspecial> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? tratamientoEspecialRepository.findAll(pageable)
                : tratamientoEspecialRepository.findByNombreContainingIgnoreCase(busqueda, pageable);

        final List<CatTratamientoEspecialResponse> contenido =
                tratamientoEspecialMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatTratamientoEspecialResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatTratamientoEspecialResponse> findById(final Short idTratamientoEspecial) {
        final Optional<CatTratamientoEspecial> resultado =
                tratamientoEspecialRepository.findById(idTratamientoEspecial);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.TRATAMIENTO_ESPECIAL_NO_ENCONTRADO,
                    "Tratamiento especial no encontrado: " + idTratamientoEspecial);
        }
        return exito(tratamientoEspecialMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatTratamientoEspecialResponse> crear(final CatTratamientoEspecialRequest request) {
        final CatTratamientoEspecial guardado =
                tratamientoEspecialRepository.save(tratamientoEspecialMapper.toEntity(request));
        log.info("Tratamiento especial creado: {}", guardado.getIdTratamientoEspecial());
        return exito(tratamientoEspecialMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatTratamientoEspecialResponse> actualizar(
            final Short idTratamientoEspecial, final CatTratamientoEspecialRequest request) {

        final Optional<CatTratamientoEspecial> opt =
                tratamientoEspecialRepository.findById(idTratamientoEspecial);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.TRATAMIENTO_ESPECIAL_NO_ENCONTRADO,
                    "Tratamiento especial no encontrado: " + idTratamientoEspecial);
        }
        final CatTratamientoEspecial entidad = opt.get();
        if (request.getNombre() != null) {
            entidad.setNombre(request.getNombre());
        }
        if (request.getIdeClasifTratamiento() != null) {
            entidad.setIdeClasifTratamiento(request.getIdeClasifTratamiento());
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
        return exito(tratamientoEspecialMapper.toResponse(tratamientoEspecialRepository.save(entidad)));
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
