package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatUnidadMedidaTtraDTO;
import com.example.vucem_catalogos_service.model.entity.CatUnidadMedidaTtra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICatUnidadMedidaTtraRepository extends JpaRepository<CatUnidadMedidaTtra, Integer> {

    @Query(value = """
            SELECT new com.example.vucem_catalogos_service.model.dto.CatUnidadMedidaTtraDTO(
                e.id,
                e.cveUnidadMedida.cveUnidadMedida,
                e.cveUnidadMedida.descripcion,
                e.idTipoTramite.id,
                e.idTipoTramite.descModalidad,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatUnidadMedidaTtra e
            WHERE (
                        :search IS NULL OR
                        LOWER(CAST(e.id AS string)) LIKE :search OR
                        LOWER(e.cveUnidadMedida.cveUnidadMedida) LIKE :search OR
                        LOWER(e.cveUnidadMedida.descripcion) LIKE :search OR
                        LOWER(CAST(e.idTipoTramite.id AS string)) LIKE :search OR
                        LOWER(e.idTipoTramite.descModalidad) LIKE :search OR
                        LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
                        LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search
                   )
              AND (:activo IS NULL OR e.blnActivo = :activo)
            """,
            countQuery = """
            SELECT COUNT(e)
            FROM CatUnidadMedidaTtra e
            WHERE (
                        :search IS NULL OR
                        LOWER(CAST(e.id AS string)) LIKE :search OR
                        LOWER(e.cveUnidadMedida.cveUnidadMedida) LIKE :search OR
                        LOWER(e.cveUnidadMedida.descripcion) LIKE :search OR
                        LOWER(CAST(e.idTipoTramite.id AS string)) LIKE :search OR
                        LOWER(e.idTipoTramite.descModalidad) LIKE :search OR
                        LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
                        LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search
                   )
              AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatUnidadMedidaTtraDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatUnidadMedidaTtraDTO(
                e.id,
                e.cveUnidadMedida.cveUnidadMedida,
                e.cveUnidadMedida.descripcion,
                e.idTipoTramite.id,
                e.idTipoTramite.descModalidad,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatUnidadMedidaTtra e
            WHERE e.id = :id
            """)
    Optional<CatUnidadMedidaTtraDTO> findByUnidadMedidaTtraDTO(@Param("id") Integer id);
}
