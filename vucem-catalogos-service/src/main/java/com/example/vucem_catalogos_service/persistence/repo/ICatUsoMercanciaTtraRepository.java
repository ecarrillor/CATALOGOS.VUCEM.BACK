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

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatUsoMercanciaTtraDTO(
                e.id,
                CASE WHEN e.idTipoTramite IS NOT NULL THEN e.idTipoTramite.id ELSE NULL END,
                CASE WHEN e.idTipoTramite IS NOT NULL THEN e.idTipoTramite.nombre ELSE NULL END,
                e.descUsoMercancia,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatUsoMercanciaTtra e
            WHERE (:search IS NULL OR LOWER(e.descUsoMercancia) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))
              AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatUsoMercanciaTtraDTO> search(
            @Param("search") String search,
            @Param("activo") Short activo,
            Pageable pageable
    );

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatUsoMercanciaTtraDTO(
                e.id,
                CASE WHEN e.idTipoTramite IS NOT NULL THEN e.idTipoTramite.id ELSE NULL END,
                CASE WHEN e.idTipoTramite IS NOT NULL THEN e.idTipoTramite.nombre ELSE NULL END,
                e.descUsoMercancia,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatUsoMercanciaTtra e
            WHERE e.id = :id
            """)
    Optional<CatUsoMercanciaTtraDTO> findByUsoMercanciaTtraDTO(@Param("id") Short id);
}
