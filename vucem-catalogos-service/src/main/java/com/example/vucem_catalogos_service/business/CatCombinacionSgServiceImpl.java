package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatCombinacionSgService;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.dto.*;
import com.example.vucem_catalogos_service.model.entity.CatCatalogoD;
import com.example.vucem_catalogos_service.model.entity.CatCatalogoDTr;
import com.example.vucem_catalogos_service.model.entity.CatCombinacionSg;
import com.example.vucem_catalogos_service.model.entity.CatPais;
import com.example.vucem_catalogos_service.persistence.repo.ICatCatalogoDRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatCombinacionSgRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatPaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class CatCombinacionSgServiceImpl implements ICatCombinacionSgService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.ofEntries(

            Map.entry("id", "e.id"),

            Map.entry("cvcEspecie", "e.cvcEspecie.cveCatalogo"),
            Map.entry("descEspecie", "e.cvcEspecie.descGenerica1"),

            Map.entry("cvcFuncionZootecnica", "e.cvcFuncionZootecnica.cveCatalogo"),
            Map.entry("descFuncionZootecnica", "e.cvcFuncionZootecnica.descGenerica1"),

            Map.entry("cvcNombreComun", "e.cvcNombreComun.cveCatalogo"),
            Map.entry("descNombreComun", "e.cvcNombreComun.descGenerica1"),

            Map.entry("cvcTipoProducto", "e.cvcTipoProducto.cveCatalogo"),
            Map.entry("descTipoProducto", "e.cvcTipoProducto.descGenerica1"),

            Map.entry("cvePais", "e.cvePais.cvePais"),
            Map.entry("nombrePais", "e.cvePais.nombre"),

            Map.entry("ideTipoCertificadoMerc", "e.ideTipoCertificadoMerc"));



    @Autowired
    private ICatCombinacionSgRepository catCombinacionSgRepository;

    @Autowired
    private ICatCatalogoDRepository catCatalogoDRepository;

    @Autowired
    private ICatPaisRepository catPaisRepository;

    @Override
    public PageResponseDTO<CatCombinacionSgDTO> list(String search, String sortBy, String sortDir, Pageable pageable) {
        Short activo = null;
        String texto = null;

        if (search != null && !search.isBlank()) {
            String s = search.trim().toLowerCase();
            if (s.equals("activo")) {
                activo = 1;
            } else if (s.equals("inactivo")) {
                activo = 0;
            } else {
                texto = "%" + s + "%";
            }
        }

        Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        Pageable sortedPageable = sort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort)
                : pageable;

        Page<CatCombinacionSgDTO> page = catCombinacionSgRepository.search(texto, activo, sortedPageable);

        return PageResponseDTO.<CatCombinacionSgDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatCombinacionSgDTO findById(Long id) {
        return catCombinacionSgRepository.findByCombinacionSgDTO(id)
                .orElseThrow(() -> new RuntimeException("CatCombinacionSg no encontrado con id: " + id));
    }

    @Override
    public CatCombinacionSgDTO create(CatCombinacionSgDTO dto) {

        CatCombinacionSg entity = new CatCombinacionSg();
        entity.setId(dto.getId());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());
        entity.setIdeTipoCertificadoMerc(dto.getIdeTipoCertificadoMerc());

        if (dto.getCvcEspecie() != null) {
            CatCatalogoD especie = catCatalogoDRepository.findById(dto.getCvcEspecie())
                    .orElseThrow(() -> new RuntimeException("CatCatalogoD no encontrado: " + dto.getCvcEspecie()));
            entity.setCvcEspecie(especie);
        }

        if (dto.getCvcFuncionZootecnica() != null) {
            CatCatalogoD funcion = catCatalogoDRepository.findById(dto.getCvcFuncionZootecnica())
                    .orElseThrow(() -> new RuntimeException("CatCatalogoD no encontrado: " + dto.getCvcFuncionZootecnica()));
            entity.setCvcFuncionZootecnica(funcion);
        }

        if (dto.getCvcNombreComun() != null) {
            CatCatalogoD nombreComun = catCatalogoDRepository.findById(dto.getCvcNombreComun())
                    .orElseThrow(() -> new RuntimeException("CatCatalogoD no encontrado: " + dto.getCvcNombreComun()));
            entity.setCvcNombreComun(nombreComun);
        }

        if (dto.getCvcTipoProducto() != null) {
            CatCatalogoD tipoProducto = catCatalogoDRepository.findById(dto.getCvcTipoProducto())
                    .orElseThrow(() -> new RuntimeException("CatCatalogoD no encontrado: " + dto.getCvcTipoProducto()));
            entity.setCvcTipoProducto(tipoProducto);
        }

        if (dto.getCvePais() != null) {
            CatPais pais = catPaisRepository.findById(dto.getCvePais())
                    .orElseThrow(() -> new RuntimeException("CatPais no encontrado: " + dto.getCvePais()));
            entity.setCvePais(pais);
        }

        CatCombinacionSg saved = catCombinacionSgRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatCombinacionSgDTO update(Long id, CatCombinacionSgDTO dto) {

        CatCombinacionSg entity = catCombinacionSgRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatCombinacionSg no encontrado con id: " + id));

        if (dto.getFecIniVigencia() != null) entity.setFecIniVigencia(dto.getFecIniVigencia());
        if (dto.getFecFinVigencia() != null) entity.setFecFinVigencia(dto.getFecFinVigencia());
        if (dto.getBlnActivo() != null) entity.setBlnActivo(dto.getBlnActivo());
        if (dto.getIdeTipoCertificadoMerc() != null) entity.setIdeTipoCertificadoMerc(dto.getIdeTipoCertificadoMerc());


        if (dto.getId() != null) {

            entity.setId(dto.getId());
        }
        if (dto.getCvcEspecie() != null) {
            CatCatalogoD especie = catCatalogoDRepository.findById(dto.getCvcEspecie())
                    .orElseThrow(() -> new RuntimeException("CatCatalogoD no encontrado: " + dto.getCvcEspecie()));
            entity.setCvcEspecie(especie);
        }

        if (dto.getCvcFuncionZootecnica() != null) {
            CatCatalogoD funcion = catCatalogoDRepository.findById(dto.getCvcFuncionZootecnica())
                    .orElseThrow(() -> new RuntimeException("CatCatalogoD no encontrado: " + dto.getCvcFuncionZootecnica()));
            entity.setCvcFuncionZootecnica(funcion);
        }

        if (dto.getCvcNombreComun() != null) {
            CatCatalogoD nombreComun = catCatalogoDRepository.findById(dto.getCvcNombreComun())
                    .orElseThrow(() -> new RuntimeException("CatCatalogoD no encontrado: " + dto.getCvcNombreComun()));
            entity.setCvcNombreComun(nombreComun);
        }

        if (dto.getCvcTipoProducto() != null) {
            CatCatalogoD tipoProducto = catCatalogoDRepository.findById(dto.getCvcTipoProducto())
                    .orElseThrow(() -> new RuntimeException("CatCatalogoD no encontrado: " + dto.getCvcTipoProducto()));
            entity.setCvcTipoProducto(tipoProducto);
        }

        if (dto.getCvePais() != null) {
            CatPais pais = catPaisRepository.findById(dto.getCvePais())
                    .orElseThrow(() -> new RuntimeException("CatPais no encontrado: " + dto.getCvePais()));
            entity.setCvePais(pais);
        }

        CatCombinacionSg saved = catCombinacionSgRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CombinacionReqResponseDTO listTipoCertificado(String id) {
        List<SelectCombinacionReqDTO> select = catCombinacionSgRepository
                .listTipoCertificado(id);

        List<String> claves =
                catCombinacionSgRepository.findCatalogoClaves(id);

        Long nextId = claves.stream()
                .map(c -> c.replaceAll("[^0-9]", ""))
                .filter(s -> !s.isEmpty())
                .mapToLong(Long::parseLong)
                .max()
                .orElse(0) + 1;

        return new CombinacionReqResponseDTO(select, nextId);

    }

    @Override
    public CombinacionReqUpdateDTO listUpdateTipoCertificado(String tipoCertificado, String catalogo) {

        List<CombinacionReqUpdateResponseDTO> especie = List.of();
        List<CombinacionReqUpdateResponseDTO> zootecnica = List.of();
        List<CombinacionReqUpdateResponseDTO> mercancia = List.of();
        List<CombinacionReqUpdateResponseDTO> tipoProd = List.of();

        if ("TICERM.AN".equals(tipoCertificado)) {

            if ("SGESP".equals(catalogo)) {
                especie = catCombinacionSgRepository.findCatalogo(catalogo)
                        .stream()
                        .map(this::mapToDTOCatalogo)
                        .toList();
            }

            if ("SGFZOO".equals(catalogo)) {
                zootecnica = catCombinacionSgRepository.findCatalogo(catalogo)
                        .stream()
                        .map(this::mapToDTOCatalogo)
                        .toList();
            }

            if ("SGNCOM".equals(catalogo)) {
                mercancia = catCombinacionSgRepository.findCatalogo(catalogo)
                        .stream()
                        .map(this::mapToDTOCatalogo)
                        .toList();
            }
        }

        if ("TICERM.SOA".equals(tipoCertificado)) {

            if ("SGESP".equals(catalogo)) {
                especie = catCombinacionSgRepository.findCatalogo(catalogo)
                        .stream()
                        .map(this::mapToDTOCatalogo)
                        .toList();
            }

            if ("SGFZOO".equals(catalogo)) {
                zootecnica = catCombinacionSgRepository.findCatalogo(catalogo)
                        .stream()
                        .map(this::mapToDTOCatalogo)
                        .toList();
            }

            if ("SGNCOP".equals(catalogo)) {
                mercancia = catCombinacionSgRepository.findCatalogo(catalogo)
                        .stream()
                        .map(this::mapToDTOCatalogo)
                        .toList();
            }

            if ("SGPPRO".equals(catalogo)) {
                tipoProd = catCombinacionSgRepository.findCatalogo(catalogo)
                        .stream()
                        .map(this::mapToDTOCatalogo)
                        .toList();
            }
        }

        if ("TICERM.QFBA".equals(tipoCertificado)) {

            if ("SGNCOM".equals(catalogo)) {
                mercancia = catCombinacionSgRepository.findCatalogo(catalogo)
                        .stream()
                        .map(this::mapToDTOCatalogo)
                        .toList();
            }

            if ("SGPQFB".equals(catalogo)) {
                tipoProd = catCombinacionSgRepository.findCatalogo(catalogo)
                        .stream()
                        .map(this::mapToDTOCatalogo)
                        .toList();
            }
        }

        CombinacionReqUpdateDTO response = new CombinacionReqUpdateDTO();
        response.setEspecie(especie);
        response.setZootecnica(zootecnica);
        response.setMercancia(mercancia);
        response.setTipoProd(tipoProd);

        return response;
    }

    private CatCombinacionSgDTO mapToDTO(CatCombinacionSg entity) {
        return CatCombinacionSgDTO.builder()
                .id(entity.getId())
                .cvcEspecie(entity.getCvcEspecie() != null ? entity.getCvcEspecie().getCveCatalogo() : null)
                .descEspecie(entity.getCvcEspecie() != null ? entity.getCvcEspecie().getDescGenerica1() : null)
                .cvcFuncionZootecnica(entity.getCvcFuncionZootecnica() != null ? entity.getCvcFuncionZootecnica().getCveCatalogo() : null)
                .descFuncionZootecnica(entity.getCvcFuncionZootecnica() != null ? entity.getCvcFuncionZootecnica().getDescGenerica1() : null)
                .cvcNombreComun(entity.getCvcNombreComun() != null ? entity.getCvcNombreComun().getCveCatalogo() : null)
                .descNombreComun(entity.getCvcNombreComun() != null ? entity.getCvcNombreComun().getDescGenerica1() : null)
                .cvcTipoProducto(entity.getCvcTipoProducto() != null ? entity.getCvcTipoProducto().getCveCatalogo() : null)
                .descTipoProducto(entity.getCvcTipoProducto() != null ? entity.getCvcTipoProducto().getDescGenerica1() : null)
                .cvePais(entity.getCvePais() != null ? entity.getCvePais().getCvePais() : null)
                .nombrePais(entity.getCvePais() != null ? entity.getCvePais().getNombre() : null)
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .ideTipoCertificadoMerc(entity.getIdeTipoCertificadoMerc())
                .build();
    }

    private CombinacionReqUpdateResponseDTO mapToDTOCatalogo(CombinacionReqUpdateTempDTO dto){

        Long id = Long.parseLong(
                dto.getCveCatalogo().replaceAll("[^0-9]", "")
        );

        return new CombinacionReqUpdateResponseDTO(
                id,
                dto.getDescripcion(),
                dto.getCveCatalogo(),
                dto.getFecIniVigencia(),
                dto.getFecFinVigencia(),
                dto.getBlnActivo()
        );
    }
}
