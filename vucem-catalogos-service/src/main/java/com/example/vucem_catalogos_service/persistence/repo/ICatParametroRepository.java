package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatParametro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICatParametroRepository extends JpaRepository<CatParametro, String> {
}
