package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatAduanaService;
import com.example.vucem_catalogos_service.model.entity.CatAduana;
import com.example.vucem_catalogos_service.model.entity.CatAduanaClasifProd;
import com.example.vucem_catalogos_service.model.entity.CatEntidad;
import com.example.vucem_catalogos_service.model.entity.CatTipoAduana;
import com.example.vucem_catalogos_service.persistence.repo.ICatAduanaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatEntidadRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoAduanaRepository;
import com.example.vucem_catalogos_service.persistence.specification.GenericFilterSpecification;
import com.example.vucem_catalogos_service.persistence.specification.GenericSearchSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CatAduanaServiceImpl implements ICatAduanaService {

    @Autowired
    private ICatAduanaRepository repository;

    @Autowired
    private ICatTipoAduanaRepository tipoAduanaRepository;

    @Autowired
    private ICatEntidadRepository entidadRepository;


    @Override
    public Page<CatAduana> catAduanaListAll(int page, int size, String search) {
        Pageable pageable = PageRequest.of(page, size);
        if (search == null || search.isEmpty()) {
            return repository.findAll(pageable);
        }
        return repository.findByNombreContainingIgnoreCaseOrCveAduanaContainingIgnoreCase(search, search, pageable);
    }

    @Override
    public CatAduana crearAduana(CatAduana catAduana) {
        CatTipoAduana tipoAduana = tipoAduanaRepository
                .findById(catAduana.getTipoAduana().getCveTipoAduana())
                .orElseThrow(() -> new RuntimeException("TipoAduana no encontrado"));

        CatEntidad entidad = entidadRepository
                .findById(catAduana.getEntidad().getCveEntidad())
                .orElseThrow(() -> new RuntimeException("Entidad no encontrada"));

        catAduana.setTipoAduana(tipoAduana);
        catAduana.setEntidad(entidad);

        return repository.save(catAduana);
    }

    @Override
    public CatAduana findByCveAduana(String cveAduana) {
        return repository.findById(cveAduana)
                .orElseThrow(() -> new RuntimeException("Entidad no encontrada"));
    }

    @Override
    public CatAduana updateAduana(String cveAduana, CatAduana request) {
        CatAduana aduana = repository.findById(cveAduana)
                .orElseThrow(() -> new RuntimeException("Aduana no encontrada"));

        if (request.getNombre() != null) {
            aduana.setNombre(request.getNombre());
        }

        if (request.getFecCaptura() != null) {
            aduana.setFecCaptura(request.getFecCaptura());
        }

        if (request.getFecIniVigencia() != null) {
            aduana.setFecIniVigencia(request.getFecIniVigencia());
        }

        if (request.getFecFinVigencia() != null) {
            aduana.setFecFinVigencia(request.getFecFinVigencia());
        }

        if (request.getBlnActivo() != null) {
            aduana.setBlnActivo(request.getBlnActivo());
        }

        if (request.getCorreoElectronico() != null) {
            aduana.setCorreoElectronico(request.getCorreoElectronico());
        }

        if (request.getTipoAduana() != null &&
                request.getTipoAduana().getCveTipoAduana() != null) {

            CatTipoAduana tipo = new CatTipoAduana();
            tipo.setCveTipoAduana(
                    request.getTipoAduana().getCveTipoAduana()
            );
            aduana.setTipoAduana(tipo);
        }

        if (request.getEntidad() != null &&
                request.getEntidad().getCveEntidad() != null) {

            CatEntidad entidad = new CatEntidad();
            entidad.setCveEntidad(
                    request.getEntidad().getCveEntidad()
            );
            aduana.setEntidad(entidad);
        }

        return repository.save(aduana);
    }

    @Override
    public List<CatTipoAduana> getAllTiposAduana() {
        return tipoAduanaRepository.findAll();
    }

    @Override
    public List<CatEntidad> getAllEntidades() {
        return entidadRepository.findAll();
    }
}
