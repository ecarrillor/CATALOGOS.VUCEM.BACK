package mx.gob.sat.catalogo.service.api.colonia;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.colonia.CatColoniaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.colonia.CatColoniaResponse;
import mx.gob.sat.catalogo.model.entity.CatColonia;
import mx.gob.sat.catalogo.model.entity.CatDelegMun;
import mx.gob.sat.catalogo.model.entity.CatLocalidad;
import mx.gob.sat.catalogo.repository.CatColoniaRepository;
import mx.gob.sat.catalogo.repository.CatDelegMunRepository;
import mx.gob.sat.catalogo.repository.CatLocalidadRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatColoniaMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatColoniaAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de colonias.</p>
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
class CatColoniaAPIServiceImpl implements CatColoniaAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveColonia", "cveColonia",
            "nombre", "nombre");

    private static final String DEFAULT_SORT = "cveColonia";

    private final CatColoniaRepository coloniaRepository;
    private final CatDelegMunRepository delegMunRepository;
    private final CatLocalidadRepository localidadRepository;
    private final CatColoniaMapper coloniaMapper;

    @Override
    public BaseResponse<PaginaResponse<CatColoniaResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatColonia> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? coloniaRepository.findAll(pageable)
                : coloniaRepository.findByNombreContainingIgnoreCaseOrCveColoniaContainingIgnoreCase(
                        busqueda, busqueda, pageable);

        final List<CatColoniaResponse> contenido =
                coloniaMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatColoniaResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatColoniaResponse> findById(final String cveColonia) {
        final Optional<CatColonia> resultado = coloniaRepository.findById(cveColonia);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.COLONIA_NO_ENCONTRADA,
                    "Colonia no encontrada: " + cveColonia);
        }
        return exito(coloniaMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatColoniaResponse> crear(final CatColoniaRequest request) {
        final CatColonia entidad = new CatColonia();
        entidad.setCveColonia(request.getCveColonia());
        entidad.setNombre(request.getNombre());
        entidad.setFecCaptura(request.getFecCaptura());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setCp(request.getCp());
        entidad.setSatColonyCd(request.getSatColonyCd());
        entidad.setBlnActivo(request.getBlnActivo());

        if (request.getCveDelegMun() != null) {
            final Optional<CatDelegMun> delegMun = delegMunRepository.findById(request.getCveDelegMun());
            if (delegMun.isEmpty()) {
                return error(CodigoErrorConst.DELEG_MUN_NO_ENCONTRADA,
                        "Delegacion/municipio no encontrado: " + request.getCveDelegMun());
            }
            entidad.setCveDelegMun(delegMun.get());
        }

        if (request.getCveLocalidad() != null) {
            final Optional<CatLocalidad> localidad = localidadRepository.findById(request.getCveLocalidad());
            if (localidad.isEmpty()) {
                return error(CodigoErrorConst.LOCALIDAD_NO_ENCONTRADA,
                        "Localidad no encontrada: " + request.getCveLocalidad());
            }
            entidad.setCveLocalidad(localidad.get());
        }

        final CatColonia guardada = coloniaRepository.save(entidad);
        log.info("Colonia creada: {}", guardada.getCveColonia());
        return exito(coloniaMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatColoniaResponse> actualizar(
            final String cveColonia, final CatColoniaRequest request) {

        final Optional<CatColonia> opt = coloniaRepository.findById(cveColonia);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.COLONIA_NO_ENCONTRADA,
                    "Colonia no encontrada: " + cveColonia);
        }
        final CatColonia entidad = opt.get();
        if (request.getNombre() != null) {
            entidad.setNombre(request.getNombre());
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
        if (request.getCp() != null) {
            entidad.setCp(request.getCp());
        }
        if (request.getSatColonyCd() != null) {
            entidad.setSatColonyCd(request.getSatColonyCd());
        }
        if (request.getBlnActivo() != null) {
            entidad.setBlnActivo(request.getBlnActivo());
        }
        if (request.getCveDelegMun() != null) {
            final Optional<CatDelegMun> delegMun = delegMunRepository.findById(request.getCveDelegMun());
            if (delegMun.isEmpty()) {
                return error(CodigoErrorConst.DELEG_MUN_NO_ENCONTRADA,
                        "Delegacion/municipio no encontrado: " + request.getCveDelegMun());
            }
            entidad.setCveDelegMun(delegMun.get());
        }
        if (request.getCveLocalidad() != null) {
            final Optional<CatLocalidad> localidad = localidadRepository.findById(request.getCveLocalidad());
            if (localidad.isEmpty()) {
                return error(CodigoErrorConst.LOCALIDAD_NO_ENCONTRADA,
                        "Localidad no encontrada: " + request.getCveLocalidad());
            }
            entidad.setCveLocalidad(localidad.get());
        }
        return exito(coloniaMapper.toResponse(coloniaRepository.save(entidad)));
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
