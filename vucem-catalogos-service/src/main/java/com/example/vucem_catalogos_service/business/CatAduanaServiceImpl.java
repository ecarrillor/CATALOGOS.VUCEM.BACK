package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatAduanaService;
import com.example.vucem_catalogos_service.model.entity.CatAduana;
import com.example.vucem_catalogos_service.model.entity.CatAduanaClasifProd;
import com.example.vucem_catalogos_service.model.entity.CatEntidad;
import com.example.vucem_catalogos_service.persistence.repo.ICatAduanaRepository;
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


    @Override
    public Page<CatAduana> catAduanaListAll(int page, int size, String search) {
        Pageable pageable = PageRequest.of(page, size);
        if (search == null || search.isEmpty()) {
            return repository.findAll(pageable);
        }
        return repository.findByNombreContainingIgnoreCaseOrCveAduanaContainingIgnoreCase(search, search, pageable);
    }
}
