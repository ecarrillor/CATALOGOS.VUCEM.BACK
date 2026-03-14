package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatMontoExportacionDTO;
import com.example.vucem_catalogos_service.model.entity.CatMontoExportacion;
import com.example.vucem_catalogos_service.model.entity.CatMontoExportacionId;
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
