package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.LeyendaTexto.CatLeyendaTextoResponseDTO;
import com.example.vucem_catalogos_service.model.dto.ObservacionTramite.CatObservacionTramiteResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatObservacionTtra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatObservacionTtraRepository extends JpaRepository<CatObservacionTtra, Long> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.ObservacionTramite.CatObservacionTramiteResponseDTO(
                a.id,
                b.descModalidad,
                a.descObservacionDict,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.blnActivo
            )
            FROM CatObservacionTtra a
            JOIN a.idTipoTramite b
            WHERE
                            (
                                        :search IS NULL OR
                                        LOWER(a.descObservacionDict) LIKE :search OR
                                        LOWER(b.descModalidad) LIKE :search OR
                                        STR(a.id) LIKE :search
                           )
                           AND
                           (
                               :activo IS NULL OR a.blnActivo = :activo
                           )
            """)
    Page<CatObservacionTramiteResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );

}
