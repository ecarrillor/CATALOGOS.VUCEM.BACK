package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatUnidadAdministrativaDTO;
import com.example.vucem_catalogos_service.model.dto.CatUnidadAdministrativaNameDTO;
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
            SELECT new com.example.vucem_catalogos_service.model.dto.CatUnidadAdministrativaNameDTO(
                a.cveUnidadAdministrativa,
                a.nombre,
                a.descripcion)
            FROM CatUnidadAdministrativa a
            ORDER BY a.nombre DESC
            """)
    List<CatUnidadAdministrativaNameDTO> findByName();

    @Query(value = """
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
            WHERE (:search IS NULL OR
                LOWER(e.cveUnidadAdministrativa) LIKE :search OR
                LOWER(e.ideTipoUnidadAdministrativa) LIKE :search OR
                LOWER(parent.cveUnidadAdministrativa) LIKE :search OR
                LOWER(CAST(e.nivel AS string)) LIKE :search OR
                LOWER(e.acronimo) LIKE :search OR
                LOWER(e.nombre) LIKE :search OR
                LOWER(e.descripcion) LIKE :search OR
                LOWER(ent.cveEntidad) LIKE :search OR
                LOWER(ent.nombre) LIKE :search OR
                LOWER(CAST(dep.id AS string)) LIKE :search OR
                LOWER(dep.nombre) LIKE :search OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search)
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """,
            countQuery = """
            SELECT COUNT(e)
            FROM CatUnidadAdministrativa e
            LEFT JOIN e.cveUnidadAdministrativaR parent
            LEFT JOIN e.cveEntidad ent
            JOIN e.idDependencia dep
            WHERE (:search IS NULL OR
                LOWER(e.cveUnidadAdministrativa) LIKE :search OR
                LOWER(e.ideTipoUnidadAdministrativa) LIKE :search OR
                LOWER(parent.cveUnidadAdministrativa) LIKE :search OR
                LOWER(CAST(e.nivel AS string)) LIKE :search OR
                LOWER(e.acronimo) LIKE :search OR
                LOWER(e.nombre) LIKE :search OR
                LOWER(e.descripcion) LIKE :search OR
                LOWER(ent.cveEntidad) LIKE :search OR
                LOWER(ent.nombre) LIKE :search OR
                LOWER(CAST(dep.id AS string)) LIKE :search OR
                LOWER(dep.nombre) LIKE :search OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search)
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
