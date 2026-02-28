package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatCasFraccionTtra;
import com.example.vucem_catalogos_service.model.dto.CasFraccionTtra.CatCasFraccionTtraResponseDTO;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatCasFraccionTtraRepository extends JpaRepository<CatCasFraccionTtra, Short>,
        JpaSpecificationExecutor<CatCasFraccionTtra> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CasFraccionTtra.CatCasFraccionTtraResponseDTO(
                a.id,
                cas.id,
                cas.descCas,
                fra.cveFraccion,
                fra.descripcion,
                tt.id,
                CONCAT(CAST(tt.id AS string), '  ', COALESCE(tt.descModalidad, tt.descSubservicio)),
                a.blnRotterdam,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.blActivo,
                a.descFraccionAlt,
                a.cvnWasser,
                a.cvnArmas,
                a.cvnMontreal,
                a.cvnEstocolmo,
                a.formaDesc,
                a.ideIdentificadorRegla
            )
            FROM CatCasFraccionTtra a
            JOIN a.idCas cas
            JOIN a.cveFraccion fra
            JOIN a.idTipoTramite tt
            WHERE
                (
                    :search IS NULL OR
                    LOWER(fra.cveFraccion) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(fra.descripcion) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(a.descFraccionAlt) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
                )
                AND (:activo IS NULL OR a.blActivo = :activo)
                AND (:idTipoTramite IS NULL OR tt.id = :idTipoTramite)
            """)
    Page<CatCasFraccionTtraResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            @Param("idTipoTramite") Long idTipoTramite,
            Pageable pageable);

    @Query("""
            SELECT DISTINCT new com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO(
                tt.id,
                COALESCE(tt.descModalidad, tt.descSubservicio)
            )
            FROM CatCasFraccionTtra cas
            JOIN cas.idTipoTramite tt
            WHERE tt.blnActivo = true
              AND tt.cveServicio IN ('23', '26')
            ORDER BY COALESCE(tt.descModalidad, tt.descSubservicio) ASC
            """)
    List<ClasifProductoTraDTO> listadoTipoTramite();
}
