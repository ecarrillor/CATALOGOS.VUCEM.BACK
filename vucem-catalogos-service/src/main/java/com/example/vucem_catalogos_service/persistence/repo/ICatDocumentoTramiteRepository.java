package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatDocumentoTramiteDTO;
import com.example.vucem_catalogos_service.model.entity.CatDocumentoTramite;
import com.example.vucem_catalogos_service.model.entity.CatDocumentoTramiteId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICatDocumentoTramiteRepository extends JpaRepository<CatDocumentoTramite, CatDocumentoTramiteId> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatDocumentoTramiteDTO(
                td.id,
                td.nombre,
                tr.id,
                tr.descModalidad,
                e.blnEspecifico,
                e.ideClasificacionDocumento,
                e.ideTipoSolicitanteRfe,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo,
                e.blnSoloAnexar,
                e.ideReglaAnexado
            )
            FROM CatDocumentoTramite e
            JOIN e.idTipoDoc td
            JOIN e.idTipoTramite tr
            WHERE (:search IS NULL OR
                LOWER(CAST(td.id AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(td.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(CAST(tr.id AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(tr.descModalidad) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.ideClasificacionDocumento) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.ideTipoSolicitanteRfe) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.ideReglaAnexado) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatDocumentoTramiteDTO> search(@Param("search") String search,
                                         @Param("activo") Boolean activo,
                                         Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatDocumentoTramiteDTO(
                td.id,
                td.nombre,
                tr.id,
                tr.descModalidad,
                e.blnEspecifico,
                e.ideClasificacionDocumento,
                e.ideTipoSolicitanteRfe,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo,
                e.blnSoloAnexar,
                e.ideReglaAnexado
            )
            FROM CatDocumentoTramite e
            JOIN e.idTipoDoc td
            JOIN e.idTipoTramite tr
            WHERE e.id.idTipoDoc = :idTipoDoc AND e.id.idTipoTramite = :idTipoTramite
            """)
    Optional<CatDocumentoTramiteDTO> findByDocumentoTramiteDTO(@Param("idTipoDoc") Short idTipoDoc,
                                                                @Param("idTipoTramite") Long idTipoTramite);
}
