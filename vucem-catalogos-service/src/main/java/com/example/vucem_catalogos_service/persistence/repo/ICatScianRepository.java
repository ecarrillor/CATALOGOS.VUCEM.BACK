package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatScianDTO;
import com.example.vucem_catalogos_service.model.entity.CatScian;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICatScianRepository extends JpaRepository<CatScian, String> {

    @Query(value = """
            SELECT new com.example.vucem_catalogos_service.model.dto.CatScianDTO(
                e.cveScian,
                e.descScian,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo,
                e.giro
            )
            FROM CatScian e
            WHERE (:search IS NULL OR LOWER(e.cveScian) LIKE :search
                OR LOWER(e.descScian) LIKE :search
                OR LOWER(e.giro) LIKE :search)
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """,
            countQuery = """
            SELECT COUNT(e)
            FROM CatScian e
            WHERE (:search IS NULL OR LOWER(e.cveScian) LIKE :search
                OR LOWER(e.descScian) LIKE :search
                OR LOWER(e.giro) LIKE :search)
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatScianDTO> search(@Param("search") String search,
                              @Param("activo") Boolean activo,
                              Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatScianDTO(
                e.cveScian,
                e.descScian,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo,
                e.giro
            )
            FROM CatScian e
            WHERE e.cveScian = :cveScian
            """)
    Optional<CatScianDTO> findByScianDTO(@Param("cveScian") String cveScian);
}
