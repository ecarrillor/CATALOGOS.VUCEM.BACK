package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatMontoExportacionDTO;
import com.example.vucem_catalogos_service.model.entity.CatMontoExportacion;
import com.example.vucem_catalogos_service.model.entity.CatMontoExportacionId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ICatMontoExportacionRepository extends JpaRepository<CatMontoExportacion, CatMontoExportacionId>,
        JpaSpecificationExecutor<CatMontoExportacion> {

    @Query(value = """
            SELECT new com.example.vucem_catalogos_service.model.dto.CatMontoExportacionDTO(
                e.razonSocial,
                e.monto,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatMontoExportacion e
            WHERE
                (
                    :search IS NULL OR
                    LOWER(e.razonSocial) LIKE :search OR
                    LOWER(CAST(e.monto AS string)) LIKE :search
                )
                AND (:activo IS NULL OR e.blnActivo = :activo)
            """,
            countQuery = """
            SELECT COUNT(e)
            FROM CatMontoExportacion e
            WHERE
                (
                    :search IS NULL OR
                    LOWER(e.razonSocial) LIKE :search OR
                    LOWER(CAST(e.monto AS string)) LIKE :search
                )
                AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatMontoExportacionDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatMontoExportacionDTO(
                e.razonSocial,
                e.monto,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatMontoExportacion e
            WHERE e.id.rfcExportador = :id AND e.id.fecMontoExportacion = :fecha
            """)
    Optional<CatMontoExportacionDTO> findByMontoExportacion(@Param("id") String id,
                                                            @Param("fecha") LocalDate fecha);
}
