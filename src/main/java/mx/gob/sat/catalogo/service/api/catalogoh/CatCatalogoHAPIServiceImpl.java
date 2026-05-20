package mx.gob.sat.catalogo.service.api.catalogoh;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.catalogoh.CatCatalogoHRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.catalogoh.CatCatalogoHResponse;
import mx.gob.sat.catalogo.model.entity.CatCatalogoH;
import mx.gob.sat.catalogo.repository.CatCatalogoHRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatCatalogoHMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatCatalogoHAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de catalogos H (cabecera).</p>
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
class CatCatalogoHAPIServiceImpl implements CatCatalogoHAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveCatalogoH", "cveCatalogoH",
            "nomCatalogo", "nomCatalogo");

    private static final String DEFAULT_SORT = "cveCatalogoH";

    private final CatCatalogoHRepository catalogoHRepository;
    private final CatCatalogoHMapper catalogoHMapper;

    @Override
    public BaseResponse<PaginaResponse<CatCatalogoHResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatCatalogoH> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? catalogoHRepository.findAll(pageable)
                : catalogoHRepository.findByNomCatalogoContainingIgnoreCaseOrCveCatalogoHContainingIgnoreCase(
                        busqueda, busqueda, pageable);

        final List<CatCatalogoHResponse> contenido =
                catalogoHMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatCatalogoHResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatCatalogoHResponse> findById(final String cveCatalogoH) {
        final Optional<CatCatalogoH> resultado = catalogoHRepository.findById(cveCatalogoH);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.CATALOGO_H_NO_ENCONTRADO,
                    "Catalogo H no encontrado: " + cveCatalogoH);
        }
        return exito(catalogoHMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatCatalogoHResponse> crear(final CatCatalogoHRequest request) {
        final CatCatalogoH guardado = catalogoHRepository.save(catalogoHMapper.toEntity(request));
        log.info("Catalogo H creado: {}", guardado.getCveCatalogoH());
        return exito(catalogoHMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatCatalogoHResponse> actualizar(
            final String cveCatalogoH, final CatCatalogoHRequest request) {

        final Optional<CatCatalogoH> opt = catalogoHRepository.findById(cveCatalogoH);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.CATALOGO_H_NO_ENCONTRADO,
                    "Catalogo H no encontrado: " + cveCatalogoH);
        }
        final CatCatalogoH entidad = opt.get();
        if (request.getNomCatalogo() != null) {
            entidad.setNomCatalogo(request.getNomCatalogo());
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
        return exito(catalogoHMapper.toResponse(catalogoHRepository.save(entidad)));
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
