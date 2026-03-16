package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatGeneroService;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.persistence.repo.ICatGeneroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class GeneroServiceImpl implements ICatGeneroService {

   @Autowired
   private ICatGeneroRepository repository;

    @Override
    public ClasifProductoTraDTO lastGenero() {
        return repository.findTopByOrderByIdDesc()
                .map(e -> new ClasifProductoTraDTO(e.getId().longValue(), e.getDescGenero()))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existen registros en Genero"));
    }

}
