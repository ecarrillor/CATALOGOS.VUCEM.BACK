package mx.gob.sat.catalogo.service.api.delegmun;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.delegmun.CatDelegMunRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.delegmun.CatDelegMunResponse;
import mx.gob.sat.catalogo.model.entity.CatDelegMun;
import mx.gob.sat.catalogo.model.entity.CatEntidad;
import mx.gob.sat.catalogo.repository.CatDelegMunRepository;
import mx.gob.sat.catalogo.repository.CatEntidadRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatDelegMunMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatDelegMunAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de delegaciones y municipios.</p>
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
class CatDelegMunAPIServiceImpl implements CatDelegMunAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveDelegMun", "cveDelegMun",
            "nombre", "nombre");

    private static final String DEFAULT_SORT = "cveDelegMun";

    private final CatDelegMunRepository delegMunRepository;
    private final CatEntidadRepository entidadRepository;
    private final CatDelegMunMapper delegMunMapper;

    @Override
    public BaseResponse<PaginaResponse<CatDelegMunResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        Boolean activo = null;
        String texto = null;

        if (busqueda != null && !busqueda.isBlank()) {
            final String termino = busqueda.trim().toLowerCase();
            if ("activo".equals(termino)) {
                activo = Boolean.TRUE;
            } else if ("inactivo".equals(termino)) {
                activo = Boolean.FALSE;
            } else {
                texto = "%" + termino + "%";
            }
        }

        final Page<CatDelegMun> paginaResultado =
                delegMunRepository.search(texto, activo, pageable);

        final List<CatDelegMunResponse> contenido =
                delegMunMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatDelegMunResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatDelegMunResponse> findById(final String cveDelegMun) {
        final Optional<CatDelegMun> resultado = delegMunRepository.findById(cveDelegMun);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.DELEG_MUN_NO_ENCONTRADA,
                    "Delegacion/municipio no encontrado: " + cveDelegMun);
        }
        return exito(delegMunMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatDelegMunResponse> crear(final CatDelegMunRequest request) {
        final CatDelegMun entidad = new CatDelegMun();
        entidad.setCveDelegMun(request.getCveDelegMun());
        entidad.setNombre(request.getNombre());
        entidad.setFecCaptura(request.getFecCaptura());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setSatMunicipality(request.getSatMunicipality());
        entidad.setBlnActivo(request.getBlnActivo());

        if (request.getCveEntidad() != null) {
            final Optional<CatEntidad> entidad2 = entidadRepository.findById(request.getCveEntidad());
            if (entidad2.isEmpty()) {
                return error(CodigoErrorConst.ENTIDAD_NO_ENCONTRADA,
                        "Entidad no encontrada: " + request.getCveEntidad());
            }
            entidad.setCveEntidad(entidad2.get());
        }

        final CatDelegMun guardado = delegMunRepository.save(entidad);
        log.info("Delegacion/municipio creado: {}", guardado.getCveDelegMun());
        return exito(delegMunMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatDelegMunResponse> actualizar(
            final String cveDelegMun, final CatDelegMunRequest request) {

        final Optional<CatDelegMun> opt = delegMunRepository.findById(cveDelegMun);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.DELEG_MUN_NO_ENCONTRADA,
                    "Delegacion/municipio no encontrado: " + cveDelegMun);
        }
        final CatDelegMun entidad = opt.get();
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
        if (request.getSatMunicipality() != null) {
            entidad.setSatMunicipality(request.getSatMunicipality());
        }
        if (request.getBlnActivo() != null) {
            entidad.setBlnActivo(request.getBlnActivo());
        }
        if (request.getCveEntidad() != null) {
            final Optional<CatEntidad> entidad2 = entidadRepository.findById(request.getCveEntidad());
            if (entidad2.isEmpty()) {
                return error(CodigoErrorConst.ENTIDAD_NO_ENCONTRADA,
                        "Entidad no encontrada: " + request.getCveEntidad());
            }
            entidad.setCveEntidad(entidad2.get());
        }
        return exito(delegMunMapper.toResponse(delegMunRepository.save(entidad)));
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
