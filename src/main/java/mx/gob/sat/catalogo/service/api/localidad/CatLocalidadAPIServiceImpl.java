package mx.gob.sat.catalogo.service.api.localidad;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.localidad.CatLocalidadRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.localidad.CatLocalidadResponse;
import mx.gob.sat.catalogo.model.entity.CatDelegMun;
import mx.gob.sat.catalogo.model.entity.CatLocalidad;
import mx.gob.sat.catalogo.repository.CatDelegMunRepository;
import mx.gob.sat.catalogo.repository.CatLocalidadRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatLocalidadMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatLocalidadAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de localidades.</p>
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
class CatLocalidadAPIServiceImpl implements CatLocalidadAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveLocalidad", "cveLocalidad",
            "nombre", "nombre");

    private static final String DEFAULT_SORT = "cveLocalidad";

    private final CatLocalidadRepository localidadRepository;
    private final CatDelegMunRepository delegMunRepository;
    private final CatLocalidadMapper localidadMapper;

    @Override
    public BaseResponse<PaginaResponse<CatLocalidadResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatLocalidad> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? localidadRepository.findAll(pageable)
                : localidadRepository.findByNombreContainingIgnoreCaseOrCveLocalidadContainingIgnoreCase(
                        busqueda, busqueda, pageable);

        final List<CatLocalidadResponse> contenido =
                localidadMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatLocalidadResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatLocalidadResponse> findById(final String cveLocalidad) {
        final Optional<CatLocalidad> resultado = localidadRepository.findById(cveLocalidad);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.LOCALIDAD_NO_ENCONTRADA,
                    "Localidad no encontrada: " + cveLocalidad);
        }
        return exito(localidadMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatLocalidadResponse> crear(final CatLocalidadRequest request) {
        final CatLocalidad entidad = new CatLocalidad();
        entidad.setCveLocalidad(request.getCveLocalidad());
        entidad.setNombre(request.getNombre());
        entidad.setFecCaptura(request.getFecCaptura());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setCp(request.getCp());
        entidad.setSatTownCode(request.getSatTownCode());
        entidad.setBlnActivo(request.getBlnActivo());

        if (request.getCveDelegMun() != null) {
            final Optional<CatDelegMun> delegMun = delegMunRepository.findById(request.getCveDelegMun());
            if (delegMun.isEmpty()) {
                return error(CodigoErrorConst.DELEG_MUN_NO_ENCONTRADA,
                        "Delegacion/municipio no encontrado: " + request.getCveDelegMun());
            }
            entidad.setCveDelegMun(delegMun.get());
        }

        final CatLocalidad guardada = localidadRepository.save(entidad);
        log.info("Localidad creada: {}", guardada.getCveLocalidad());
        return exito(localidadMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatLocalidadResponse> actualizar(
            final String cveLocalidad, final CatLocalidadRequest request) {

        final Optional<CatLocalidad> opt = localidadRepository.findById(cveLocalidad);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.LOCALIDAD_NO_ENCONTRADA,
                    "Localidad no encontrada: " + cveLocalidad);
        }
        final CatLocalidad entidad = opt.get();
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
        if (request.getSatTownCode() != null) {
            entidad.setSatTownCode(request.getSatTownCode());
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
        return exito(localidadMapper.toResponse(localidadRepository.save(entidad)));
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
