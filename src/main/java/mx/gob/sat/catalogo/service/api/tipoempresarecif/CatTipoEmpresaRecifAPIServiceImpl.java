package mx.gob.sat.catalogo.service.api.tipoempresarecif;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.tipoempresarecif.CatTipoEmpresaRecifRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tipoempresarecif.CatTipoEmpresaRecifResponse;
import mx.gob.sat.catalogo.model.entity.CatTipoEmpresaRecif;
import mx.gob.sat.catalogo.repository.CatTipoEmpresaRecifRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatTipoEmpresaRecifMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatTipoEmpresaRecifAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de tipos de empresa RECIF.</p>
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
class CatTipoEmpresaRecifAPIServiceImpl implements CatTipoEmpresaRecifAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveTipoEmpresaRecif", "cveTipoEmpresaRecif",
            "descripcion", "descripcion");

    private static final String DEFAULT_SORT = "cveTipoEmpresaRecif";

    private final CatTipoEmpresaRecifRepository tipoEmpresaRecifRepository;
    private final CatTipoEmpresaRecifMapper tipoEmpresaRecifMapper;

    @Override
    public BaseResponse<PaginaResponse<CatTipoEmpresaRecifResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatTipoEmpresaRecif> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? tipoEmpresaRecifRepository.findAll(pageable)
                : tipoEmpresaRecifRepository
                        .findByDescripcionContainingIgnoreCaseOrCveTipoEmpresaRecifContainingIgnoreCase(
                                busqueda, busqueda, pageable);

        final List<CatTipoEmpresaRecifResponse> contenido =
                tipoEmpresaRecifMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatTipoEmpresaRecifResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatTipoEmpresaRecifResponse> findById(final String cveTipoEmpresaRecif) {
        final Optional<CatTipoEmpresaRecif> resultado =
                tipoEmpresaRecifRepository.findById(cveTipoEmpresaRecif);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.TIPO_EMPRESA_RECIF_NO_ENCONTRADO,
                    "Tipo empresa RECIF no encontrado: " + cveTipoEmpresaRecif);
        }
        return exito(tipoEmpresaRecifMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatTipoEmpresaRecifResponse> crear(final CatTipoEmpresaRecifRequest request) {
        final CatTipoEmpresaRecif entidad = new CatTipoEmpresaRecif();
        entidad.setCveTipoEmpresaRecif(request.getCveTipoEmpresaRecif());
        entidad.setDescripcion(request.getDescripcion());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());

        if (request.getCveTipoEmpresaRecifR() != null) {
            final Optional<CatTipoEmpresaRecif> recifR =
                    tipoEmpresaRecifRepository.findById(request.getCveTipoEmpresaRecifR());
            if (recifR.isEmpty()) {
                return error(CodigoErrorConst.TIPO_EMPRESA_RECIF_NO_ENCONTRADO,
                        "Tipo empresa RECIF referenciado no encontrado: " + request.getCveTipoEmpresaRecifR());
            }
            entidad.setCveTipoEmpresaRecifR(recifR.get());
        }

        final CatTipoEmpresaRecif guardado = tipoEmpresaRecifRepository.save(entidad);
        log.info("Tipo empresa RECIF creado: {}", guardado.getCveTipoEmpresaRecif());
        return exito(tipoEmpresaRecifMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatTipoEmpresaRecifResponse> actualizar(
            final String cveTipoEmpresaRecif, final CatTipoEmpresaRecifRequest request) {

        final Optional<CatTipoEmpresaRecif> opt =
                tipoEmpresaRecifRepository.findById(cveTipoEmpresaRecif);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.TIPO_EMPRESA_RECIF_NO_ENCONTRADO,
                    "Tipo empresa RECIF no encontrado: " + cveTipoEmpresaRecif);
        }
        final CatTipoEmpresaRecif entidad = opt.get();
        if (request.getDescripcion() != null) {
            entidad.setDescripcion(request.getDescripcion());
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
        if (request.getCveTipoEmpresaRecifR() != null) {
            final Optional<CatTipoEmpresaRecif> recifR =
                    tipoEmpresaRecifRepository.findById(request.getCveTipoEmpresaRecifR());
            if (recifR.isEmpty()) {
                return error(CodigoErrorConst.TIPO_EMPRESA_RECIF_NO_ENCONTRADO,
                        "Tipo empresa RECIF referenciado no encontrado: " + request.getCveTipoEmpresaRecifR());
            }
            entidad.setCveTipoEmpresaRecifR(recifR.get());
        }
        return exito(tipoEmpresaRecifMapper.toResponse(tipoEmpresaRecifRepository.save(entidad)));
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
