package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatMoneda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatMonedaRepository extends JpaRepository<CatMoneda, String>,
        JpaSpecificationExecutor<CatMoneda> {

    List<CatMoneda> findByBlnActivoTrue();
}
