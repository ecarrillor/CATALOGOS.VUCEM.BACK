package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatDeclaracionTramiteDTO;
import com.example.vucem_catalogos_service.model.entity.CatDeclaracionTramite;
import com.example.vucem_catalogos_service.model.entity.CatDeclaracionTramiteId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatDeclaracionTramiteRepository extends JpaRepository<CatDeclaracionTramite, CatDeclaracionTramiteId> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatDeclaracionTramiteDTO(
                e.id.cveDeclaracion,
                e.id.idTipoTramite,
                e.cveDeclaracion.descDeclaracion,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatDeclaracionTramite e
            WHERE (:search IS NULL 
                    OR LOWER(e.cveDeclaracion.descDeclaracion) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
                )
                AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatDeclaracionTramiteDTO> search(@Param("search") String search,
                                           @Param("activo") Boolean activo,
                                           Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatDeclaracionTramiteDTO(
                e.id.cveDeclaracion,
                e.id.idTipoTramite,
                e.cveDeclaracion.descDeclaracion,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatDeclaracionTramite e
            WHERE e.id.cveDeclaracion = :cveDeclaracion
              AND e.id.idTipoTramite = :idTipoTramite
            """)
    CatDeclaracionTramiteDTO findByDeclaracionTramiteDTO(
            @Param("cveDeclaracion") String cveDeclaracion,
            @Param("idTipoTramite") Integer idTipoTramite);
}
