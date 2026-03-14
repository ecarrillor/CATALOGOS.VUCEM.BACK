package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatIsotopoFraccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatIsotopoFraccionRepository extends JpaRepository<CatIsotopoFraccion, Short> {

}
