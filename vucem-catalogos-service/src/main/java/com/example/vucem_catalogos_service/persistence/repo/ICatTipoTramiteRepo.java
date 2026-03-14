package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatTipoTramiteDTO;
import com.example.vucem_catalogos_service.model.entity.CatTipoTramite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICatTipoTramiteRepo extends JpaRepository<CatTipoTramite, Long> {

    @Query("""
        SELECT new com.example.vucem_catalogos_service.model.dto.CatTipoTramiteDTO(
            e.id,
            e.cveServicio,
            e.descServicio,
            e.cveSubservicio,
            e.descSubservicio,
            e.cveModalidad,
            e.descModalidad,
            e.cveFlujo,
            e.descFlujo,
            e.nivelServicio,
            CASE WHEN e.idDependencia IS NOT NULL THEN e.idDependencia.id ELSE NULL END,
            CASE WHEN e.idDependencia IS NOT NULL THEN e.idDependencia.nombre ELSE NULL END,
            e.nomServAxway,
            e.nomMensajeAxway,
            e.urlAxway,
            e.fecCaptura,
            e.fecFinVigencia,
            e.nombre,
            e.blnReplicaInfo,
            e.blnAutomatico,
            e.fecIniVigencia,
            e.blnActivo,
            e.blnAsignacion,
            e.cveModulo
        )
        FROM CatTipoTramite e
        WHERE
        (
            :search IS NULL OR
            LOWER(CAST(e.id AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
            LOWER(e.cveServicio) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
            LOWER(e.descServicio) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
            LOWER(e.cveSubservicio) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
            LOWER(e.descSubservicio) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
            LOWER(e.cveModalidad) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
            LOWER(e.descModalidad) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
            LOWER(e.cveFlujo) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
            LOWER(e.descFlujo) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
            LOWER(CAST(e.nivelServicio AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
            LOWER(e.nomServAxway) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
            LOWER(e.nomMensajeAxway) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
            LOWER(e.urlAxway) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
            LOWER(CAST(e.fecCaptura AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
            LOWER(CAST(e.fecFinVigencia AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
            LOWER(e.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
            LOWER(CAST(e.fecIniVigencia AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
            LOWER(CAST(e.cveModulo AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
        )
        AND (:activo IS NULL OR e.blnActivo = :activo)
        """)
    Page<CatTipoTramiteDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );


    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatTipoTramiteDTO(
                e.id,
                e.cveServicio,
                e.descServicio,
                e.cveSubservicio,
                e.descSubservicio,
                e.cveModalidad,
                e.descModalidad,
                e.cveFlujo,
                e.descFlujo,
                e.nivelServicio,
                CASE WHEN e.idDependencia IS NOT NULL THEN e.idDependencia.id ELSE NULL END,
                CASE WHEN e.idDependencia IS NOT NULL THEN e.idDependencia.nombre ELSE NULL END,
                e.nomServAxway,
                e.nomMensajeAxway,
                e.urlAxway,
                e.fecCaptura,
                e.fecFinVigencia,
                e.nombre,
                e.blnReplicaInfo,
                e.blnAutomatico,
                e.fecIniVigencia,
                e.blnActivo,
                e.blnAsignacion,
                e.cveModulo
            )
            FROM CatTipoTramite e
            WHERE e.id = :id
            """)
    Optional<CatTipoTramiteDTO> findByTipoTramiteDTO(@Param("id") Long id);
}
