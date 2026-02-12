package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatClasifToxicologicaTtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatClasifToxicologicaTtraRepository extends JpaRepository<CatClasifToxicologicaTtra, Short> {
}
