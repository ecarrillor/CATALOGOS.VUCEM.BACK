package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatDeclaracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatDeclaracionRepository extends JpaRepository<CatDeclaracion, String>,
        JpaSpecificationExecutor<CatDeclaracion> {

    List<CatDeclaracion> findByBlnActivoTrue();
}
