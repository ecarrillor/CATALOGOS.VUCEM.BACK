package mx.gob.sat.catalogo.service.api.casfraccionttra;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.casfraccionttra.CatCasFraccionTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.casfraccionttra.CatCasFraccionTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatCasFraccionTtra;
import mx.gob.sat.catalogo.repository.CatCasFraccionTtraRepository;
import mx.gob.sat.catalogo.repository.CatCasRepository;
import mx.gob.sat.catalogo.repository.CatFraccionArancelariaRepository;
import mx.gob.sat.catalogo.repository.CatTipoTramiteRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatCasFraccionTtraMapper;
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
class CatCasFraccionTtraAPIServiceImpl implements CatCasFraccionTtraAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of("idCasFraccionTtra", "idCasFraccionTtra");
    private static final String DEFAULT_SORT = "idCasFraccionTtra";

    private final CatCasFraccionTtraRepository casFraccionTtraRepository;
    private final CatCasRepository casRepository;
    private final CatFraccionArancelariaRepository fraccionArancelariaRepository;
    private final CatTipoTramiteRepository tipoTramiteRepository;
    private final CatCasFraccionTtraMapper casFraccionTtraMapper;

    @Override
    public BaseResponse<PaginaResponse<CatCasFraccionTtraResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatCasFraccionTtra> paginaResultado = casFraccionTtraRepository.findAll(pageable);
        final List<CatCasFraccionTtraResponse> contenido = casFraccionTtraMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatCasFraccionTtraResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatCasFraccionTtraResponse> findById(final Short idCasFraccionTtra) {
        final Optional<CatCasFraccionTtra> resultado = casFraccionTtraRepository.findById(idCasFraccionTtra);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.CAS_FRACCION_TTRA_NO_ENCONTRADO, "Cas Fraccion Ttra no encontrado: " + idCasFraccionTtra);
        }
        return exito(casFraccionTtraMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatCasFraccionTtraResponse> crear(final CatCasFraccionTtraRequest request) {
        final CatCasFraccionTtra entidad = new CatCasFraccionTtra();
        entidad.setIdCasFraccionTtra(request.getIdCasFraccionTtra());
        if (request.getIdCas() != null) {
            casRepository.findById(request.getIdCas()).ifPresent(entidad::setIdCas);
        }
        if (request.getCveFraccion() != null) {
            fraccionArancelariaRepository.findById(request.getCveFraccion()).ifPresent(entidad::setCveFraccion);
        }
        if (request.getIdTipoTramite() != null) {
            tipoTramiteRepository.findById(request.getIdTipoTramite()).ifPresent(entidad::setIdTipoTramite);
        }
        entidad.setBlnRotterdam(request.getBlnRotterdam());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlActivo(request.getBlActivo());
        entidad.setDescFraccionAlt(request.getDescFraccionAlt());
        entidad.setCvnWasser(request.getCvnWasser());
        entidad.setCvnArmas(request.getCvnArmas());
        entidad.setCvnMontreal(request.getCvnMontreal());
        entidad.setCvnEstocolmo(request.getCvnEstocolmo());
        entidad.setFormaDesc(request.getFormaDesc());
        entidad.setIdeIdentificadorRegla(request.getIdeIdentificadorRegla());
        final CatCasFraccionTtra guardada = casFraccionTtraRepository.save(entidad);
        log.info("Cas Fraccion Ttra creado: {}", guardada.getIdCasFraccionTtra());
        return exito(casFraccionTtraMapper.toResponse(guardada));
    }

    @Override
    public BaseResponse<CatCasFraccionTtraResponse> actualizar(final Short idCasFraccionTtra, final CatCasFraccionTtraRequest request) {
        final Optional<CatCasFraccionTtra> opt = casFraccionTtraRepository.findById(idCasFraccionTtra);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.CAS_FRACCION_TTRA_NO_ENCONTRADO, "Cas Fraccion Ttra no encontrado: " + idCasFraccionTtra);
        }
        final CatCasFraccionTtra entidad = opt.get();
        if (request.getIdCas() != null) {
            casRepository.findById(request.getIdCas()).ifPresent(entidad::setIdCas);
        }
        if (request.getCveFraccion() != null) {
            fraccionArancelariaRepository.findById(request.getCveFraccion()).ifPresent(entidad::setCveFraccion);
        }
        if (request.getIdTipoTramite() != null) {
            tipoTramiteRepository.findById(request.getIdTipoTramite()).ifPresent(entidad::setIdTipoTramite);
        }
        if (request.getBlnRotterdam() != null) { entidad.setBlnRotterdam(request.getBlnRotterdam()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlActivo() != null) { entidad.setBlActivo(request.getBlActivo()); }
        if (request.getDescFraccionAlt() != null) { entidad.setDescFraccionAlt(request.getDescFraccionAlt()); }
        if (request.getCvnWasser() != null) { entidad.setCvnWasser(request.getCvnWasser()); }
        if (request.getCvnArmas() != null) { entidad.setCvnArmas(request.getCvnArmas()); }
        if (request.getCvnMontreal() != null) { entidad.setCvnMontreal(request.getCvnMontreal()); }
        if (request.getCvnEstocolmo() != null) { entidad.setCvnEstocolmo(request.getCvnEstocolmo()); }
        if (request.getFormaDesc() != null) { entidad.setFormaDesc(request.getFormaDesc()); }
        if (request.getIdeIdentificadorRegla() != null) { entidad.setIdeIdentificadorRegla(request.getIdeIdentificadorRegla()); }
        return exito(casFraccionTtraMapper.toResponse(casFraccionTtraRepository.save(entidad)));
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
