package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatEntidadService;
import com.example.vucem_catalogos_service.model.entity.CatEntidad;
import com.example.vucem_catalogos_service.model.entity.CatPais;
import com.example.vucem_catalogos_service.persistence.repo.ICatEntidadRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatPaisRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CatEntidadServiceImpl implements ICatEntidadService {

    @Autowired
    private ICatEntidadRepository repository;

    @Autowired
    private ICatPaisRepository paisRepository;


    @Override
    public Page<CatEntidad> catEntidadListAll(int page, int size, String search) {
        Pageable pageable = PageRequest.of(page, size);
        if (search == null || search.isEmpty()) {
            return repository.findAll(pageable);
        }
        return repository.findByNombreContainingIgnoreCaseOrCveEntidadContainingIgnoreCase(search, search, pageable);
    }

    @Override
    public CatEntidad crearEntidad(CatEntidad entidad) {
        return repository.save(entidad);
    }

    @Override
    public CatEntidad findByCveEntidad(String cveEntidad) {
        return repository.findById(cveEntidad)
                .orElseThrow(() -> new RuntimeException("Entidad no encontrada"));
    }

    @Override
    public CatEntidad updateEntidad(String cveEntidad, CatEntidad catEntidad) {
        CatEntidad entidad = repository.findById(cveEntidad)
                .orElseThrow(() -> new RuntimeException("Entidad no encontrada"));


        if (catEntidad.getNombre() != null) {
            entidad.setNombre(catEntidad.getNombre());
        }

        if (catEntidad.getCodEntidadIdc() != null) {
            entidad.setCodEntidadIdc(catEntidad.getCodEntidadIdc());
        }

        if (catEntidad.getFecIniVigencia() != null) {
            entidad.setFecIniVigencia(catEntidad.getFecIniVigencia());
        }

        if (catEntidad.getFecFinVigencia() != null) {
            entidad.setFecFinVigencia(catEntidad.getFecFinVigencia());
        }

        if (catEntidad.getBlnActivo() != null) {
            entidad.setBlnActivo(catEntidad.getBlnActivo());
        }

        if (catEntidad.getCvePais() != null) {
            entidad.setCvePais(catEntidad.getCvePais());
        }

        return repository.save(entidad);
    }

    @Override
    public List<CatPais> findAllPaises() {
        return paisRepository.findAll();
    }
}
