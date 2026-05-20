package mx.gob.sat.catalogo.service.api.tipocertificado;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.tipocertificado.CatTipoCertificadoRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tipocertificado.CatTipoCertificadoResponse;
import mx.gob.sat.catalogo.model.entity.CatTipoCertificado;
import mx.gob.sat.catalogo.repository.CatTipoCertificadoRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatTipoCertificadoMapper;
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
class CatTipoCertificadoAPIServiceImpl implements CatTipoCertificadoAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idTipoCertificado", "idTipoCertificado",
            "cveCatalogo", "cveCatalogo"
    );
    private static final String DEFAULT_SORT = "idTipoCertificado";

    private final CatTipoCertificadoRepository tipoCertificadoRepository;
    private final CatTipoCertificadoMapper tipoCertificadoMapper;

    @Override
    public BaseResponse<PaginaResponse<CatTipoCertificadoResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatTipoCertificado> paginaResultado = tipoCertificadoRepository.findAll(pageable);
        final List<CatTipoCertificadoResponse> contenido = tipoCertificadoMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatTipoCertificadoResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatTipoCertificadoResponse> findById(final Long idTipoCertificado) {
        final Optional<CatTipoCertificado> resultado = tipoCertificadoRepository.findById(idTipoCertificado);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.TIPO_CERTIFICADO_NO_ENCONTRADO, "Tipo Certificado no encontrado: " + idTipoCertificado);
        }
        return exito(tipoCertificadoMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatTipoCertificadoResponse> crear(final CatTipoCertificadoRequest request) {
        final CatTipoCertificado entidad = new CatTipoCertificado();
        entidad.setIdTipoCertificado(request.getIdTipoCertificado());
        entidad.setCveCatalogo(request.getCveCatalogo());
        entidad.setDescripcion(request.getDescripcion());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        entidad.setBlnRfc(request.getBlnRfc());
        final CatTipoCertificado guardado = tipoCertificadoRepository.save(entidad);
        log.info("Tipo Certificado creado: {}", guardado.getIdTipoCertificado());
        return exito(tipoCertificadoMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatTipoCertificadoResponse> actualizar(final Long idTipoCertificado, final CatTipoCertificadoRequest request) {
        final Optional<CatTipoCertificado> opt = tipoCertificadoRepository.findById(idTipoCertificado);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.TIPO_CERTIFICADO_NO_ENCONTRADO, "Tipo Certificado no encontrado: " + idTipoCertificado);
        }
        final CatTipoCertificado entidad = opt.get();
        if (request.getCveCatalogo() != null) { entidad.setCveCatalogo(request.getCveCatalogo()); }
        if (request.getDescripcion() != null) { entidad.setDescripcion(request.getDescripcion()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getBlnRfc() != null) { entidad.setBlnRfc(request.getBlnRfc()); }
        return exito(tipoCertificadoMapper.toResponse(tipoCertificadoRepository.save(entidad)));
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
