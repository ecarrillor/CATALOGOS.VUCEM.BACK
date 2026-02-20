package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatUnidadMedida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ICatUnidadMedidaRepository extends JpaRepository<CatUnidadMedida, String>,
        JpaSpecificationExecutor<CatUnidadMedida> {
}
