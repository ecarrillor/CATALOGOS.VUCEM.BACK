package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatUnidadMedida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatUnidadMedidaRepository extends JpaRepository<CatUnidadMedida, String> {

    List<CatUnidadMedida> findByBlnActivoTrue();
}
