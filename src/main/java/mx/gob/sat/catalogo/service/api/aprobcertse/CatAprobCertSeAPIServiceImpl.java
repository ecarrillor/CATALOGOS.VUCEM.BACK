package mx.gob.sat.catalogo.service.api.aprobcertse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.aprobcertse.CatAprobCertSeRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.aprobcertse.CatAprobCertSeResponse;
import mx.gob.sat.catalogo.model.entity.CatAprobCertSe;
import mx.gob.sat.catalogo.model.entity.CatUnidadAdministrativa;
import mx.gob.sat.catalogo.repository.CatAprobCertSeRepository;
import mx.gob.sat.catalogo.repository.CatUnidadAdministrativaRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatAprobCertSeMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatAprobCertSeAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de aprobaciones de certificado SE.</p>
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
class CatAprobCertSeAPIServiceImpl implements CatAprobCertSeAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idAprobCertSe", "idAprobCertSe",
            "numAprobCert", "numAprobCert");

    private static final String DEFAULT_SORT = "idAprobCertSe";

    private final CatAprobCertSeRepository aprobCertSeRepository;
    private final CatUnidadAdministrativaRepository unidadAdministrativaRepository;
    private final CatAprobCertSeMapper aprobCertSeMapper;

    @Override
    public BaseResponse<PaginaResponse<CatAprobCertSeResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatAprobCertSe> paginaResultado = aprobCertSeRepository.findAll(pageable);

        final List<CatAprobCertSeResponse> contenido =
                aprobCertSeMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatAprobCertSeResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatAprobCertSeResponse> findById(final Short idAprobCertSe) {
        final Optional<CatAprobCertSe> resultado = aprobCertSeRepository.findById(idAprobCertSe);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.APROB_CERT_SE_NO_ENCONTRADA,
                    "Aprobacion certificado SE no encontrada: " + idAprobCertSe);
        }
        return exito(aprobCertSeMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatAprobCertSeResponse> crear(final CatAprobCertSeRequest request) {
        final CatAprobCertSe entidad = new CatAprobCertSe();
        entidad.setIdeTipoAprobCertSe(request.getIdeTipoAprobCertSe());
        entidad.setNumAprobCert(request.getNumAprobCert());
        entidad.setFecEmision(request.getFecEmision());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());

        final Optional<CatUnidadAdministrativa> unidadAdministrativa =
                unidadAdministrativaRepository.findById(request.getCveUnidadAdministrativa());
        if (unidadAdministrativa.isEmpty()) {
            return error(CodigoErrorConst.UNIDAD_ADMINISTRATIVA_NO_ENCONTRADA,
                    "Unidad administrativa no encontrada: " + request.getCveUnidadAdministrativa());
        }
        entidad.setCveUnidadAdministrativa(unidadAdministrativa.get());

        final CatAprobCertSe guardada = aprobCertSeRepository.save(entidad);
        log.info("Aprobacion certificado SE creada: {}", guardada.getIdAprobCertSe());
        return exito(aprobCertSeMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatAprobCertSeResponse> actualizar(
            final Short idAprobCertSe, final CatAprobCertSeRequest request) {

        final Optional<CatAprobCertSe> opt = aprobCertSeRepository.findById(idAprobCertSe);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.APROB_CERT_SE_NO_ENCONTRADA,
                    "Aprobacion certificado SE no encontrada: " + idAprobCertSe);
        }
        final CatAprobCertSe entidad = opt.get();
        if (request.getIdeTipoAprobCertSe() != null) {
            entidad.setIdeTipoAprobCertSe(request.getIdeTipoAprobCertSe());
        }
        if (request.getNumAprobCert() != null) {
            entidad.setNumAprobCert(request.getNumAprobCert());
        }
        if (request.getFecEmision() != null) {
            entidad.setFecEmision(request.getFecEmision());
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
        if (request.getCveUnidadAdministrativa() != null) {
            final Optional<CatUnidadAdministrativa> unidadAdministrativa =
                    unidadAdministrativaRepository.findById(request.getCveUnidadAdministrativa());
            if (unidadAdministrativa.isEmpty()) {
                return error(CodigoErrorConst.UNIDAD_ADMINISTRATIVA_NO_ENCONTRADA,
                        "Unidad administrativa no encontrada: " + request.getCveUnidadAdministrativa());
            }
            entidad.setCveUnidadAdministrativa(unidadAdministrativa.get());
        }
        return exito(aprobCertSeMapper.toResponse(aprobCertSeRepository.save(entidad)));
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
