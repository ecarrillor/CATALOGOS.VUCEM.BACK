package com.example.vucem_catalogos_service.persistence.repo;


import com.example.vucem_catalogos_service.model.entity.CatIsotopoTtra;
import com.example.vucem_catalogos_service.model.entity.CatIsotopoTtraId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatIsotopoTtraRepository extends JpaRepository<CatIsotopoTtra, CatIsotopoTtraId> {
}
