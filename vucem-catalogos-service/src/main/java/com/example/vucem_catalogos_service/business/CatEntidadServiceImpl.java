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
    public CatEntidad updateBlnActivo(String cveEntidad, Boolean blnActivo) {
        CatEntidad entidad = repository.findById(cveEntidad)
                .orElseThrow(() -> new RuntimeException("Entidad no encontrada"));

        entidad.setBlnActivo(blnActivo);

        return repository.save(entidad);
    }

    @Override
    public List<CatPais> findAllPaises() {
        return paisRepository.findAll();
    }
}
