package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatAprobCertService;
import com.example.vucem_catalogos_service.business.Interface.ICatUnidadAdministrativaService;
import com.example.vucem_catalogos_service.model.dto.CatUnidadAdministrativaNameDTO;
import com.example.vucem_catalogos_service.persistence.repo.ICatAprobCertSeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatUnidadAdministrativaServiceImpl implements ICatUnidadAdministrativaService {
    @Autowired
    private ICatAprobCertSeRepository catAprobCertSeRepository;

    @Override
    public List<CatUnidadAdministrativaNameDTO> findByAll() {
        return catAprobCertSeRepository.findByName();
    }
}
