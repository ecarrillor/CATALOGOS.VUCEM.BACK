package mx.gob.sat.catalogo.service.api.tipoproductottra;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.tipoproductottra.CatTipoProductoTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tipoproductottra.CatTipoProductoTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatTipoProductoTtra;
import mx.gob.sat.catalogo.model.entity.CatTipoTramite;
import mx.gob.sat.catalogo.repository.CatTipoProductoTtraRepository;
import mx.gob.sat.catalogo.repository.CatTipoTramiteRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatTipoProductoTtraMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatTipoProductoTtraAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de tipos de producto ttra.</p>
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
class CatTipoProductoTtraAPIServiceImpl implements CatTipoProductoTtraAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idTipoProductoTtra", "idTipoProductoTtra",
            "descTipoProducto", "descTipoProducto");
    private static final String DEFAULT_SORT = "idTipoProductoTtra";

    private final CatTipoProductoTtraRepository tipoProductoTtraRepository;
    private final CatTipoTramiteRepository tipoTramiteRepository;
    private final CatTipoProductoTtraMapper tipoProductoTtraMapper;

    @Override
    public BaseResponse<PaginaResponse<CatTipoProductoTtraResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatTipoProductoTtra> paginaResultado = tipoProductoTtraRepository.findAll(pageable);
        final List<CatTipoProductoTtraResponse> contenido =
                tipoProductoTtraMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatTipoProductoTtraResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatTipoProductoTtraResponse> findById(final Short idTipoProductoTtra) {
        final Optional<CatTipoProductoTtra> resultado =
                tipoProductoTtraRepository.findById(idTipoProductoTtra);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.TIPO_PRODUCTO_TTRA_NO_ENCONTRADO,
                    "Tipo de producto ttra no encontrado: " + idTipoProductoTtra);
        }
        return exito(tipoProductoTtraMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatTipoProductoTtraResponse> crear(final CatTipoProductoTtraRequest request) {
        final CatTipoProductoTtra entidad = new CatTipoProductoTtra();
        entidad.setDescTipoProducto(request.getDescTipoProducto());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        entidad.setIdeTipoCertificadoMerc(request.getIdeTipoCertificadoMerc());

        if (request.getIdTipoTramite() != null) {
            final Optional<CatTipoTramite> tipoTramite =
                    tipoTramiteRepository.findById(request.getIdTipoTramite());
            if (tipoTramite.isEmpty()) {
                return error(CodigoErrorConst.TIPO_TRAMITE_NO_ENCONTRADO,
                        "Tipo de tramite no encontrado: " + request.getIdTipoTramite());
            }
            entidad.setIdTipoTramite(tipoTramite.get());
        }

        final CatTipoProductoTtra guardada = tipoProductoTtraRepository.save(entidad);
        log.info("Tipo de producto ttra creado: {}", guardada.getIdTipoProductoTtra());
        return exito(tipoProductoTtraMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatTipoProductoTtraResponse> actualizar(
            final Short idTipoProductoTtra, final CatTipoProductoTtraRequest request) {
        final Optional<CatTipoProductoTtra> opt =
                tipoProductoTtraRepository.findById(idTipoProductoTtra);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.TIPO_PRODUCTO_TTRA_NO_ENCONTRADO,
                    "Tipo de producto ttra no encontrado: " + idTipoProductoTtra);
        }
        final CatTipoProductoTtra entidad = opt.get();
        if (request.getDescTipoProducto() != null) { entidad.setDescTipoProducto(request.getDescTipoProducto()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getIdeTipoCertificadoMerc() != null) { entidad.setIdeTipoCertificadoMerc(request.getIdeTipoCertificadoMerc()); }
        if (request.getIdTipoTramite() != null) {
            final Optional<CatTipoTramite> tipoTramite =
                    tipoTramiteRepository.findById(request.getIdTipoTramite());
            if (tipoTramite.isEmpty()) {
                return error(CodigoErrorConst.TIPO_TRAMITE_NO_ENCONTRADO,
                        "Tipo de tramite no encontrado: " + request.getIdTipoTramite());
            }
            entidad.setIdTipoTramite(tipoTramite.get());
        }
        return exito(tipoProductoTtraMapper.toResponse(tipoProductoTtraRepository.save(entidad)));
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
