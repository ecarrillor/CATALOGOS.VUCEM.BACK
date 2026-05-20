package mx.gob.sat.catalogo.service.api.regimenttra;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.regimenttra.CatRegimenTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.regimenttra.CatRegimenTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatRegimen;
import mx.gob.sat.catalogo.model.entity.CatRegimenTtra;
import mx.gob.sat.catalogo.model.entity.CatTipoTramite;
import mx.gob.sat.catalogo.repository.CatRegimenRepository;
import mx.gob.sat.catalogo.repository.CatRegimenTtraRepository;
import mx.gob.sat.catalogo.repository.CatTipoTramiteRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatRegimenTtraMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatRegimenTtraAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de regimenes ttra.</p>
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
class CatRegimenTtraAPIServiceImpl implements CatRegimenTtraAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idRegimenTtra", "idRegimenTtra");
    private static final String DEFAULT_SORT = "idRegimenTtra";

    private final CatRegimenTtraRepository regimenTtraRepository;
    private final CatRegimenRepository regimenRepository;
    private final CatTipoTramiteRepository tipoTramiteRepository;
    private final CatRegimenTtraMapper regimenTtraMapper;

    @Override
    public BaseResponse<PaginaResponse<CatRegimenTtraResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatRegimenTtra> paginaResultado = regimenTtraRepository.findAll(pageable);
        final List<CatRegimenTtraResponse> contenido =
                regimenTtraMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatRegimenTtraResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatRegimenTtraResponse> findById(final Short idRegimenTtra) {
        final Optional<CatRegimenTtra> resultado = regimenTtraRepository.findById(idRegimenTtra);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.REGIMEN_TTRA_NO_ENCONTRADO,
                    "Regimen ttra no encontrado: " + idRegimenTtra);
        }
        return exito(regimenTtraMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatRegimenTtraResponse> crear(final CatRegimenTtraRequest request) {
        final CatRegimenTtra entidad = new CatRegimenTtra();
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());

        if (request.getCveRegimen() != null) {
            final Optional<CatRegimen> regimen = regimenRepository.findById(request.getCveRegimen());
            if (regimen.isEmpty()) {
                return error(CodigoErrorConst.REGIMEN_NO_ENCONTRADO,
                        "Regimen no encontrado: " + request.getCveRegimen());
            }
            entidad.setCveRegimen(regimen.get());
        }

        if (request.getIdTipoTramite() != null) {
            final Optional<CatTipoTramite> tipoTramite = tipoTramiteRepository.findById(request.getIdTipoTramite());
            if (tipoTramite.isEmpty()) {
                return error(CodigoErrorConst.TIPO_TRAMITE_NO_ENCONTRADO,
                        "Tipo de tramite no encontrado: " + request.getIdTipoTramite());
            }
            entidad.setIdTipoTramite(tipoTramite.get());
        }

        final CatRegimenTtra guardada = regimenTtraRepository.save(entidad);
        log.info("Regimen ttra creado: {}", guardada.getIdRegimenTtra());
        return exito(regimenTtraMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatRegimenTtraResponse> actualizar(
            final Short idRegimenTtra, final CatRegimenTtraRequest request) {
        final Optional<CatRegimenTtra> opt = regimenTtraRepository.findById(idRegimenTtra);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.REGIMEN_TTRA_NO_ENCONTRADO,
                    "Regimen ttra no encontrado: " + idRegimenTtra);
        }
        final CatRegimenTtra entidad = opt.get();
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }

        if (request.getCveRegimen() != null) {
            final Optional<CatRegimen> regimen = regimenRepository.findById(request.getCveRegimen());
            if (regimen.isEmpty()) {
                return error(CodigoErrorConst.REGIMEN_NO_ENCONTRADO,
                        "Regimen no encontrado: " + request.getCveRegimen());
            }
            entidad.setCveRegimen(regimen.get());
        }

        if (request.getIdTipoTramite() != null) {
            final Optional<CatTipoTramite> tipoTramite = tipoTramiteRepository.findById(request.getIdTipoTramite());
            if (tipoTramite.isEmpty()) {
                return error(CodigoErrorConst.TIPO_TRAMITE_NO_ENCONTRADO,
                        "Tipo de tramite no encontrado: " + request.getIdTipoTramite());
            }
            entidad.setIdTipoTramite(tipoTramite.get());
        }

        return exito(regimenTtraMapper.toResponse(regimenTtraRepository.save(entidad)));
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
