package mx.gob.sat.catalogo.service.api.laboratoriottra;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.laboratoriottra.CatLaboratorioTtraRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.laboratoriottra.CatLaboratorioTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatLaboratorioTtra;
import mx.gob.sat.catalogo.model.entity.CatTipoTramite;
import mx.gob.sat.catalogo.repository.CatLaboratorioTtraRepository;
import mx.gob.sat.catalogo.repository.CatTipoTramiteRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatLaboratorioTtraMapper;
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
class CatLaboratorioTtraAPIServiceImpl implements CatLaboratorioTtraAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idLaboratorioTtra", "idLaboratorioTtra"
    );
    private static final String DEFAULT_SORT = "idLaboratorioTtra";

    private final CatLaboratorioTtraRepository laboratorioTtraRepository;
    private final CatTipoTramiteRepository tipoTramiteRepository;
    private final CatLaboratorioTtraMapper laboratorioTtraMapper;

    @Override
    public BaseResponse<PaginaResponse<CatLaboratorioTtraResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatLaboratorioTtra> paginaResultado = laboratorioTtraRepository.findAll(pageable);
        final List<CatLaboratorioTtraResponse> contenido = laboratorioTtraMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatLaboratorioTtraResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatLaboratorioTtraResponse> findById(final Long idLaboratorioTtra) {
        final Optional<CatLaboratorioTtra> resultado = laboratorioTtraRepository.findById(idLaboratorioTtra);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.LABORATORIO_TTRA_NO_ENCONTRADO, "Laboratorio Ttra no encontrado: " + idLaboratorioTtra);
        }
        return exito(laboratorioTtraMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatLaboratorioTtraResponse> crear(final CatLaboratorioTtraRequest request) {
        final CatLaboratorioTtra entidad = new CatLaboratorioTtra();
        entidad.setIdLaboratorioTtra(request.getIdLaboratorioTtra());
        entidad.setDescLaboratorio(request.getDescLaboratorio());
        entidad.setIdeTipoLaboratorio(request.getIdeTipoLaboratorio());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        if (request.getIdTipoTramite() != null) {
            final Optional<CatTipoTramite> tipoTramite = tipoTramiteRepository.findById(request.getIdTipoTramite());
            tipoTramite.ifPresent(entidad::setIdTipoTramite);
        }
        final CatLaboratorioTtra guardado = laboratorioTtraRepository.save(entidad);
        log.info("Laboratorio Ttra creado: {}", guardado.getIdLaboratorioTtra());
        return exito(laboratorioTtraMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatLaboratorioTtraResponse> actualizar(final Long idLaboratorioTtra, final CatLaboratorioTtraRequest request) {
        final Optional<CatLaboratorioTtra> opt = laboratorioTtraRepository.findById(idLaboratorioTtra);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.LABORATORIO_TTRA_NO_ENCONTRADO, "Laboratorio Ttra no encontrado: " + idLaboratorioTtra);
        }
        final CatLaboratorioTtra entidad = opt.get();
        if (request.getDescLaboratorio() != null) { entidad.setDescLaboratorio(request.getDescLaboratorio()); }
        if (request.getIdeTipoLaboratorio() != null) { entidad.setIdeTipoLaboratorio(request.getIdeTipoLaboratorio()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getIdTipoTramite() != null) {
            final Optional<CatTipoTramite> tipoTramite = tipoTramiteRepository.findById(request.getIdTipoTramite());
            tipoTramite.ifPresent(entidad::setIdTipoTramite);
        }
        return exito(laboratorioTtraMapper.toResponse(laboratorioTtraRepository.save(entidad)));
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
