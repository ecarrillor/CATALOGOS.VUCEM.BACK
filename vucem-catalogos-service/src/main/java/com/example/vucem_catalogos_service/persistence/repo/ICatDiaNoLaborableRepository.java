package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatDiaNoLaborable;
import com.example.vucem_catalogos_service.model.entity.CatDiaNoLaborableId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatDiaNoLaborableRepository extends JpaRepository<CatDiaNoLaborable, CatDiaNoLaborableId> {
}
