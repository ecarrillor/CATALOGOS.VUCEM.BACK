package mx.gob.sat.catalogo.service.api.unidadadminvecina;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.unidadadminvecina.CatUnidadAdminVecinaRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.unidadadminvecina.CatUnidadAdminVecinaResponse;
import mx.gob.sat.catalogo.model.entity.CatUnidadAdminVecina;
import mx.gob.sat.catalogo.model.entity.CatUnidadAdminVecinaId;
import mx.gob.sat.catalogo.repository.CatUnidadAdminVecinaRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatUnidadAdminVecinaMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatUnidadAdminVecinaAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio para el catalogo de unidades administrativas vecinas.</p>
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
class CatUnidadAdminVecinaAPIServiceImpl implements CatUnidadAdminVecinaAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveUnidadAdministrativa", "id.cveUnidadAdministrativa",
            "cveEntidad", "id.cveEntidad");
    private static final String DEFAULT_SORT = "id.cveUnidadAdministrativa";

    private final CatUnidadAdminVecinaRepository unidadAdminVecinaRepository;
    private final CatUnidadAdminVecinaMapper unidadAdminVecinaMapper;

    @Override
    public BaseResponse<PaginaResponse<CatUnidadAdminVecinaResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted() ? PageRequest.of(pagina, tamano, sort) : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatUnidadAdminVecina> paginaResultado = unidadAdminVecinaRepository.findAll(pageable);
        final List<CatUnidadAdminVecinaResponse> contenido = unidadAdminVecinaMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatUnidadAdminVecinaResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatUnidadAdminVecinaResponse> findById(final String cveUnidadAdministrativa, final String cveEntidad) {
        final CatUnidadAdminVecinaId id = new CatUnidadAdminVecinaId();
        id.setCveUnidadAdministrativa(cveUnidadAdministrativa);
        id.setCveEntidad(cveEntidad);
        final Optional<CatUnidadAdminVecina> resultado = unidadAdminVecinaRepository.findById(id);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.UNIDAD_ADMIN_VECINA_NO_ENCONTRADO, "Unidad Admin Vecina no encontrada: " + cveUnidadAdministrativa + "/" + cveEntidad);
        }
        return exito(unidadAdminVecinaMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatUnidadAdminVecinaResponse> crear(final CatUnidadAdminVecinaRequest request) {
        final CatUnidadAdminVecina entidad = new CatUnidadAdminVecina();
        final CatUnidadAdminVecinaId id = new CatUnidadAdminVecinaId();
        id.setCveUnidadAdministrativa(request.getCveUnidadAdministrativa());
        id.setCveEntidad(request.getCveEntidad());
        entidad.setId(id);
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        final CatUnidadAdminVecina guardado = unidadAdminVecinaRepository.save(entidad);
        log.info("Unidad Admin Vecina creada: {}/{}", guardado.getId().getCveUnidadAdministrativa(), guardado.getId().getCveEntidad());
        return exito(unidadAdminVecinaMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatUnidadAdminVecinaResponse> actualizar(final String cveUnidadAdministrativa, final String cveEntidad, final CatUnidadAdminVecinaRequest request) {
        final CatUnidadAdminVecinaId id = new CatUnidadAdminVecinaId();
        id.setCveUnidadAdministrativa(cveUnidadAdministrativa);
        id.setCveEntidad(cveEntidad);
        final Optional<CatUnidadAdminVecina> opt = unidadAdminVecinaRepository.findById(id);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.UNIDAD_ADMIN_VECINA_NO_ENCONTRADO, "Unidad Admin Vecina no encontrada: " + cveUnidadAdministrativa + "/" + cveEntidad);
        }
        final CatUnidadAdminVecina entidad = opt.get();
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        return exito(unidadAdminVecinaMapper.toResponse(unidadAdminVecinaRepository.save(entidad)));
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
