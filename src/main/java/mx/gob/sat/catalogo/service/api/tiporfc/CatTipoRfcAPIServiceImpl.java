package mx.gob.sat.catalogo.service.api.tiporfc;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.tiporfc.CatTipoRfcRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.tiporfc.CatTipoRfcResponse;
import mx.gob.sat.catalogo.model.entity.CatTipoRfc;
import mx.gob.sat.catalogo.model.entity.CatTipoRfcId;
import mx.gob.sat.catalogo.model.entity.CatUnidadAdministrativa;
import mx.gob.sat.catalogo.repository.CatTipoRfcRepository;
import mx.gob.sat.catalogo.repository.CatUnidadAdministrativaRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatTipoRfcMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatTipoRfcAPIServiceImpl implements CatTipoRfcAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "rfc", "id.rfc",
            "ideTipoRfc", "id.ideTipoRfc");
    private static final String DEFAULT_SORT = "id.rfc";

    private final CatTipoRfcRepository tipoRfcRepository;
    private final CatUnidadAdministrativaRepository unidadAdministrativaRepository;
    private final CatTipoRfcMapper tipoRfcMapper;

    @Override
    public BaseResponse<PaginaResponse<CatTipoRfcResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatTipoRfc> paginaResultado = tipoRfcRepository.findAll(pageable);
        final List<CatTipoRfcResponse> contenido = tipoRfcMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatTipoRfcResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatTipoRfcResponse> findById(final String rfc, final String ideTipoRfc) {
        final CatTipoRfcId id = new CatTipoRfcId();
        id.setRfc(rfc);
        id.setIdeTipoRfc(ideTipoRfc);
        final Optional<CatTipoRfc> resultado = tipoRfcRepository.findById(id);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.TIPO_RFC_NO_ENCONTRADO, "Tipo RFC no encontrado: " + rfc + "/" + ideTipoRfc);
        }
        return exito(tipoRfcMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatTipoRfcResponse> crear(final CatTipoRfcRequest request) {
        final CatTipoRfc entidad = new CatTipoRfc();
        final CatTipoRfcId id = new CatTipoRfcId();
        id.setRfc(request.getRfc());
        id.setIdeTipoRfc(request.getIdeTipoRfc());
        entidad.setId(id);
        entidad.setRazonSocial(request.getRazonSocial());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setDireccion(request.getDireccion());
        entidad.setTelefono(request.getTelefono());
        entidad.setClave(request.getClave());
        entidad.setBlnActivo(request.getBlnActivo());
        entidad.setCorreoElectronico(request.getCorreoElectronico());
        entidad.setFax(request.getFax());
        entidad.setBlnLabAcreditado(request.getBlnLabAcreditado());
        if (request.getCveUnidadAdministrativa() != null) {
            final Optional<CatUnidadAdministrativa> unidad = unidadAdministrativaRepository.findById(request.getCveUnidadAdministrativa());
            unidad.ifPresent(entidad::setCveUnidadAdministrativa);
        }
        final CatTipoRfc guardado = tipoRfcRepository.save(entidad);
        log.info("Tipo RFC creado: {}/{}", guardado.getId().getRfc(), guardado.getId().getIdeTipoRfc());
        return exito(tipoRfcMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatTipoRfcResponse> actualizar(final String rfc, final String ideTipoRfc, final CatTipoRfcRequest request) {
        final CatTipoRfcId id = new CatTipoRfcId();
        id.setRfc(rfc);
        id.setIdeTipoRfc(ideTipoRfc);
        final Optional<CatTipoRfc> opt = tipoRfcRepository.findById(id);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.TIPO_RFC_NO_ENCONTRADO, "Tipo RFC no encontrado: " + rfc + "/" + ideTipoRfc);
        }
        final CatTipoRfc entidad = opt.get();
        if (request.getRazonSocial() != null) { entidad.setRazonSocial(request.getRazonSocial()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getDireccion() != null) { entidad.setDireccion(request.getDireccion()); }
        if (request.getTelefono() != null) { entidad.setTelefono(request.getTelefono()); }
        if (request.getClave() != null) { entidad.setClave(request.getClave()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getCorreoElectronico() != null) { entidad.setCorreoElectronico(request.getCorreoElectronico()); }
        if (request.getFax() != null) { entidad.setFax(request.getFax()); }
        if (request.getBlnLabAcreditado() != null) { entidad.setBlnLabAcreditado(request.getBlnLabAcreditado()); }
        if (request.getCveUnidadAdministrativa() != null) {
            final Optional<CatUnidadAdministrativa> unidad = unidadAdministrativaRepository.findById(request.getCveUnidadAdministrativa());
            unidad.ifPresent(entidad::setCveUnidadAdministrativa);
        }
        return exito(tipoRfcMapper.toResponse(tipoRfcRepository.save(entidad)));
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
