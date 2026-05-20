package mx.gob.sat.catalogo.service.api.arancelprosec;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.arancelprosec.CatArancelProsecRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.arancelprosec.CatArancelProsecResponse;
import mx.gob.sat.catalogo.model.entity.CatArancelProsec;
import mx.gob.sat.catalogo.model.entity.CatArancelProsecId;
import mx.gob.sat.catalogo.repository.CatArancelProsecRepository;
import mx.gob.sat.catalogo.repository.CatFraccionArancelariaRepository;
import mx.gob.sat.catalogo.repository.CatSectorProsecRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.mapper.CatArancelProsecMapper;
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
class CatArancelProsecAPIServiceImpl implements CatArancelProsecAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveFraccion", "id.cveFraccion",
            "cveSectorProsec", "id.cveSectorProsec");
    private static final String DEFAULT_SORT = "id.cveFraccion";

    private final CatArancelProsecRepository arancelProsecRepository;
    private final CatFraccionArancelariaRepository fraccionArancelariaRepository;
    private final CatSectorProsecRepository sectorProsecRepository;
    private final CatArancelProsecMapper arancelProsecMapper;

    @Override
    public BaseResponse<PaginaResponse<CatArancelProsecResponse>> listar(
            final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatArancelProsec> paginaResultado = arancelProsecRepository.findAll(pageable);
        final List<CatArancelProsecResponse> contenido = arancelProsecMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatArancelProsecResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatArancelProsecResponse> crear(final CatArancelProsecRequest request) {
        final CatArancelProsec entidad = new CatArancelProsec();
        final CatArancelProsecId id = new CatArancelProsecId();
        id.setCveFraccion(request.getCveFraccion());
        id.setCveSectorProsec(request.getCveSectorProsec());
        entidad.setId(id);
        if (request.getCveFraccion() != null) {
            fraccionArancelariaRepository.findById(request.getCveFraccion()).ifPresent(entidad::setCveFraccion);
        }
        if (request.getCveSectorProsec() != null) {
            sectorProsecRepository.findById(request.getCveSectorProsec()).ifPresent(entidad::setCveSectorProsec);
        }
        entidad.setTasa(request.getTasa());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        final CatArancelProsec guardada = arancelProsecRepository.save(entidad);
        log.info("Arancel Prosec creado: {}/{}", guardada.getId().getCveFraccion(), guardada.getId().getCveSectorProsec());
        return exito(arancelProsecMapper.toResponse(guardada));
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
