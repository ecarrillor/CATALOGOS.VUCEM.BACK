package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.JustificacionTramite.CatJustificacionTramiteResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatJustificacionTtra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatJustificacionTramiteRepository extends JpaRepository<CatJustificacionTtra, Long>,
        JpaSpecificationExecutor<CatJustificacionTtra> {


    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.JustificacionTramite.CatJustificacionTramiteResponseDTO(
                a.id,
                b.id,
                b.descServicio,
                a.descJustificacion,
                a.descContenidoJustificacion,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.blnActivo
            )
            FROM CatJustificacionTtra a
            JOIN a.idTipoTramite b
            WHERE
                   (
                          :search IS NULL OR
                          LOWER(CAST(a.id AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
                          LOWER(CAST(b.id AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
                          LOWER(b.descServicio) LIKE LOWER(CONCAT('%', :search, '%')) OR
                          LOWER(a.descJustificacion) LIKE LOWER(CONCAT('%', :search, '%')) OR
                          LOWER(a.descContenidoJustificacion) LIKE LOWER(CONCAT('%', :search, '%')) OR
                          LOWER(CAST(a.fecIniVigencia AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
                          LOWER(CAST(a.fecFinVigencia AS string)) LIKE LOWER(CONCAT('%', :search, '%'))
                   )
                           AND
                           (
                               :activo IS NULL OR a.blnActivo = :activo
                           )
            """)
    Page<CatJustificacionTramiteResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );

}
