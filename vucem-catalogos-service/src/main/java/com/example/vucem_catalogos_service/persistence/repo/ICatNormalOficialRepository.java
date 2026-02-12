package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatNormalOficial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICatNormalOficialRepository extends JpaRepository<CatNormalOficial, Integer> {
}
