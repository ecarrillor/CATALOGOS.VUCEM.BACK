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
import java.util.Optional;

@Repository
public interface ICatEquivalenciaAelcRepository extends JpaRepository<CatEquivalenciaAelc, CatEquivalenciaAelcId> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatEquivalenciaAelcDTO(
                e.id.fecIniVigencia,
                e.id.nombre,
                e.valor,
                e.fecCaptura,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatEquivalenciaAelc e
            WHERE (:search IS NULL OR LOWER(e.id.cveMoneda) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatEquivalenciaAelcDTO> search(@Param("search") String search,
                                         @Param("activo") Boolean activo,
                                         Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatEquivalenciaAelcDTO(
                e.id.fecIniVigencia,
                e.id.cveMoneda,
                e.valor,
                e.fecCaptura,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatEquivalenciaAelc e
            WHERE e.id.fecIniVigencia = :fecIniVigencia AND e.id.cveMoneda = :cveMoneda
            """)
    Optional<CatEquivalenciaAelcDTO> findByEquivalenciaAelcDTO(@Param("fecIniVigencia") Instant fecIniVigencia,
                                                                @Param("cveMoneda") String cveMoneda);
}
