package mx.gob.sat.catalogo.service.api.recintofiscalizado;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.recintofiscalizado.CatRecintoFiscalizadoRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.recintofiscalizado.CatRecintoFiscalizadoResponse;
import mx.gob.sat.catalogo.model.entity.CatAduana;
import mx.gob.sat.catalogo.model.entity.CatRecintoFiscalizado;
import mx.gob.sat.catalogo.repository.CatAduanaRepository;
import mx.gob.sat.catalogo.repository.CatRecintoFiscalizadoRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatRecintoFiscalizadoMapper;
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
class CatRecintoFiscalizadoAPIServiceImpl implements CatRecintoFiscalizadoAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idRecintoFiscalizado", "idRecintoFiscalizado",
            "nombre", "nombre"
    );
    private static final String DEFAULT_SORT = "idRecintoFiscalizado";

    private final CatRecintoFiscalizadoRepository recintoFiscalizadoRepository;
    private final CatAduanaRepository aduanaRepository;
    private final CatRecintoFiscalizadoMapper recintoFiscalizadoMapper;

    @Override
    public BaseResponse<PaginaResponse<CatRecintoFiscalizadoResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatRecintoFiscalizado> paginaResultado = recintoFiscalizadoRepository.findAll(pageable);
        final List<CatRecintoFiscalizadoResponse> contenido = recintoFiscalizadoMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatRecintoFiscalizadoResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatRecintoFiscalizadoResponse> findById(final Long idRecintoFiscalizado) {
        final Optional<CatRecintoFiscalizado> resultado = recintoFiscalizadoRepository.findById(idRecintoFiscalizado);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.RECINTO_FISCALIZADO_NO_ENCONTRADO, "Recinto Fiscalizado no encontrado: " + idRecintoFiscalizado);
        }
        return exito(recintoFiscalizadoMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatRecintoFiscalizadoResponse> crear(final CatRecintoFiscalizadoRequest request) {
        final CatRecintoFiscalizado entidad = new CatRecintoFiscalizado();
        entidad.setIdeTipoRecintoFiscalizado(request.getIdeTipoRecintoFiscalizado());
        entidad.setNombre(request.getNombre());
        entidad.setRfc(request.getRfc());
        entidad.setNumAutorizacion(request.getNumAutorizacion());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        entidad.setCodCamir(request.getCodCamir());
        entidad.setBlnComRfMf(request.getBlnComRfMf());
        entidad.setCorreoElectronico(request.getCorreoElectronico());
        entidad.setDescUrl(request.getDescUrl());
        entidad.setTipo(request.getTipo());
        if (request.getCveAduana() != null) {
            final Optional<CatAduana> aduana = aduanaRepository.findById(request.getCveAduana());
            aduana.ifPresent(entidad::setCveAduana);
        }
        final CatRecintoFiscalizado guardado = recintoFiscalizadoRepository.save(entidad);
        log.info("Recinto Fiscalizado creado: {}", guardado.getIdRecintoFiscalizado());
        return exito(recintoFiscalizadoMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatRecintoFiscalizadoResponse> actualizar(final Long idRecintoFiscalizado, final CatRecintoFiscalizadoRequest request) {
        final Optional<CatRecintoFiscalizado> opt = recintoFiscalizadoRepository.findById(idRecintoFiscalizado);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.RECINTO_FISCALIZADO_NO_ENCONTRADO, "Recinto Fiscalizado no encontrado: " + idRecintoFiscalizado);
        }
        final CatRecintoFiscalizado entidad = opt.get();
        if (request.getIdeTipoRecintoFiscalizado() != null) { entidad.setIdeTipoRecintoFiscalizado(request.getIdeTipoRecintoFiscalizado()); }
        if (request.getNombre() != null) { entidad.setNombre(request.getNombre()); }
        if (request.getRfc() != null) { entidad.setRfc(request.getRfc()); }
        if (request.getNumAutorizacion() != null) { entidad.setNumAutorizacion(request.getNumAutorizacion()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getCodCamir() != null) { entidad.setCodCamir(request.getCodCamir()); }
        if (request.getBlnComRfMf() != null) { entidad.setBlnComRfMf(request.getBlnComRfMf()); }
        if (request.getCorreoElectronico() != null) { entidad.setCorreoElectronico(request.getCorreoElectronico()); }
        if (request.getDescUrl() != null) { entidad.setDescUrl(request.getDescUrl()); }
        if (request.getTipo() != null) { entidad.setTipo(request.getTipo()); }
        if (request.getCveAduana() != null) {
            final Optional<CatAduana> aduana = aduanaRepository.findById(request.getCveAduana());
            aduana.ifPresent(entidad::setCveAduana);
        }
        return exito(recintoFiscalizadoMapper.toResponse(recintoFiscalizadoRepository.save(entidad)));
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
