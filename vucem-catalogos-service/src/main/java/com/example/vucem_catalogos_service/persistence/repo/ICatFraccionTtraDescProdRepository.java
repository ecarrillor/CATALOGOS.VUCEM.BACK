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

@Repository
public interface ICatFraccionTtraDescProdRepository extends JpaRepository<CatFraccionTtraDescProd, Long>,
        JpaSpecificationExecutor<CatFraccionTtraDescProd> {

    @Query("""
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
                    LOWER(dp.idDescripcionProd.descripcionProducto) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(fra.cveFraccion) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(fra.descripcion) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
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
            ORDER BY UPPER(TRIM(COALESCE(t.descModalidad, t.descSubservicio))) ASC
            """)
    List<ClasifProductoTraDTO> listadoTipoTramite();

}
