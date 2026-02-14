package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatCapituloFraccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatCapituloFraccionRepository extends JpaRepository<CatCapituloFraccion, String>,
        JpaSpecificationExecutor<CatCapituloFraccion> {
}
