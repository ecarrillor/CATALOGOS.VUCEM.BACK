package mx.gob.sat.catalogo.service.api.vidasilvestre;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.gob.sat.basems.controller.response.BaseResponse;
import mx.gob.sat.basems.utils.consts.GeneralConst;
import mx.gob.sat.catalogo.controller.request.vidasilvestre.CatVidaSilvestreRequest;
import mx.gob.sat.catalogo.controller.response.PaginaResponse;
import mx.gob.sat.catalogo.controller.response.vidasilvestre.CatVidaSilvestreResponse;
import mx.gob.sat.catalogo.model.entity.CatEspecie;
import mx.gob.sat.catalogo.model.entity.CatGenero;
import mx.gob.sat.catalogo.model.entity.CatVidaSilvestre;
import mx.gob.sat.catalogo.repository.CatEspecieRepository;
import mx.gob.sat.catalogo.repository.CatGeneroRepository;
import mx.gob.sat.catalogo.repository.CatVidaSilvestreRepository;
import mx.gob.sat.catalogo.util.SortValidator;
import mx.gob.sat.catalogo.util.consts.CodigoErrorConst;
import mx.gob.sat.catalogo.util.mapper.CatVidaSilvestreMapper;
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
class CatVidaSilvestreAPIServiceImpl implements CatVidaSilvestreAPIService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idVidaSilvestre", "idVidaSilvestre"
    );
    private static final String DEFAULT_SORT = "idVidaSilvestre";

    private final CatVidaSilvestreRepository vidaSilvestreRepository;
    private final CatGeneroRepository generoRepository;
    private final CatEspecieRepository especieRepository;
    private final CatVidaSilvestreMapper vidaSilvestreMapper;

    @Override
    public BaseResponse<PaginaResponse<CatVidaSilvestreResponse>> listar(final int pagina, final int tamano, final String busqueda, final String sortBy, final String sortDir) {
        final Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        final Pageable pageable = sort.isSorted()
                ? PageRequest.of(pagina, tamano, sort)
                : PageRequest.of(pagina, tamano, Sort.by(Sort.Direction.ASC, DEFAULT_SORT));
        final Page<CatVidaSilvestre> paginaResultado = vidaSilvestreRepository.findAll(pageable);
        final List<CatVidaSilvestreResponse> contenido = vidaSilvestreMapper.toResponseList(paginaResultado.getContent());
        return exito(PaginaResponse.<CatVidaSilvestreResponse>builder()
                .contenido(contenido).pagina(paginaResultado.getNumber()).tamano(paginaResultado.getSize())
                .total(paginaResultado.getTotalElements()).totalPaginas(paginaResultado.getTotalPages())
                .ultima(paginaResultado.isLast()).build());
    }

    @Override
    public BaseResponse<CatVidaSilvestreResponse> findById(final Integer idVidaSilvestre) {
        final Optional<CatVidaSilvestre> resultado = vidaSilvestreRepository.findById(idVidaSilvestre);
        if (resultado.isEmpty()) {
            return error(CodigoErrorConst.VIDA_SILVESTRE_NO_ENCONTRADO, "Vida Silvestre no encontrada: " + idVidaSilvestre);
        }
        return exito(vidaSilvestreMapper.toResponse(resultado.get()));
    }

    @Override
    public BaseResponse<CatVidaSilvestreResponse> crear(final CatVidaSilvestreRequest request) {
        final CatVidaSilvestre entidad = new CatVidaSilvestre();
        entidad.setIdeTipoVidaSilvestre(request.getIdeTipoVidaSilvestre());
        entidad.setDescNombreComun(request.getDescNombreComun());
        entidad.setDescNombreCientifico(request.getDescNombreCientifico());
        entidad.setIdeClasifTaxonomica(request.getIdeClasifTaxonomica());
        entidad.setFecIniVigencia(request.getFecIniVigencia());
        entidad.setFecFinVigencia(request.getFecFinVigencia());
        entidad.setBlnActivo(request.getBlnActivo());
        entidad.setFuncionZootecnica(request.getFuncionZootecnica());
        if (request.getIdGenero() != null) {
            final Optional<CatGenero> genero = generoRepository.findById(request.getIdGenero());
            genero.ifPresent(entidad::setIdGenero);
        }
        if (request.getIdEspecie() != null) {
            final Optional<CatEspecie> especie = especieRepository.findById(request.getIdEspecie());
            especie.ifPresent(entidad::setIdEspecie);
        }
        final CatVidaSilvestre guardado = vidaSilvestreRepository.save(entidad);
        log.info("Vida Silvestre creada: {}", guardado.getIdVidaSilvestre());
        return exito(vidaSilvestreMapper.toResponse(guardado));
    }

    @Override
    public BaseResponse<CatVidaSilvestreResponse> actualizar(final Integer idVidaSilvestre, final CatVidaSilvestreRequest request) {
        final Optional<CatVidaSilvestre> opt = vidaSilvestreRepository.findById(idVidaSilvestre);
        if (opt.isEmpty()) {
            return error(CodigoErrorConst.VIDA_SILVESTRE_NO_ENCONTRADO, "Vida Silvestre no encontrada: " + idVidaSilvestre);
        }
        final CatVidaSilvestre entidad = opt.get();
        if (request.getIdeTipoVidaSilvestre() != null) { entidad.setIdeTipoVidaSilvestre(request.getIdeTipoVidaSilvestre()); }
        if (request.getDescNombreComun() != null) { entidad.setDescNombreComun(request.getDescNombreComun()); }
        if (request.getDescNombreCientifico() != null) { entidad.setDescNombreCientifico(request.getDescNombreCientifico()); }
        if (request.getIdeClasifTaxonomica() != null) { entidad.setIdeClasifTaxonomica(request.getIdeClasifTaxonomica()); }
        if (request.getFecIniVigencia() != null) { entidad.setFecIniVigencia(request.getFecIniVigencia()); }
        if (request.getFecFinVigencia() != null) { entidad.setFecFinVigencia(request.getFecFinVigencia()); }
        if (request.getBlnActivo() != null) { entidad.setBlnActivo(request.getBlnActivo()); }
        if (request.getFuncionZootecnica() != null) { entidad.setFuncionZootecnica(request.getFuncionZootecnica()); }
        if (request.getIdGenero() != null) {
            final Optional<CatGenero> genero = generoRepository.findById(request.getIdGenero());
            genero.ifPresent(entidad::setIdGenero);
        }
        if (request.getIdEspecie() != null) {
            final Optional<CatEspecie> especie = especieRepository.findById(request.getIdEspecie());
            especie.ifPresent(entidad::setIdEspecie);
        }
        return exito(vidaSilvestreMapper.toResponse(vidaSilvestreRepository.save(entidad)));
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
