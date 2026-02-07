package com.example.vucem_catalogos_service.repository;

import com.example.vucem_catalogos_service.model.entity.CatAga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatAgaRepository extends JpaRepository<CatAga, String> {
}
