package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatUnidadAdministrativaDTO;
import com.example.vucem_catalogos_service.model.entity.CatUnidadAdministrativa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatUnidadAdministrativaRepository extends JpaRepository<CatUnidadAdministrativa, String> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatUnidadAdministrativaDTO(
                e.cveUnidadAdministrativa,
                e.ideTipoUnidadAdministrativa,
                parent.cveUnidadAdministrativa,
                e.nivel,
                e.acronimo,
                e.nombre,
                e.descripcion,
                ent.cveEntidad,
                ent.nombre,
                dep.id,
                dep.nombre,
                e.blnFronteriza,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatUnidadAdministrativa e
            LEFT JOIN e.cveUnidadAdministrativaR parent
            LEFT JOIN e.cveEntidad ent
            JOIN e.idDependencia dep
            WHERE (:search IS NULL OR LOWER(e.nombre) LIKE LOWER(CONCAT('%', :search, '%'))
                OR LOWER(e.acronimo) LIKE LOWER(CONCAT('%', :search, '%')))
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatUnidadAdministrativaDTO> search(@Param("search") String search,
                                             @Param("activo") Boolean activo,
                                             Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatUnidadAdministrativaDTO(
                e.cveUnidadAdministrativa,
                e.ideTipoUnidadAdministrativa,
                parent.cveUnidadAdministrativa,
                e.nivel,
                e.acronimo,
                e.nombre,
                e.descripcion,
                ent.cveEntidad,
                ent.nombre,
                dep.id,
                dep.nombre,
                e.blnFronteriza,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatUnidadAdministrativa e
            LEFT JOIN e.cveUnidadAdministrativaR parent
            LEFT JOIN e.cveEntidad ent
            JOIN e.idDependencia dep
            WHERE e.cveUnidadAdministrativa = :cveUnidadAdministrativa
            """)
    CatUnidadAdministrativaDTO findByUnidadAdministrativaDTO(
            @Param("cveUnidadAdministrativa") String cveUnidadAdministrativa);

    List<CatUnidadAdministrativa> findByBlnActivoTrue();
}
