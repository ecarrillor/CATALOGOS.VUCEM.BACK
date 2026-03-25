package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatUsoMercanciaTtraDTO;
import com.example.vucem_catalogos_service.model.entity.CatUsoMercanciaTtra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICatUsoMercanciaTtraRepository extends JpaRepository<CatUsoMercanciaTtra, Short> {

    @Query(value = """
            SELECT new com.example.vucem_catalogos_service.model.dto.CatUsoMercanciaTtraDTO(
                e.id,
                CASE WHEN tr.id IS NOT NULL THEN tr.id ELSE NULL END,
                CASE WHEN tr.id IS NOT NULL THEN tr.descModalidad ELSE NULL END,
                e.descUsoMercancia,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatUsoMercanciaTtra e
            LEFT JOIN e.idTipoTramite tr
            WHERE (:search IS NULL OR :search = '' OR
            LOWER(e.descUsoMercancia) LIKE :search OR
            LOWER(tr.descModalidad) LIKE :search OR
        
            CAST(tr.id AS string) LIKE :search OR
            CAST(e.id AS string) LIKE :search OR
        
            CAST(e.fecIniVigencia AS string) LIKE :search OR
            CAST(e.fecFinVigencia AS string) LIKE :search)
            AND (:activo IS NULL OR e.blnActivo = :activo)""",
            countQuery = """
            SELECT COUNT(e)
            FROM CatUsoMercanciaTtra e
            LEFT JOIN e.idTipoTramite tr
            WHERE (:search IS NULL OR :search = '' OR
            LOWER(e.descUsoMercancia) LIKE :search OR
            LOWER(tr.descModalidad) LIKE :search OR
        
            CAST(tr.id AS string) LIKE :search OR
            CAST(e.id AS string) LIKE :search OR
        
            CAST(e.fecIniVigencia AS string) LIKE :search OR
            CAST(e.fecFinVigencia AS string) LIKE :search)
            AND (:activo IS NULL OR e.blnActivo = :activo)""")
    Page<CatUsoMercanciaTtraDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatUsoMercanciaTtraDTO(
               e.id,
                CASE WHEN tr.id IS NOT NULL THEN tr.id ELSE NULL END,
                CASE WHEN tr.id IS NOT NULL THEN tr.descModalidad ELSE NULL END,
                e.descUsoMercancia,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatUsoMercanciaTtra e
            LEFT JOIN e.idTipoTramite tr
            WHERE e.id = :id
            """)
    Optional<CatUsoMercanciaTtraDTO> findByUsoMercanciaTtraDTO(@Param("id") Short id);
}
