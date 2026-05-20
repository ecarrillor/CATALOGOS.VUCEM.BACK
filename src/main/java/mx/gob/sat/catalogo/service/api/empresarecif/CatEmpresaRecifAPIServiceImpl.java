package mx.gob.sat.catalogo.service.api.empresarecif;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.empresarecif.CatEmpresaRecifRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.empresarecif.CatEmpresaRecifResponse;
import mx.gob.sat.catalogo.model.entity.CatEmpresaRecif;
import mx.gob.sat.catalogo.model.entity.CatUnidadAdministrativa;
import mx.gob.sat.catalogo.repository.CatEmpresaRecifRepository;
import mx.gob.sat.catalogo.repository.CatUnidadAdministrativaRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatEmpresaRecifMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatEmpresaRecifAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de empresas RECIF.</p>
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
class CatEmpresaRecifAPIServiceImpl implements CatEmpresaRecifAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "recif", "recif",
            "razonSocial", "razonSocial");

    private static final String DEFAULT_SORT = "recif";

    private final CatEmpresaRecifRepository empresaRecifRepository;
    private final CatUnidadAdministrativaRepository unidadAdministrativaRepository;
    private final CatEmpresaRecifMapper empresaRecifMapper;

    @Override
    public BaseResponse<PaginaResponse<CatEmpresaRecifResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatEmpresaRecif> paginaResultado = empresaRecifRepository.findAll(pageable);

        final List<CatEmpresaRecifResponse> contenido =
                empresaRecifMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatEmpresaRecifResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatEmpresaRecifResponse> findById(final String recif) {
        final Optional<CatEmpresaRecif> resultado = empresaRecifRepository.findById(recif);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.EMPRESA_RECIF_NO_ENCONTRADA,
                    "Empresa RECIF no encontrada: " + recif);
        }
        return exito(empresaRecifMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatEmpresaRecifResponse> crear(final CatEmpresaRecifRequest request) {
        final CatEmpresaRecif entidad = new CatEmpresaRecif();
        entidad.setRecif(request.getRecif());
        entidad.setRfc(request.getRfc());
        entidad.setRazonSocial(request.getRazonSocial());
        entidad.setBlnActivo(request.getBlnActivo());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());

        if (request.getCveUnidadAdministrativa() != null) {
            final Optional<CatUnidadAdministrativa> unidadAdministrativa =
                    unidadAdministrativaRepository.findById(request.getCveUnidadAdministrativa());
            if (unidadAdministrativa.isEmpty()) {
                return error(CodigoErrorConst.UNIDAD_ADMINISTRATIVA_NO_ENCONTRADA,
                        "Unidad administrativa no encontrada: " + request.getCveUnidadAdministrativa());
            }
            entidad.setCveUnidadAdministrativa(unidadAdministrativa.get());
        }

        final CatEmpresaRecif guardada = empresaRecifRepository.save(entidad);
        log.info("Empresa RECIF creada: {}", guardada.getRecif());
        return exito(empresaRecifMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatEmpresaRecifResponse> actualizar(
            final String recif, final CatEmpresaRecifRequest request) {

        final Optional<CatEmpresaRecif> opt = empresaRecifRepository.findById(recif);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.EMPRESA_RECIF_NO_ENCONTRADA,
                    "Empresa RECIF no encontrada: " + recif);
        }
        final CatEmpresaRecif entidad = opt.get();
        if (request.getRfc() != null) {
            entidad.setRfc(request.getRfc());
        }
        if (request.getRazonSocial() != null) {
            entidad.setRazonSocial(request.getRazonSocial());
        }
        if (request.getBlnActivo() != null) {
            entidad.setBlnActivo(request.getBlnActivo());
        }
        if (request.getFecIniVigencia() != null) {
            entidad.setFecIniVigencia(request.getFecIniVigencia());
        }
        if (request.getFecFinVigencia() != null) {
            entidad.setFecFinVigencia(request.getFecFinVigencia());
        }
        if (request.getCveUnidadAdministrativa() != null) {
            final Optional<CatUnidadAdministrativa> unidadAdministrativa =
                    unidadAdministrativaRepository.findById(request.getCveUnidadAdministrativa());
            if (unidadAdministrativa.isEmpty()) {
                return error(CodigoErrorConst.UNIDAD_ADMINISTRATIVA_NO_ENCONTRADA,
                        "Unidad administrativa no encontrada: " + request.getCveUnidadAdministrativa());
            }
            entidad.setCveUnidadAdministrativa(unidadAdministrativa.get());
        }
        return exito(empresaRecifMapper.toResponse(empresaRecifRepository.save(entidad)));
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
