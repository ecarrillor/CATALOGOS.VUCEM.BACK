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
            WHERE (
                        :search IS NULL OR
                        LOWER(e.cveUnidadMedida.cveUnidadMedida) LIKE :search OR
                        LOWER(e.cveUnidadMedida.descripcion) LIKE :search OR
                        LOWER(e.idTipoTramite.descModalidad) LIKE :search OR
                        STR(e.id) LIKE :search
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
