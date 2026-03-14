package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatEsquemaRegla8;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatEsquemaRegla8Repository extends JpaRepository<CatEsquemaRegla8, Short> ,
        JpaSpecificationExecutor<CatEsquemaRegla8> {
}
