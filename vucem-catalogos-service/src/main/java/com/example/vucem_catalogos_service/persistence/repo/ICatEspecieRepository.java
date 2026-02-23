package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatEspecie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatEspecieRepository extends JpaRepository<CatEspecie, Integer> {

    List<CatEspecie> findByBlnActivoTrue();
}
