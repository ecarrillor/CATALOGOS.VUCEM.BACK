package com.example.vucem_catalogos_service.repository;

import com.example.vucem_catalogos_service.model.entity.CatAduana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatAduanaRepository extends JpaRepository<CatAduana, String> {
}
