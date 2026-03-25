package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatSectorProsecDTO;
import com.example.vucem_catalogos_service.model.entity.CatSectorProsec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICatSectorProsecRepository extends JpaRepository<CatSectorProsec, String> {

    @Query(value = """
            SELECT new com.example.vucem_catalogos_service.model.dto.CatSectorProsecDTO(
                e.cveSectorProsec,
                e.nombre,
                e.blnProductorIndirecto,
                e.blnAmpliacionMercancias,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatSectorProsec e
            WHERE (:search IS NULL OR LOWER(e.cveSectorProsec) LIKE :search
                OR LOWER(e.nombre) LIKE :search)
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """,
            countQuery = """
            SELECT COUNT(e)
            FROM CatSectorProsec e
            WHERE (:search IS NULL OR LOWER(e.cveSectorProsec) LIKE :search
                OR LOWER(e.nombre) LIKE :search)
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatSectorProsecDTO> search(@Param("search") String search,
                                     @Param("activo") Boolean activo,
                                     Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatSectorProsecDTO(
                e.cveSectorProsec,
                e.nombre,
                e.blnProductorIndirecto,
                e.blnAmpliacionMercancias,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatSectorProsec e
            WHERE e.cveSectorProsec = :cveSectorProsec
            """)
    Optional<CatSectorProsecDTO> findBySectorProsecDTO(@Param("cveSectorProsec") String cveSectorProsec);

    List<CatSectorProsec> findByBlnActivoTrue();
}
