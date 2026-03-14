package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatEntidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatEntidadRepository extends JpaRepository<CatEntidad, String> {

    Page<CatEntidad> findByNombreContainingIgnoreCaseOrCveEntidadContainingIgnoreCase(
            String nombre,
            String cveEntidad,
            Pageable pageable
    );

    List<CatEntidad> findByBlnActivoTrue();
}
