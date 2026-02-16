package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatIdentificadorPrevalidador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatIdentificadorPrevalidadorRepository extends JpaRepository<CatIdentificadorPrevalidador, Long>,
        JpaSpecificationExecutor<CatIdentificadorPrevalidador> {
}
