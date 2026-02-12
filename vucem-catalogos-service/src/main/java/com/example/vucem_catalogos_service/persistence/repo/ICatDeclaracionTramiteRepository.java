package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatDeclaracionTramite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatDeclaracionTramiteRepository extends JpaRepository<CatDeclaracionTramite, Integer> {
}
