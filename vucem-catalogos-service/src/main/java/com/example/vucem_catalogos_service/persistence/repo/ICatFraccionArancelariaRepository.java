package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatFraccionArancelaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICatFraccionArancelariaRepository extends JpaRepository<CatFraccionArancelaria, String> {
}
