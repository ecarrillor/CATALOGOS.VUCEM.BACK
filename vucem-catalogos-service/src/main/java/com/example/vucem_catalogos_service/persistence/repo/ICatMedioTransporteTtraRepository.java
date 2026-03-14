package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatMedioTransporteTtraDTO;
import com.example.vucem_catalogos_service.model.entity.CatMedioTransporteTtra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICatMedioTransporteTtraRepository extends JpaRepository<CatMedioTransporteTtra, Integer> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatMedioTransporteTtraDTO(
                e.id,
                t.id,
                t.descModalidad,
                e.ideMedioTransporteGob,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatMedioTransporteTtra e
            JOIN e.idTipoTramite t
            WHERE (:search IS NULL OR
                LOWER(CAST(e.id AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(CAST(t.id AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(t.descModalidad) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.ideMedioTransporteGob) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatMedioTransporteTtraDTO> search(@Param("search") String search,
                                            @Param("activo") Boolean activo,
                                            Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatMedioTransporteTtraDTO(
                   e.id,
                t.id,
                t.descModalidad,
                e.ideMedioTransporteGob,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatMedioTransporteTtra e
            JOIN e.idTipoTramite t
            WHERE e.id = :id
            """)
    Optional<CatMedioTransporteTtraDTO> findByMedioTransporteTtraDTO(@Param("id") Integer id);
}
