package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatCa;
import com.example.vucem_catalogos_service.business.Interface.ICatCasService;
import com.example.vucem_catalogos_service.model.dto.CatCas.CatCaRequestDTO;
import com.example.vucem_catalogos_service.model.dto.CatCas.CatCaResponseDTO;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatCasFraccionTtra;
import com.example.vucem_catalogos_service.model.entity.CatFraccionTtra;
import com.example.vucem_catalogos_service.model.entity.CatTipoTramite;
import com.example.vucem_catalogos_service.persistence.repo.ICatCasRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatFraccionTtraRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CatCasServiceImpl implements ICatCasService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ICatCasRepository repository;

    @Autowired
    private ICatTipoTramiteRepo catTipoTramiteRepo;

    @Autowired
    private ICatFraccionTtraRepository catFraccionTtraRepository;

    @Override
    public PageResponseDTO<CatCaResponseDTO> listAll(String search, Long idTipoTramite,Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if (search != null && !search.isBlank()) {

            String s = search.trim().toLowerCase();

            if (s.equals("activo")) {
                activo = true;
            }
            else if (s.equals("inactivo")) {
                activo = false;
            }
            else {
                texto = "%" + search.trim().toLowerCase() + "%";
            }
        }


        Page<CatCaResponseDTO> page = repository.search(texto, activo, idTipoTramite, pageable);

        return PageResponseDTO.<CatCaResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatCaResponseDTO crear(CatCaRequestDTO dto, Long idTipoTramite) {

        // 1️⃣ Validar que no exista el CAS
        if (repository.existsById(dto.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }

        // 2️⃣ Validar TipoTramite
        CatTipoTramite tipoTramite = catTipoTramiteRepo.findById(idTipoTramite)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No existe TipoTramite con id: " + idTipoTramite
                ));


        CatCa cas = new CatCa();
        cas.setId(dto.getId());
        cas.setDescCas(dto.getDescCas());
        cas.setFecIniVigencia(dto.getFecIniVigencia());
        cas.setFecFinVigencia(dto.getFecFinVigencia());
        cas.setBlActivo(true);

        entityManager.persist(cas);



        List<CatFraccionTtra> fracciones =
                catFraccionTtraRepository.findByIdTipoTramiteId(tipoTramite.getId());

        if (fracciones.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El TipoTramite no tiene fracciones asociadas"
            );
        }

        Short maxId = repository.findMaxId();
        short nuevoId = (short) (maxId == null ? 1 : maxId + 1);

        // 4️⃣ Crear relaciones
        for (CatFraccionTtra fraccion : fracciones) {

            CatCasFraccionTtra relacion = new CatCasFraccionTtra();

            relacion.setId(nuevoId++);
            relacion.setIdCas(cas);
            relacion.setIdTipoTramite(tipoTramite); // 👈 obligatorio
            relacion.setCveFraccion(fraccion.getCveFraccion()); // 👈 objeto completo
            relacion.setBlnRotterdam(false);
            relacion.setBlActivo(true);
            relacion.setFecIniVigencia(LocalDate.now());

            entityManager.persist(relacion);
        }

        entityManager.flush();

        return toResponseDTO(cas);
    }

    @Override
    public CatCaResponseDTO findById(Short id) {
        CatCa entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Registro no encontrado con id: " + id
                ));
        return toResponseDTO(entity);
    }

    @Override
    public CatCaResponseDTO update(Short id, CatCaRequestDTO dto) {

        CatCa entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Registro no encontrado con id: " + id
                ));

        if (dto.getDescCas() != null) {
            entity.setDescCas(dto.getDescCas());
        }
        if (dto.getFecIniVigencia() != null) {
            entity.setFecIniVigencia(dto.getFecIniVigencia());
        }
        if (dto.getFecFinVigencia() != null) {
            entity.setFecFinVigencia(dto.getFecFinVigencia());
        }
        if (dto.getBlActivo() != null) {
            entity.setBlActivo(dto.getBlActivo());
        }

        return toResponseDTO(repository.save(entity));
    }

    @Override
    public List<ClasifProductoTraDTO> listadoTipoTramite() {
        return repository.listadoTipoTramite();
    }

    private CatCaResponseDTO toResponseDTO(CatCa entity) {
        return CatCaResponseDTO.builder()
                .id(entity.getId())
                .descCas(entity.getDescCas())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blActivo(entity.getBlActivo())
                .build();
    }

    @Override
    public ClasifProductoTraDTO lastCas() {
        return repository.findTopByOrderByIdDesc()
                .map(e -> new ClasifProductoTraDTO(e.getId().longValue(), e.getDescCas()))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existen registros en Chemical Abstracts Service"));
    }
}
