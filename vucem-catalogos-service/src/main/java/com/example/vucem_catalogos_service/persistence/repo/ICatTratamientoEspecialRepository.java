package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatTratamientoEspecialDTO;
import com.example.vucem_catalogos_service.model.entity.CatTratamientoEspecial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICatTratamientoEspecialRepository extends JpaRepository<CatTratamientoEspecial, Short> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatTratamientoEspecialDTO(
                e.id,
                e.nombre,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo,
                e.ideClasifTratamiento
            )
            FROM CatTratamientoEspecial e
            WHERE (:search IS NULL OR
                LOWER(CAST(e.id AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.ideClasifTratamiento) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))
              AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatTratamientoEspecialDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatTratamientoEspecialDTO(
                e.id,
                e.nombre,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo,
                e.ideClasifTratamiento
            )
            FROM CatTratamientoEspecial e
            WHERE e.id = :id
            """)
    Optional<CatTratamientoEspecialDTO> findByTratamientoEspecialDTO(@Param("id") Short id);
}
