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
                e.id.idTipoDoc,
                e.id.idTipoTramite,
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
            WHERE (:search IS NULL OR LOWER(e.ideClasificacionDocumento) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
                OR LOWER(e.ideTipoSolicitanteRfe) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
                OR LOWER(e.ideReglaAnexado) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatDocumentoTramiteDTO> search(@Param("search") String search,
                                         @Param("activo") Boolean activo,
                                         Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatDocumentoTramiteDTO(
                e.id.idTipoDoc,
                e.id.idTipoTramite,
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
            WHERE e.id.idTipoDoc = :idTipoDoc AND e.id.idTipoTramite = :idTipoTramite
            """)
    Optional<CatDocumentoTramiteDTO> findByDocumentoTramiteDTO(@Param("idTipoDoc") Short idTipoDoc,
                                                                @Param("idTipoTramite") Integer idTipoTramite);
}
