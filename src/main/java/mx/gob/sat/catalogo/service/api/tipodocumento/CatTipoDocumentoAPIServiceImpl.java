package mx.gob.sat.catalogo.service.api.tipodocumento;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.tipodocumento.CatTipoDocumentoRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tipodocumento.CatTipoDocumentoResponse;
import mx.gob.sat.catalogo.model.entity.CatTipoDocumento;
import mx.gob.sat.catalogo.repository.CatTipoDocumentoRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatTipoDocumentoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatTipoDocumentoAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de tipos de documento.</p>
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
class CatTipoDocumentoAPIServiceImpl implements CatTipoDocumentoAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idTipoDocumento", "idTipoDocumento",
            "nombre", "nombre");

    private static final String DEFAULT_SORT = "idTipoDocumento";

    private final CatTipoDocumentoRepository tipoDocumentoRepository;
    private final CatTipoDocumentoMapper tipoDocumentoMapper;

    @Override
    public BaseResponse<PaginaResponse<CatTipoDocumentoResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatTipoDocumento> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? tipoDocumentoRepository.findAll(pageable)
                : tipoDocumentoRepository.findByNombreContainingIgnoreCase(busqueda, pageable);

        final List<CatTipoDocumentoResponse> contenido =
                tipoDocumentoMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatTipoDocumentoResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatTipoDocumentoResponse> findById(final Short idTipoDocumento) {
        final Optional<CatTipoDocumento> resultado = tipoDocumentoRepository.findById(idTipoDocumento);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.TIPO_DOCUMENTO_NO_ENCONTRADO,
                    "Tipo documento no encontrado: " + idTipoDocumento);
        }
        return exito(tipoDocumentoMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatTipoDocumentoResponse> crear(final CatTipoDocumentoRequest request) {
        final CatTipoDocumento guardado = tipoDocumentoRepository.save(tipoDocumentoMapper.toEntity(request));
        log.info("Tipo documento creado: {}", guardado.getIdTipoDocumento());
        return exito(tipoDocumentoMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatTipoDocumentoResponse> actualizar(
            final Short idTipoDocumento, final CatTipoDocumentoRequest request) {

        final Optional<CatTipoDocumento> opt = tipoDocumentoRepository.findById(idTipoDocumento);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.TIPO_DOCUMENTO_NO_ENCONTRADO,
                    "Tipo documento no encontrado: " + idTipoDocumento);
        }
        final CatTipoDocumento entidad = opt.get();
        if (request.getNombre() != null) {
            entidad.setNombre(request.getNombre());
        }
        if (request.getIdeRangoResolucionImagen() != null) {
            entidad.setIdeRangoResolucionImagen(request.getIdeRangoResolucionImagen());
        }
        if (request.getTamanioMaximo() != null) {
            entidad.setTamanioMaximo(request.getTamanioMaximo());
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
        return exito(tipoDocumentoMapper.toResponse(tipoDocumentoRepository.save(entidad)));
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
