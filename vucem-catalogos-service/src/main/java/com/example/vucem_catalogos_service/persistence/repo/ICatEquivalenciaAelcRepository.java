package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatEquivalenciaAelcDTO;
import com.example.vucem_catalogos_service.model.entity.CatEquivalenciaAelc;
import com.example.vucem_catalogos_service.model.entity.CatEquivalenciaAelcId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ICatEquivalenciaAelcRepository extends JpaRepository<CatEquivalenciaAelc, CatEquivalenciaAelcId> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatEquivalenciaAelcDTO(
                e.id.fecIniVigencia,
                e.id.cveMoneda,
                m.nombre,           
                e.valor,
                e.fecCaptura,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatEquivalenciaAelc e
            JOIN e.moneda m
            WHERE (:search IS NULL OR
                LOWER(CAST(e.id.fecIniVigencia AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.id.cveMoneda) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(m.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(CAST(e.valor AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(CAST(e.fecCaptura AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatEquivalenciaAelcDTO> search(@Param("search") String search,
                                         @Param("activo") Boolean activo,
                                         Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatEquivalenciaAelcDTO(
               e.id.fecIniVigencia,
                e.id.cveMoneda,
                m.nombre,           
                e.valor,
                e.fecCaptura,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatEquivalenciaAelc e
            JOIN e.moneda m
            WHERE e.id.fecIniVigencia = :fecIniVigencia AND e.id.cveMoneda = :cveMoneda
            """)
    Optional<CatEquivalenciaAelcDTO> findByEquivalenciaAelcDTO(@Param("fecIniVigencia") LocalDate fecIniVigencia,
                                                                @Param("cveMoneda") String cveMoneda);
}
