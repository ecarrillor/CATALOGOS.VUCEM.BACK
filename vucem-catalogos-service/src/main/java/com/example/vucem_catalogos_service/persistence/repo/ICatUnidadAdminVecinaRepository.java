package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatUnidadAdminVecinaDTO;
import com.example.vucem_catalogos_service.model.entity.CatUnidadAdminVecina;
import com.example.vucem_catalogos_service.model.entity.CatUnidadAdminVecinaId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICatUnidadAdminVecinaRepository extends JpaRepository<CatUnidadAdminVecina, CatUnidadAdminVecinaId> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatUnidadAdminVecinaDTO(
                e.id.cveUnidadAdministrativa,
                e.id.cveEntidad,
                e.cveUnidadAdministrativa.nombre,
                e.cveEntidad.nombre,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatUnidadAdminVecina e
            WHERE (:search IS NULL OR LOWER(e.cveUnidadAdministrativa.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
                OR LOWER(e.cveEntidad.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatUnidadAdminVecinaDTO> search(@Param("search") String search,
                                         @Param("activo") Boolean activo,
                                         Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatUnidadAdminVecinaDTO(
                e.id.cveUnidadAdministrativa,
                e.id.cveEntidad,
                e.cveUnidadAdministrativa.nombre,
                e.cveEntidad.nombre,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatUnidadAdminVecina e
            WHERE e.id.cveUnidadAdministrativa = :cveUnidadAdministrativa
            AND e.id.cveEntidad = :cveEntidad
            """)
    Optional<CatUnidadAdminVecinaDTO> findByUnidadAdminVecinaDTO(
            @Param("cveUnidadAdministrativa") String cveUnidadAdministrativa,
            @Param("cveEntidad") String cveEntidad);
}
