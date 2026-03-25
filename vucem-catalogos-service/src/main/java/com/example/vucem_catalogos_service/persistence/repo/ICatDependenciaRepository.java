package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatNormalOficialDTO;
import com.example.vucem_catalogos_service.model.dto.Dependencia.CatDependenciaResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatDependencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatDependenciaRepository extends JpaRepository<CatDependencia, Long> {


    @Query(value = """
            SELECT new com.example.vucem_catalogos_service.model.dto.Dependencia.CatDependenciaResponseDTO(
                a.id,
                a.nombre,
                a.acronimo,
                b.nombre,
                b.cveCalendario,
                a.blnTramitesVu,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.blnActivo
            )
            FROM CatDependencia a
            JOIN a.cveCalendario b
            WHERE
                     (
                           :search IS NULL OR
                           LOWER(CAST(a.id AS string)) LIKE :search OR
                           LOWER(a.nombre) LIKE :search OR
                           LOWER(a.acronimo) LIKE :search OR
                           LOWER(b.nombre) LIKE :search OR
                           LOWER(b.cveCalendario) LIKE :search OR
                           LOWER(CAST(a.fecIniVigencia AS string)) LIKE :search OR
                           LOWER(CAST(a.fecFinVigencia AS string)) LIKE :search
                     )
                           AND
                           (
                               :activo IS NULL OR a.blnActivo = :activo
                           )
            """,
            countQuery = """
            SELECT COUNT(a)
            FROM CatDependencia a
            JOIN a.cveCalendario b
            WHERE
                     (
                           :search IS NULL OR
                           LOWER(CAST(a.id AS string)) LIKE :search OR
                           LOWER(a.nombre) LIKE :search OR
                           LOWER(a.acronimo) LIKE :search OR
                           LOWER(b.nombre) LIKE :search OR
                           LOWER(b.cveCalendario) LIKE :search OR
                           LOWER(CAST(a.fecIniVigencia AS string)) LIKE :search OR
                           LOWER(CAST(a.fecFinVigencia AS string)) LIKE :search
                     )
                           AND
                           (
                               :activo IS NULL OR a.blnActivo = :activo
                           )
            """)
    Page<CatDependenciaResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );

    List<CatDependencia> findByBlnActivoTrue();


    @Query("""
        SELECT new com.example.vucem_catalogos_service.model.dto.Dependencia.CatDependenciaResponseDTO(
                a.id,
                a.nombre,
                a.acronimo,
                b.nombre,
                b.cveCalendario,
                a.blnTramitesVu,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.blnActivo
        )
        FROM CatDependencia a
                JOIN a.cveCalendario b
        WHERE a.blnActivo = true
        """)
    List<CatDependenciaResponseDTO> findAllActive();
}

