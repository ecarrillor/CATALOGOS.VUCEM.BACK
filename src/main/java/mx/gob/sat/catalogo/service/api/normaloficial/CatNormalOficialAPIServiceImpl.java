package mx.gob.sat.catalogo.service.api.normaloficial;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.normaloficial.CatNormalOficialRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.normaloficial.CatNormalOficialResponse;
import mx.gob.sat.catalogo.model.entity.CatNormalOficial;
import mx.gob.sat.catalogo.model.entity.CatPais;
import mx.gob.sat.catalogo.repository.CatNormalOficialRepository;
import mx.gob.sat.catalogo.repository.CatPaisRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatNormalOficialMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatNormalOficialAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de normas oficiales.</p>
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
class CatNormalOficialAPIServiceImpl implements CatNormalOficialAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idNormalOficial", "idNormalOficial",
            "claveNorma", "claveNorma");

    private static final String DEFAULT_SORT = "idNormalOficial";

    private final CatNormalOficialRepository normalOficialRepository;
    private final CatPaisRepository paisRepository;
    private final CatNormalOficialMapper normalOficialMapper;

    @Override
    public BaseResponse<PaginaResponse<CatNormalOficialResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatNormalOficial> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? normalOficialRepository.findAll(pageable)
                : normalOficialRepository.findByClaveNormaContainingIgnoreCaseOrDescNormaContainingIgnoreCase(
                        busqueda, busqueda, pageable);

        final List<CatNormalOficialResponse> contenido =
                normalOficialMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatNormalOficialResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatNormalOficialResponse> findById(final Integer idNormalOficial) {
        final Optional<CatNormalOficial> resultado =
                normalOficialRepository.findById(idNormalOficial);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.NORMAL_OFICIAL_NO_ENCONTRADO,
                    "Norma oficial no encontrada: " + idNormalOficial);
        }
        return exito(normalOficialMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatNormalOficialResponse> crear(final CatNormalOficialRequest request) {
        final CatNormalOficial entidad = new CatNormalOficial();
        entidad.setClaveNorma(request.getClaveNorma());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        entidad.setDescNorma(request.getDescNorma());
        entidad.setFecPublicacion(request.getFecPublicacion());
        entidad.setFecEntradaVigor(request.getFecEntradaVigor());
        entidad.setIdeClasifNorma(request.getIdeClasifNorma());
        entidad.setBlnLoteEstructurado(request.getBlnLoteEstructurado());

        if (request.getCvePais() != null) {
            final Optional<CatPais> pais = paisRepository.findById(request.getCvePais());
            if (pais.isEmpty()) {
                return error(CodigoErrorConst.PAIS_NO_ENCONTRADO,
                        "Pais no encontrado: " + request.getCvePais());
            }
            entidad.setCvePais(pais.get());
        }

        if (request.getIdNormaOficialR() != null) {
            final Optional<CatNormalOficial> normaR =
                    normalOficialRepository.findById(request.getIdNormaOficialR());
            if (normaR.isEmpty()) {
                return error(CodigoErrorConst.NORMAL_OFICIAL_NO_ENCONTRADO,
                        "Norma oficial referenciada no encontrada: " + request.getIdNormaOficialR());
            }
            entidad.setIdNormaOficialR(normaR.get());
        }

        final CatNormalOficial guardada = normalOficialRepository.save(entidad);
        log.info("Norma oficial creada: {}", guardada.getIdNormalOficial());
        return exito(normalOficialMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatNormalOficialResponse> actualizar(
            final Integer idNormalOficial, final CatNormalOficialRequest request) {

        final Optional<CatNormalOficial> opt = normalOficialRepository.findById(idNormalOficial);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.NORMAL_OFICIAL_NO_ENCONTRADO,
                    "Norma oficial no encontrada: " + idNormalOficial);
        }
        final CatNormalOficial entidad = opt.get();
        if (request.getClaveNorma() != null) {
            entidad.setClaveNorma(request.getClaveNorma());
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
        if (request.getDescNorma() != null) {
            entidad.setDescNorma(request.getDescNorma());
        }
        if (request.getFecPublicacion() != null) {
            entidad.setFecPublicacion(request.getFecPublicacion());
        }
        if (request.getFecEntradaVigor() != null) {
            entidad.setFecEntradaVigor(request.getFecEntradaVigor());
        }
        if (request.getIdeClasifNorma() != null) {
            entidad.setIdeClasifNorma(request.getIdeClasifNorma());
        }
        if (request.getBlnLoteEstructurado() != null) {
            entidad.setBlnLoteEstructurado(request.getBlnLoteEstructurado());
        }
        if (request.getCvePais() != null) {
            final Optional<CatPais> pais = paisRepository.findById(request.getCvePais());
            if (pais.isEmpty()) {
                return error(CodigoErrorConst.PAIS_NO_ENCONTRADO,
                        "Pais no encontrado: " + request.getCvePais());
            }
            entidad.setCvePais(pais.get());
        }
        if (request.getIdNormaOficialR() != null) {
            final Optional<CatNormalOficial> normaR =
                    normalOficialRepository.findById(request.getIdNormaOficialR());
            if (normaR.isEmpty()) {
                return error(CodigoErrorConst.NORMAL_OFICIAL_NO_ENCONTRADO,
                        "Norma oficial referenciada no encontrada: " + request.getIdNormaOficialR());
            }
            entidad.setIdNormaOficialR(normaR.get());
        }
        return exito(normalOficialMapper.toResponse(normalOficialRepository.save(entidad)));
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
