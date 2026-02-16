package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatTipoDictamen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatTipoDictamanRepository extends JpaRepository<CatTipoDictamen, Short>,
        JpaSpecificationExecutor<CatTipoDictamen> {
}
