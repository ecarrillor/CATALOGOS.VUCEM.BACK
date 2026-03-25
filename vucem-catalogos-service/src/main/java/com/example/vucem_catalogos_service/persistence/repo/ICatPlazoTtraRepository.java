package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatPlazoTtraDTO;
import com.example.vucem_catalogos_service.model.entity.CatPlazoTtra;
import com.example.vucem_catalogos_service.model.entity.CatPlazoTtraId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICatPlazoTtraRepository extends JpaRepository<CatPlazoTtra, CatPlazoTtraId> {

    @Query(value = """
            SELECT new com.example.vucem_catalogos_service.model.dto.CatPlazoTtraDTO(
                a.id,
                e.id.idePlazoVigencia,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo,
                a.descModalidad
            )
            FROM CatPlazoTtra e
                        JOIN e.tipoTramite a
             WHERE
                            (
                                        :search IS NULL OR
                                        LOWER(CAST(a.id AS string)) LIKE :search OR
                                        LOWER(e.id.idePlazoVigencia) LIKE :search OR
                                        LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
                                        LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search OR
                                        LOWER(a.descModalidad) LIKE :search
                           )
                                         AND
                           (
                               :activo IS NULL OR a.blnActivo = :activo
                           )
            """,
            countQuery = """
            SELECT COUNT(e)
            FROM CatPlazoTtra e
                        JOIN e.tipoTramite a
             WHERE
                            (
                                        :search IS NULL OR
                                        LOWER(CAST(a.id AS string)) LIKE :search OR
                                        LOWER(e.id.idePlazoVigencia) LIKE :search OR
                                        LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
                                        LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search OR
                                        LOWER(a.descModalidad) LIKE :search
                           )
                                         AND
                           (
                               :activo IS NULL OR a.blnActivo = :activo
                           )
            """)
    Page<CatPlazoTtraDTO> search(@Param("search") String search,
                                  @Param("activo") Boolean activo,
                                  Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatPlazoTtraDTO(
                a.id,
                e.id.idePlazoVigencia,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo,
                a.descModalidad
            )
            FROM CatPlazoTtra e
                        JOIN e.tipoTramite a
            WHERE e.id.idTipoTramite = :idTipoTramite AND e.id.idePlazoVigencia = :idePlazoVigencia
            """)
    Optional<CatPlazoTtraDTO> findByPlazoTtraDTO(@Param("idTipoTramite") Long idTipoTramite,
                                                   @Param("idePlazoVigencia") String idePlazoVigencia);
}
