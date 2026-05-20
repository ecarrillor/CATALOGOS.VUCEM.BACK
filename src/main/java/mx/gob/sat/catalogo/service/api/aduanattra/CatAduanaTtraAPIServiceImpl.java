package mx.gob.sat.catalogo.service.api.aduanattra;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.aduanattra.CatAduanaTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.aduanattra.CatAduanaTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatAduana;
import mx.gob.sat.catalogo.model.entity.CatAduanaTtra;
import mx.gob.sat.catalogo.model.entity.CatTipoTramite;
import mx.gob.sat.catalogo.repository.CatAduanaRepository;
import mx.gob.sat.catalogo.repository.CatAduanaTtraRepository;
import mx.gob.sat.catalogo.repository.CatTipoTramiteRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatAduanaTtraMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatAduanaTtraAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de aduanas ttra.</p>
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
class CatAduanaTtraAPIServiceImpl implements CatAduanaTtraAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idAduanaTtra", "idAduanaTtra",
            "aliasAduana", "aliasAduana");
    private static final String DEFAULT_SORT = "idAduanaTtra";

    private final CatAduanaTtraRepository aduanaTtraRepository;
    private final CatAduanaRepository aduanaRepository;
    private final CatTipoTramiteRepository tipoTramiteRepository;
    private final CatAduanaTtraMapper aduanaTtraMapper;

    @Override
    public BaseResponse<PaginaResponse<CatAduanaTtraResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatAduanaTtra> paginaResultado = aduanaTtraRepository.findAll(pageable);
        final List<CatAduanaTtraResponse> contenido =
                aduanaTtraMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatAduanaTtraResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatAduanaTtraResponse> findById(final Long idAduanaTtra) {
        final Optional<CatAduanaTtra> resultado = aduanaTtraRepository.findById(idAduanaTtra);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.ADUANA_TTRA_NO_ENCONTRADA,
                    "Aduana ttra no encontrada: " + idAduanaTtra);
        }
        return exito(aduanaTtraMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatAduanaTtraResponse> crear(final CatAduanaTtraRequest request) {
        final CatAduanaTtra entidad = new CatAduanaTtra();
        entidad.setIdAduanaTtra(request.getIdAduanaTtra());
        entidad.setAliasAduana(request.getAliasAduana());
        entidad.setFecCaptura(request.getFecCaptura());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());

        final Optional<CatAduana> aduana = aduanaRepository.findById(request.getCveAduana());
        if (aduana.isEmpty()) {
            return error(CodigoErrorConst.ADUANA_NO_ENCONTRADA,
                    "Aduana no encontrada: " + request.getCveAduana());
        }
        entidad.setCveAduana(aduana.get());

        final Optional<CatTipoTramite> tipoTramite = tipoTramiteRepository.findById(request.getIdTipoTramite());
        if (tipoTramite.isEmpty()) {
            return error(CodigoErrorConst.TIPO_TRAMITE_NO_ENCONTRADO,
                    "Tipo de tramite no encontrado: " + request.getIdTipoTramite());
        }
        entidad.setIdTipoTramite(tipoTramite.get());

        final CatAduanaTtra guardada = aduanaTtraRepository.save(entidad);
        log.info("Aduana ttra creada: {}", guardada.getIdAduanaTtra());
        return exito(aduanaTtraMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatAduanaTtraResponse> actualizar(
            final Long idAduanaTtra, final CatAduanaTtraRequest request) {
        final Optional<CatAduanaTtra> opt = aduanaTtraRepository.findById(idAduanaTtra);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.ADUANA_TTRA_NO_ENCONTRADA,
                    "Aduana ttra no encontrada: " + idAduanaTtra);
        }
        final CatAduanaTtra entidad = opt.get();
        if (request.getAliasAduana() != null) { entidad.setAliasAduana(request.getAliasAduana()); }
        if (request.getFecCaptura() != null) { entidad.setFecCaptura(request.getFecCaptura()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }

        if (request.getCveAduana() != null) {
            final Optional<CatAduana> aduana = aduanaRepository.findById(request.getCveAduana());
            if (aduana.isEmpty()) {
                return error(CodigoErrorConst.ADUANA_NO_ENCONTRADA,
                        "Aduana no encontrada: " + request.getCveAduana());
            }
            entidad.setCveAduana(aduana.get());
        }

        if (request.getIdTipoTramite() != null) {
            final Optional<CatTipoTramite> tipoTramite = tipoTramiteRepository.findById(request.getIdTipoTramite());
            if (tipoTramite.isEmpty()) {
                return error(CodigoErrorConst.TIPO_TRAMITE_NO_ENCONTRADO,
                        "Tipo de tramite no encontrado: " + request.getIdTipoTramite());
            }
            entidad.setIdTipoTramite(tipoTramite.get());
        }

        return exito(aduanaTtraMapper.toResponse(aduanaTtraRepository.save(entidad)));
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
