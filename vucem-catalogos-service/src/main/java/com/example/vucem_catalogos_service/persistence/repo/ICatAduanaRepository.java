package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatAduana;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatAduanaRepository extends JpaRepository<CatAduana, String> {

    Page<CatAduana> findByNombreContainingIgnoreCaseOrCveAduanaContainingIgnoreCase(String search, String search1, Pageable pageable);

    List<CatAduana> findByBlnActivoTrueOrderByNombreAsc();
}
