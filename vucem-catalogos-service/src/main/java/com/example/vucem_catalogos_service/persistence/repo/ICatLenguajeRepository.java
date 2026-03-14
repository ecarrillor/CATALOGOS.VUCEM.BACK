package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatLenguaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatLenguajeRepository extends JpaRepository<CatLenguaje,String>,
        JpaSpecificationExecutor<CatLenguaje> {
}
