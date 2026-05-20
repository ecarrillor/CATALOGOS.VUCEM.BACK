package mx.gob.sat.catalogo.service.api.clasiftoxicologicattra;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.clasiftoxicologicattra.CatClasifToxicologicaTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.clasiftoxicologicattra.CatClasifToxicologicaTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatClasifToxicologicaTtra;
import mx.gob.sat.catalogo.model.entity.CatTipoTramite;
import mx.gob.sat.catalogo.repository.CatClasifToxicologicaTtraRepository;
import mx.gob.sat.catalogo.repository.CatTipoTramiteRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatClasifToxicologicaTtraMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatClasifToxicologicaTtraAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de clasificaciones toxicologicas ttra.</p>
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
class CatClasifToxicologicaTtraAPIServiceImpl implements CatClasifToxicologicaTtraAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idClasifToxicologicaTtra", "idClasifToxicologicaTtra");
    private static final String DEFAULT_SORT = "idClasifToxicologicaTtra";

    private final CatClasifToxicologicaTtraRepository clasifToxicologicaTtraRepository;
    private final CatTipoTramiteRepository tipoTramiteRepository;
    private final CatClasifToxicologicaTtraMapper clasifToxicologicaTtraMapper;

    @Override
    public BaseResponse<PaginaResponse<CatClasifToxicologicaTtraResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatClasifToxicologicaTtra> paginaResultado = clasifToxicologicaTtraRepository.findAll(pageable);
        final List<CatClasifToxicologicaTtraResponse> contenido = clasifToxicologicaTtraMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatClasifToxicologicaTtraResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatClasifToxicologicaTtraResponse> findById(final Short idClasifToxicologicaTtra) {
        final Optional<CatClasifToxicologicaTtra> resultado = clasifToxicologicaTtraRepository.findById(idClasifToxicologicaTtra);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.CLASIF_TOXICOLOGICA_TTRA_NO_ENCONTRADA,
                    "Clasif toxicologica ttra no encontrada: " + idClasifToxicologicaTtra);
        }
        return exito(clasifToxicologicaTtraMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatClasifToxicologicaTtraResponse> crear(final CatClasifToxicologicaTtraRequest request) {
        final CatClasifToxicologicaTtra entidad = new CatClasifToxicologicaTtra();
        entidad.setDescClasifToxicologica(request.getDescClasifToxicologica());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());

        if (request.getIdTipoTramite() != null) {
            final Optional<CatTipoTramite> tipoTramite =
                    tipoTramiteRepository.findById(request.getIdTipoTramite());
            if (tipoTramite.isEmpty()) {
                return error(CodigoErrorConst.TIPO_TRAMITE_NO_ENCONTRADO,
                        "Tipo de tramite no encontrado: " + request.getIdTipoTramite());
            }
            entidad.setIdTipoTramite(tipoTramite.get());
        }

        final CatClasifToxicologicaTtra guardada = clasifToxicologicaTtraRepository.save(entidad);
        log.info("Clasif toxicologica ttra creada: {}", guardada.getIdClasifToxicologicaTtra());
        return exito(clasifToxicologicaTtraMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatClasifToxicologicaTtraResponse> actualizar(
            final Short idClasifToxicologicaTtra, final CatClasifToxicologicaTtraRequest request) {
        final Optional<CatClasifToxicologicaTtra> opt = clasifToxicologicaTtraRepository.findById(idClasifToxicologicaTtra);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.CLASIF_TOXICOLOGICA_TTRA_NO_ENCONTRADA,
                    "Clasif toxicologica ttra no encontrada: " + idClasifToxicologicaTtra);
        }
        final CatClasifToxicologicaTtra entidad = opt.get();
        if (request.getDescClasifToxicologica() != null) { entidad.setDescClasifToxicologica(request.getDescClasifToxicologica()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getIdTipoTramite() != null) {
            final Optional<CatTipoTramite> tipoTramite =
                    tipoTramiteRepository.findById(request.getIdTipoTramite());
            if (tipoTramite.isEmpty()) {
                return error(CodigoErrorConst.TIPO_TRAMITE_NO_ENCONTRADO,
                        "Tipo de tramite no encontrado: " + request.getIdTipoTramite());
            }
            entidad.setIdTipoTramite(tipoTramite.get());
        }
        return exito(clasifToxicologicaTtraMapper.toResponse(clasifToxicologicaTtraRepository.save(entidad)));
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
