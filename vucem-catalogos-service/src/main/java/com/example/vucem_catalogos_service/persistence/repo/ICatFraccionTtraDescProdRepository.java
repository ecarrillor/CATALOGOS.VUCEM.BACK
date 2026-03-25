package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatFraccionTtraDescProd;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.FraccionTtraDescProd.CatFraccionTtraDescProdResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICatFraccionTtraDescProdRepository extends JpaRepository<CatFraccionTtraDescProd, Long>,
        JpaSpecificationExecutor<CatFraccionTtraDescProd> {

    @Query(value = """
            SELECT new com.example.vucem_catalogos_service.model.dto.FraccionTtraDescProd.CatFraccionTtraDescProdResponseDTO(
                dp.id,
                dp.idDescripcionProd.id,
                dp.idDescripcionProd.descripcionProducto,
                dp.idFraccionGob.id,
                fra.cveFraccion,
                fra.descripcion,
                dp.fecIniVigencia,
                dp.fecFinVigencia,
                dp.blnActivo
            )
            FROM CatFraccionTtraDescProd dp
            JOIN dp.idFraccionGob f
            JOIN f.cveFraccion fra
            WHERE
                (
                    :search IS NULL OR
                    LOWER(CAST(dp.id AS string)) LIKE :search OR
                    LOWER(CAST(dp.idDescripcionProd.id AS string)) LIKE :search OR
                    LOWER(dp.idDescripcionProd.descripcionProducto) LIKE :search OR
                    LOWER(CAST(dp.idFraccionGob.id AS string)) LIKE :search OR
                    LOWER(fra.cveFraccion) LIKE :search OR
                    LOWER(fra.descripcion) LIKE :search OR
                    LOWER(CAST(dp.fecIniVigencia AS string)) LIKE :search OR
                    LOWER(CAST(dp.fecFinVigencia AS string)) LIKE :search
                )
                AND (:activo IS NULL OR dp.blnActivo = :activo)
                AND (:idTipoTramite IS NULL OR f.idTipoTramite.id = :idTipoTramite)
            """,
            countQuery = """
            SELECT COUNT(dp.id)
            FROM CatFraccionTtraDescProd dp
            JOIN dp.idFraccionGob f
            JOIN f.cveFraccion fra
            WHERE
                (
                    :search IS NULL OR
                    LOWER(CAST(dp.id AS string)) LIKE :search OR
                    LOWER(CAST(dp.idDescripcionProd.id AS string)) LIKE :search OR
                    LOWER(dp.idDescripcionProd.descripcionProducto) LIKE :search OR
                    LOWER(CAST(dp.idFraccionGob.id AS string)) LIKE :search OR
                    LOWER(fra.cveFraccion) LIKE :search OR
                    LOWER(fra.descripcion) LIKE :search OR
                    LOWER(CAST(dp.fecIniVigencia AS string)) LIKE :search OR
                    LOWER(CAST(dp.fecFinVigencia AS string)) LIKE :search
                )
                AND (:activo IS NULL OR dp.blnActivo = :activo)
                AND (:idTipoTramite IS NULL OR f.idTipoTramite.id = :idTipoTramite)
            """)
    Page<CatFraccionTtraDescProdResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            @Param("idTipoTramite") Long idTipoTramite,
            Pageable pageable);

    @Query("""
            SELECT DISTINCT new com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO(
                t.id,
                COALESCE(t.descModalidad, t.descSubservicio)
            )
            FROM CatFraccionTtraDescProd dp
            JOIN dp.idFraccionGob f
            JOIN f.idTipoTramite t
            WHERE t.blnActivo = true
              AND t.cveServicio IN ('23', '25')
            ORDER BY COALESCE(t.descModalidad, t.descSubservicio) ASC
            """)
    List<ClasifProductoTraDTO> listadoTipoTramite();

    Optional<CatFraccionTtraDescProd> findTopByOrderByIdDesc();
}
