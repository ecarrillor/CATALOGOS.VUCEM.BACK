package mx.gob.sat.catalogo.service.api.sectorprosec;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.sectorprosec.CatSectorProsecRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.sectorprosec.CatSectorProsecResponse;
import mx.gob.sat.catalogo.model.entity.CatSectorProsec;
import mx.gob.sat.catalogo.repository.CatSectorProsecRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatSectorProsecMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Class:</b> CatSectorProsecAPIServiceImpl.java <br>
 * <b>Description:</b>
 * <p>Implementacion del servicio API para el catalogo de sectores PROSEC.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Implementacion del servicio API
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CatSectorProsecAPIServiceImpl implements CatSectorProsecAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveSectorProsec", "cveSectorProsec",
            "nombre", "nombre"
    );

    private static final String DEFAULT_SORT = "nombre";

    private final CatSectorProsecRepository sectorProsecRepository;
    private final CatSectorProsecMapper sectorProsecMapper;

    @Override
    public BaseResponse<PaginaResponse<CatSectorProsecResponse>> listar(
            final int pagina, final int tamano,
            final String busqueda, final String sortBy, final String sortDir) {

        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));

        final Page<CatSectorProsec> paginaResultado = (busqueda == null || busqueda.isBlank())
                ? sectorProsecRepository.findAll(pageable)
                : sectorProsecRepository.findByNombreContainingIgnoreCaseOrCveSectorProsecContainingIgnoreCase(
                        busqueda, busqueda, pageable);

        final List<CatSectorProsecResponse> contenido =
                sectorProsecMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatSectorProsecResponse>builder()
                .contenido(contenido)
                .pagina(paginaResultado.getNumber())
                .tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements())
                .totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast())
                .build());
    }

    @Override
    public BaseResponse<CatSectorProsecResponse> findById(final String cveSectorProsec) {
        final Optional<CatSectorProsec> resultado = sectorProsecRepository.findById(cveSectorProsec);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.SECTOR_PROSEC_NO_ENCONTRADO,
                    "Sector PROSEC no encontrado: " + cveSectorProsec);
        }
        return exito(sectorProsecMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatSectorProsecResponse> crear(final CatSectorProsecRequest request) {
        final CatSectorProsec guardado = sectorProsecRepository.save(sectorProsecMapper.toEntity(request));
        log.info("Sector PROSEC creado: {}", guardado.getCveSectorProsec());
        return exito(sectorProsecMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatSectorProsecResponse> actualizar(
            final String cveSectorProsec, final CatSectorProsecRequest request) {

        final Optional<CatSectorProsec> opt = sectorProsecRepository.findById(cveSectorProsec);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.SECTOR_PROSEC_NO_ENCONTRADO,
                    "Sector PROSEC no encontrado: " + cveSectorProsec);
        }
        final CatSectorProsec entidad = opt.get();
        if (request.getNombre() != null) {
            entidad.setNombre(request.getNombre());
        }
        if (request.getBlnProductorIndirecto() != null) {
            entidad.setBlnProductorIndirecto(request.getBlnProductorIndirecto());
        }
        if (request.getBlnAmpliacionMercancias() != null) {
            entidad.setBlnAmpliacionMercancias(request.getBlnAmpliacionMercancias());
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
        return exito(sectorProsecMapper.toResponse(sectorProsecRepository.save(entidad)));
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
