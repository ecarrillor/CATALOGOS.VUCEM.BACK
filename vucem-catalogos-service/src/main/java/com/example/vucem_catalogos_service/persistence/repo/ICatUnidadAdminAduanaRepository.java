package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatUnidadAdminAduanaDTO;
import com.example.vucem_catalogos_service.model.entity.CatUnidadAdminAduana;
import com.example.vucem_catalogos_service.model.entity.CatUnidadAdminAduanaId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICatUnidadAdminAduanaRepository extends JpaRepository<CatUnidadAdminAduana, CatUnidadAdminAduanaId> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatUnidadAdminAduanaDTO(
                e.id.cveUnidadAdministrativa,
                e.id.cveAduana,
                e.cveUnidadAdministrativa.nombre,
                e.cveAduana.nombre,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatUnidadAdminAduana e
            WHERE (:search IS NULL OR
                LOWER(e.id.cveUnidadAdministrativa) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.id.cveAduana) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.cveUnidadAdministrativa.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.cveAduana.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))
              AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatUnidadAdminAduanaDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatUnidadAdminAduanaDTO(
                e.id.cveUnidadAdministrativa,
                e.id.cveAduana,
                e.cveUnidadAdministrativa.nombre,
                e.cveAduana.nombre,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatUnidadAdminAduana e
            WHERE e.id.cveUnidadAdministrativa = :cveUnidadAdministrativa
              AND e.id.cveAduana = :cveAduana
            """)
    Optional<CatUnidadAdminAduanaDTO> findByUnidadAdminAduanaDTO(
            @Param("cveUnidadAdministrativa") String cveUnidadAdministrativa,
            @Param("cveAduana") String cveAduana
    );
}
