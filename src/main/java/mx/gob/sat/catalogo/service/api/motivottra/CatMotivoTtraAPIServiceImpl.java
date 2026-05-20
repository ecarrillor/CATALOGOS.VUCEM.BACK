package mx.gob.sat.catalogo.service.api.motivottra;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.motivottra.CatMotivoTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.motivottra.CatMotivoTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatMotivoTtra;
import mx.gob.sat.catalogo.model.entity.CatTipoTramite;
import mx.gob.sat.catalogo.repository.CatMotivoTtraRepository;
import mx.gob.sat.catalogo.repository.CatTipoTramiteRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatMotivoTtraMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatMotivoTtraAPIServiceImpl implements CatMotivoTtraAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idMotivoTtra", "idMotivoTtra"
    );
    private static final String DEFAULT_SORT = "idMotivoTtra";

    private final CatMotivoTtraRepository motivoTtraRepository;
    private final CatTipoTramiteRepository tipoTramiteRepository;
    private final CatMotivoTtraMapper motivoTtraMapper;

    @Override
    public BaseResponse<PaginaResponse<CatMotivoTtraResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatMotivoTtra> paginaResultado = motivoTtraRepository.findAll(pageable);
        final List<CatMotivoTtraResponse> contenido = motivoTtraMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatMotivoTtraResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatMotivoTtraResponse> findById(final Long idMotivoTtra) {
        final Optional<CatMotivoTtra> resultado = motivoTtraRepository.findById(idMotivoTtra);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.MOTIVO_TTRA_NO_ENCONTRADO, "Motivo Ttra no encontrado: " + idMotivoTtra);
        }
        return exito(motivoTtraMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatMotivoTtraResponse> crear(final CatMotivoTtraRequest request) {
        final CatMotivoTtra entidad = new CatMotivoTtra();
        entidad.setIdMotivoTtra(request.getIdMotivoTtra());
        entidad.setIdeTipoMotivoTtra(request.getIdeTipoMotivoTtra());
        entidad.setDescMotivo(request.getDescMotivo());
        entidad.setDescContenidoMotivo(request.getDescContenidoMotivo());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        if (request.getIdTipoTramite() != null) {
            final Optional<CatTipoTramite> tipoTramite = tipoTramiteRepository.findById(request.getIdTipoTramite());
            tipoTramite.ifPresent(entidad::setIdTipoTramite);
        }
        final CatMotivoTtra guardado = motivoTtraRepository.save(entidad);
        log.info("Motivo Ttra creado: {}", guardado.getIdMotivoTtra());
        return exito(motivoTtraMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatMotivoTtraResponse> actualizar(final Long idMotivoTtra, final CatMotivoTtraRequest request) {
        final Optional<CatMotivoTtra> opt = motivoTtraRepository.findById(idMotivoTtra);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.MOTIVO_TTRA_NO_ENCONTRADO, "Motivo Ttra no encontrado: " + idMotivoTtra);
        }
        final CatMotivoTtra entidad = opt.get();
        if (request.getIdeTipoMotivoTtra() != null) { entidad.setIdeTipoMotivoTtra(request.getIdeTipoMotivoTtra()); }
        if (request.getDescMotivo() != null) { entidad.setDescMotivo(request.getDescMotivo()); }
        if (request.getDescContenidoMotivo() != null) { entidad.setDescContenidoMotivo(request.getDescContenidoMotivo()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getIdTipoTramite() != null) {
            final Optional<CatTipoTramite> tipoTramite = tipoTramiteRepository.findById(request.getIdTipoTramite());
            tipoTramite.ifPresent(entidad::setIdTipoTramite);
        }
        return exito(motivoTtraMapper.toResponse(motivoTtraRepository.save(entidad)));
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
