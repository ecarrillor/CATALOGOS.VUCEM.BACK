package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatEquivMoneda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICatEquivMonedaRepository extends JpaRepository<CatEquivMoneda, Integer> {
}
