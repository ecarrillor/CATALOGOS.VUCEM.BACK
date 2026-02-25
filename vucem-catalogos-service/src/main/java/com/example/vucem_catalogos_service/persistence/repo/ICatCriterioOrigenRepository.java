package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatCriterioOrigen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatCriterioOrigenRepository extends JpaRepository<CatCriterioOrigen, String> {
    List<CatCriterioOrigen> findByBlnActivoTrue();
}
