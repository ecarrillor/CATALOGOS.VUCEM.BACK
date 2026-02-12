package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatMoneda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICatMonedaRepository extends JpaRepository<CatMoneda, String> {
}
