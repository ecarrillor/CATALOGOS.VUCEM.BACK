package mx.gob.sat.catalogo.service.api.leyendatexto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.leyendatexto.CatLeyendaTextoRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.leyendatexto.CatLeyendaTextoResponse;
import mx.gob.sat.catalogo.model.entity.CatLeyendaTexto;
import mx.gob.sat.catalogo.repository.CatLeyendaTextoRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatLeyendaTextoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatLeyendaTextoAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de leyendas de texto.</p>
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
class CatLeyendaTextoAPIServiceImpl implements CatLeyendaTextoAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idLeyendaTexto", "idLeyendaTexto",
            "ideTipoLeyendaTexto", "ideTipoLeyendaTexto",
            "leyenda", "leyenda");

    private static final String DEFAULT_SORT = "idLeyendaTexto";

    private final CatLeyendaTextoRepository leyendaTextoRepository;
    private final CatLeyendaTextoMapper leyendaTextoMapper;

    @Override
    public BaseResponse<PaginaResponse<CatLeyendaTextoResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatLeyendaTexto> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? leyendaTextoRepository.findAll(pageable)
                : leyendaTextoRepository.findByLeyendaContainingIgnoreCaseOrIdeTipoLeyendaTextoContainingIgnoreCase(
                        busqueda, busqueda, pageable);

        final List<CatLeyendaTextoResponse> contenido =
                leyendaTextoMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatLeyendaTextoResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatLeyendaTextoResponse> findById(final Long idLeyendaTexto) {
        final Optional<CatLeyendaTexto> resultado = leyendaTextoRepository.findById(idLeyendaTexto);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.LEYENDA_TEXTO_NO_ENCONTRADA,
                    "Leyenda texto no encontrada: " + idLeyendaTexto);
        }
        return exito(leyendaTextoMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatLeyendaTextoResponse> crear(final CatLeyendaTextoRequest request) {
        final CatLeyendaTexto guardada = leyendaTextoRepository.save(leyendaTextoMapper.toEntity(request));
        log.info("Leyenda texto creada: {}", guardada.getIdLeyendaTexto());
        return exito(leyendaTextoMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatLeyendaTextoResponse> actualizar(
            final Long idLeyendaTexto, final CatLeyendaTextoRequest request) {

        final Optional<CatLeyendaTexto> opt = leyendaTextoRepository.findById(idLeyendaTexto);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.LEYENDA_TEXTO_NO_ENCONTRADA,
                    "Leyenda texto no encontrada: " + idLeyendaTexto);
        }
        final CatLeyendaTexto entidad = opt.get();
        if (request.getIdeTipoLeyendaTexto() != null) {
            entidad.setIdeTipoLeyendaTexto(request.getIdeTipoLeyendaTexto());
        }
        if (request.getNumAnio() != null) {
            entidad.setNumAnio(request.getNumAnio());
        }
        if (request.getLeyenda() != null) {
            entidad.setLeyenda(request.getLeyenda());
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
        return exito(leyendaTextoMapper.toResponse(leyendaTextoRepository.save(entidad)));
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
