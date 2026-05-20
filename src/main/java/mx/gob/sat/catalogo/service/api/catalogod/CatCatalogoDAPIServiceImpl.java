package mx.gob.sat.catalogo.service.api.catalogod;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.catalogod.CatCatalogoDRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.catalogod.CatCatalogoDResponse;
import mx.gob.sat.catalogo.model.entity.CatCatalogoD;
import mx.gob.sat.catalogo.model.entity.CatCatalogoH;
import mx.gob.sat.catalogo.repository.CatCatalogoDRepository;
import mx.gob.sat.catalogo.repository.CatCatalogoHRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatCatalogoDMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatCatalogoDAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de catalogos D (detalle).</p>
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
class CatCatalogoDAPIServiceImpl implements CatCatalogoDAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveCatalogo", "cveCatalogo",
            "descGenerica1", "descGenerica1");

    private static final String DEFAULT_SORT = "cveCatalogo";

    private final CatCatalogoDRepository catalogoDRepository;
    private final CatCatalogoHRepository catalogoHRepository;
    private final CatCatalogoDMapper catalogoDMapper;

    @Override
    public BaseResponse<PaginaResponse<CatCatalogoDResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatCatalogoD> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? catalogoDRepository.findAll(pageable)
                : catalogoDRepository.findByDescGenerica1ContainingIgnoreCaseOrCveCatalogoContainingIgnoreCase(
                        busqueda, busqueda, pageable);

        final List<CatCatalogoDResponse> contenido =
                catalogoDMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatCatalogoDResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatCatalogoDResponse> findById(final String cveCatalogo) {
        final Optional<CatCatalogoD> resultado = catalogoDRepository.findById(cveCatalogo);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.CATALOGO_D_NO_ENCONTRADO,
                    "Catalogo D no encontrado: " + cveCatalogo);
        }
        return exito(catalogoDMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatCatalogoDResponse> crear(final CatCatalogoDRequest request) {
        final CatCatalogoD entidad = new CatCatalogoD();
        entidad.setCveCatalogo(request.getCveCatalogo());
        entidad.setCodigoGenerico1(request.getCodigoGenerico1());
        entidad.setCodigoGenerico2(request.getCodigoGenerico2());
        entidad.setDescGenerica1(request.getDescGenerica1());
        entidad.setDescGenerica2(request.getDescGenerica2());
        entidad.setNumGenerico1(request.getNumGenerico1());
        entidad.setNumGenerico2(request.getNumGenerico2());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());

        final Optional<CatCatalogoH> catalogoH = catalogoHRepository.findById(request.getCveCatalogoH());
        if (catalogoH.isEmpty()) {
            return error(CodigoErrorConst.CATALOGO_H_NO_ENCONTRADO,
                    "Catalogo H no encontrado: " + request.getCveCatalogoH());
        }
        entidad.setCveCatalogoH(catalogoH.get());

        if (request.getCveCatalogoR() != null) {
            final Optional<CatCatalogoD> catalogoR = catalogoDRepository.findById(request.getCveCatalogoR());
            if (catalogoR.isEmpty()) {
                return error(CodigoErrorConst.CATALOGO_D_NO_ENCONTRADO,
                        "Catalogo D referenciado no encontrado: " + request.getCveCatalogoR());
            }
            entidad.setCveCatalogoR(catalogoR.get());
        }

        final CatCatalogoD guardado = catalogoDRepository.save(entidad);
        log.info("Catalogo D creado: {}", guardado.getCveCatalogo());
        return exito(catalogoDMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatCatalogoDResponse> actualizar(
            final String cveCatalogo, final CatCatalogoDRequest request) {

        final Optional<CatCatalogoD> opt = catalogoDRepository.findById(cveCatalogo);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.CATALOGO_D_NO_ENCONTRADO,
                    "Catalogo D no encontrado: " + cveCatalogo);
        }
        final CatCatalogoD entidad = opt.get();
        if (request.getCodigoGenerico1() != null) {
            entidad.setCodigoGenerico1(request.getCodigoGenerico1());
        }
        if (request.getCodigoGenerico2() != null) {
            entidad.setCodigoGenerico2(request.getCodigoGenerico2());
        }
        if (request.getDescGenerica1() != null) {
            entidad.setDescGenerica1(request.getDescGenerica1());
        }
        if (request.getDescGenerica2() != null) {
            entidad.setDescGenerica2(request.getDescGenerica2());
        }
        if (request.getNumGenerico1() != null) {
            entidad.setNumGenerico1(request.getNumGenerico1());
        }
        if (request.getNumGenerico2() != null) {
            entidad.setNumGenerico2(request.getNumGenerico2());
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
        if (request.getCveCatalogoH() != null) {
            final Optional<CatCatalogoH> catalogoH = catalogoHRepository.findById(request.getCveCatalogoH());
            if (catalogoH.isEmpty()) {
                return error(CodigoErrorConst.CATALOGO_H_NO_ENCONTRADO,
                        "Catalogo H no encontrado: " + request.getCveCatalogoH());
            }
            entidad.setCveCatalogoH(catalogoH.get());
        }
        if (request.getCveCatalogoR() != null) {
            final Optional<CatCatalogoD> catalogoR = catalogoDRepository.findById(request.getCveCatalogoR());
            if (catalogoR.isEmpty()) {
                return error(CodigoErrorConst.CATALOGO_D_NO_ENCONTRADO,
                        "Catalogo D referenciado no encontrado: " + request.getCveCatalogoR());
            }
            entidad.setCveCatalogoR(catalogoR.get());
        }
        return exito(catalogoDMapper.toResponse(catalogoDRepository.save(entidad)));
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
