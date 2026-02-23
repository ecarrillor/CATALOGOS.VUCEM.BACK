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

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatPlazoTtraDTO(
                e.id.idTipoTramite,
                e.id.idePlazoVigencia,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo,
                e.idTipoTramite.nombre
            )
            FROM CatPlazoTtra e
            WHERE (:search IS NULL OR LOWER(e.id.idePlazoVigencia) LIKE LOWER(CONCAT('%', :search, '%'))
                OR LOWER(e.idTipoTramite.nombre) LIKE LOWER(CONCAT('%', :search, '%')))
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatPlazoTtraDTO> search(@Param("search") String search,
                                  @Param("activo") Boolean activo,
                                  Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatPlazoTtraDTO(
                e.id.idTipoTramite,
                e.id.idePlazoVigencia,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo,
                e.idTipoTramite.nombre
            )
            FROM CatPlazoTtra e
            WHERE e.id.idTipoTramite = :idTipoTramite AND e.id.idePlazoVigencia = :idePlazoVigencia
            """)
    Optional<CatPlazoTtraDTO> findByPlazoTtraDTO(@Param("idTipoTramite") Integer idTipoTramite,
                                                   @Param("idePlazoVigencia") String idePlazoVigencia);
}
