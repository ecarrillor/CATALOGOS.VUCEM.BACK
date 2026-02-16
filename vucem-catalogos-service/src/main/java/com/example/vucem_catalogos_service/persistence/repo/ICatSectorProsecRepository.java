package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatSectorProsec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatSectorProsecRepository extends JpaRepository<CatSectorProsec, String>,
        JpaSpecificationExecutor<CatSectorProsec> {
}
