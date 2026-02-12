package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatLocalidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICatLocalidadRepository extends JpaRepository<CatLocalidad,String> {
}
