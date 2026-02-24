package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatRegimenTtraDTO;
import com.example.vucem_catalogos_service.model.entity.CatRegimenTtra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICatRegimenTtraRepository extends JpaRepository<CatRegimenTtra, Short> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatRegimenTtraDTO(
                e.id,
                CASE WHEN e.cveRegimen IS NOT NULL THEN e.cveRegimen.cveRegimen ELSE NULL END,
                CASE WHEN e.cveRegimen IS NOT NULL THEN e.cveRegimen.nombre ELSE NULL END,
                CASE WHEN e.idTipoTramite IS NOT NULL THEN e.idTipoTramite.id ELSE NULL END,
                CASE WHEN e.idTipoTramite IS NOT NULL THEN e.idTipoTramite.nombre ELSE NULL END,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatRegimenTtra e
            WHERE (:search IS NULL OR LOWER(e.cveRegimen.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatRegimenTtraDTO> search(@Param("search") String search,
                                   @Param("activo") Boolean activo,
                                   Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatRegimenTtraDTO(
                e.id,
                CASE WHEN e.cveRegimen IS NOT NULL THEN e.cveRegimen.cveRegimen ELSE NULL END,
                CASE WHEN e.cveRegimen IS NOT NULL THEN e.cveRegimen.nombre ELSE NULL END,
                CASE WHEN e.idTipoTramite IS NOT NULL THEN e.idTipoTramite.id ELSE NULL END,
                CASE WHEN e.idTipoTramite IS NOT NULL THEN e.idTipoTramite.nombre ELSE NULL END,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatRegimenTtra e
            WHERE e.id = :id
            """)
    Optional<CatRegimenTtraDTO> findByRegimenTtraDTO(@Param("id") Short id);
}
