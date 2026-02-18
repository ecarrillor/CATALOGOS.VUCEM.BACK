package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatPais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICatPaisRepository extends JpaRepository<CatPais, String> {
}
