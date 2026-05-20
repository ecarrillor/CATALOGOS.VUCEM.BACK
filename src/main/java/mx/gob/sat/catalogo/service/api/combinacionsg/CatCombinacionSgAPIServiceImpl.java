package mx.gob.sat.catalogo.service.api.combinacionsg;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.combinacionsg.CatCombinacionSgRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.combinacionsg.CatCombinacionSgResponse;
import mx.gob.sat.catalogo.model.entity.CatCombinacionSg;
import mx.gob.sat.catalogo.repository.CatCatalogoDRepository;
import mx.gob.sat.catalogo.repository.CatCombinacionSgRepository;
import mx.gob.sat.catalogo.repository.CatPaisRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatCombinacionSgMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatCombinacionSgAPIServiceImpl implements CatCombinacionSgAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of("idCombinacionSg", "idCombinacionSg");
    private static final String DEFAULT_SORT = "idCombinacionSg";

    private final CatCombinacionSgRepository combinacionSgRepository;
    private final CatCombinacionSgMapper combinacionSgMapper;
    private final CatCatalogoDRepository catalogoDRepository;
    private final CatPaisRepository paisRepository;

    @Override
    public BaseResponse<PaginaResponse<CatCombinacionSgResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatCombinacionSg> paginaResultado = combinacionSgRepository.findAll(pageable);
        final List<CatCombinacionSgResponse> contenido = combinacionSgMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatCombinacionSgResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatCombinacionSgResponse> findById(final Long idCombinacionSg) {
        final Optional<CatCombinacionSg> resultado = combinacionSgRepository.findById(idCombinacionSg);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.COMBINACION_SG_NO_ENCONTRADA, "Combinacion SG no encontrada: " + idCombinacionSg);
        }
        return exito(combinacionSgMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatCombinacionSgResponse> crear(final CatCombinacionSgRequest request) {
        final CatCombinacionSg entidad = new CatCombinacionSg();
        entidad.setIdCombinacionSg(request.getIdCombinacionSg());
        if (request.getCvcEspecie() != null) { catalogoDRepository.findById(request.getCvcEspecie()).ifPresent(entidad::setCvcEspecie); }
        if (request.getCvcFuncionZootecnica() != null) { catalogoDRepository.findById(request.getCvcFuncionZootecnica()).ifPresent(entidad::setCvcFuncionZootecnica); }
        if (request.getCvcNombreComun() != null) { catalogoDRepository.findById(request.getCvcNombreComun()).ifPresent(entidad::setCvcNombreComun); }
        if (request.getCvcTipoProducto() != null) { catalogoDRepository.findById(request.getCvcTipoProducto()).ifPresent(entidad::setCvcTipoProducto); }
        if (request.getCvePais() != null) { paisRepository.findById(request.getCvePais()).ifPresent(entidad::setCvePais); }
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        entidad.setIdeTipoCertificadoMerc(request.getIdeTipoCertificadoMerc());
        final CatCombinacionSg guardada = combinacionSgRepository.save(entidad);
        log.info("Combinacion SG creada: {}", guardada.getIdCombinacionSg());
        return exito(combinacionSgMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatCombinacionSgResponse> actualizar(final Long idCombinacionSg, final CatCombinacionSgRequest request) {
        final Optional<CatCombinacionSg> opt = combinacionSgRepository.findById(idCombinacionSg);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.COMBINACION_SG_NO_ENCONTRADA, "Combinacion SG no encontrada: " + idCombinacionSg);
        }
        final CatCombinacionSg entidad = opt.get();
        if (request.getCvcEspecie() != null) { catalogoDRepository.findById(request.getCvcEspecie()).ifPresent(entidad::setCvcEspecie); }
        if (request.getCvcFuncionZootecnica() != null) { catalogoDRepository.findById(request.getCvcFuncionZootecnica()).ifPresent(entidad::setCvcFuncionZootecnica); }
        if (request.getCvcNombreComun() != null) { catalogoDRepository.findById(request.getCvcNombreComun()).ifPresent(entidad::setCvcNombreComun); }
        if (request.getCvcTipoProducto() != null) { catalogoDRepository.findById(request.getCvcTipoProducto()).ifPresent(entidad::setCvcTipoProducto); }
        if (request.getCvePais() != null) { paisRepository.findById(request.getCvePais()).ifPresent(entidad::setCvePais); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getIdeTipoCertificadoMerc() != null) { entidad.setIdeTipoCertificadoMerc(request.getIdeTipoCertificadoMerc()); }
        return exito(combinacionSgMapper.toResponse(combinacionSgRepository.save(entidad)));
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
