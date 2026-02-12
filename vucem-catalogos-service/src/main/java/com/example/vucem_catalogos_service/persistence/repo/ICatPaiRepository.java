package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatPai;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICatPaiRepository extends JpaRepository<CatPai, String> {
}
