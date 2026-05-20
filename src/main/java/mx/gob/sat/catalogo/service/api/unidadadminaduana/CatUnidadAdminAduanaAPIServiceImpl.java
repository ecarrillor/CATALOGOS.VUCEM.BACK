package mx.gob.sat.catalogo.service.api.unidadadminaduana;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.unidadadminaduana.CatUnidadAdminAduanaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.unidadadminaduana.CatUnidadAdminAduanaResponse;
import mx.gob.sat.catalogo.model.entity.CatUnidadAdminAduana;
import mx.gob.sat.catalogo.model.entity.CatUnidadAdminAduanaId;
import mx.gob.sat.catalogo.repository.CatUnidadAdminAduanaRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatUnidadAdminAduanaMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatUnidadAdminAduanaAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio para el catalogo de unidades administrativas de aduana.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Service
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatUnidadAdminAduanaAPIServiceImpl implements CatUnidadAdminAduanaAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveUnidadAdministrativa", "id.cveUnidadAdministrativa",
            "cveAduana", "id.cveAduana");
    private static final String DEFAULT_SORT = "id.cveUnidadAdministrativa";

    private final CatUnidadAdminAduanaRepository unidadAdminAduanaRepository;
    private final CatUnidadAdminAduanaMapper unidadAdminAduanaMapper;

    @Override
    public BaseResponse<PaginaResponse<CatUnidadAdminAduanaResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted() ? PageRequest.of(pagina, tamano, sort) : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatUnidadAdminAduana> paginaResultado = unidadAdminAduanaRepository.findAll(pageable);
        final List<CatUnidadAdminAduanaResponse> contenido = unidadAdminAduanaMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatUnidadAdminAduanaResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatUnidadAdminAduanaResponse> findById(final String cveUnidadAdministrativa, final String cveAduana) {
        final CatUnidadAdminAduanaId id = new CatUnidadAdminAduanaId();
        id.setCveUnidadAdministrativa(cveUnidadAdministrativa);
        id.setCveAduana(cveAduana);
        final Optional<CatUnidadAdminAduana> resultado = unidadAdminAduanaRepository.findById(id);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.UNIDAD_ADMIN_ADUANA_NO_ENCONTRADO, "Unidad Admin Aduana no encontrada: " + cveUnidadAdministrativa + "/" + cveAduana);
        }
        return exito(unidadAdminAduanaMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatUnidadAdminAduanaResponse> crear(final CatUnidadAdminAduanaRequest request) {
        final CatUnidadAdminAduana entidad = new CatUnidadAdminAduana();
        final CatUnidadAdminAduanaId id = new CatUnidadAdminAduanaId();
        id.setCveUnidadAdministrativa(request.getCveUnidadAdministrativa());
        id.setCveAduana(request.getCveAduana());
        entidad.setId(id);
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        final CatUnidadAdminAduana guardado = unidadAdminAduanaRepository.save(entidad);
        log.info("Unidad Admin Aduana creada: {}/{}", guardado.getId().getCveUnidadAdministrativa(), guardado.getId().getCveAduana());
        return exito(unidadAdminAduanaMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatUnidadAdminAduanaResponse> actualizar(final String cveUnidadAdministrativa, final String cveAduana, final CatUnidadAdminAduanaRequest request) {
        final CatUnidadAdminAduanaId id = new CatUnidadAdminAduanaId();
        id.setCveUnidadAdministrativa(cveUnidadAdministrativa);
        id.setCveAduana(cveAduana);
        final Optional<CatUnidadAdminAduana> opt = unidadAdminAduanaRepository.findById(id);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.UNIDAD_ADMIN_ADUANA_NO_ENCONTRADO, "Unidad Admin Aduana no encontrada: " + cveUnidadAdministrativa + "/" + cveAduana);
        }
        final CatUnidadAdminAduana entidad = opt.get();
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(unidadAdminAduanaMapper.toResponse(unidadAdminAduanaRepository.save(entidad)));
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
