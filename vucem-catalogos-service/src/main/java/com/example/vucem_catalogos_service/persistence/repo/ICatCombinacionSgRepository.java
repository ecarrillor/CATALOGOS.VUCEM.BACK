package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.*;
import com.example.vucem_catalogos_service.model.entity.CatCatalogoDTr;
import com.example.vucem_catalogos_service.model.entity.CatCombinacionSg;
import org.springframework.beans.PropertyValues;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICatCombinacionSgRepository extends JpaRepository<CatCombinacionSg, Long> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatCombinacionSgDTO(
                e.id,
                e.cvcEspecie.cveCatalogo,
                e.cvcEspecie.descGenerica1,
                e.cvcFuncionZootecnica.cveCatalogo,
                e.cvcFuncionZootecnica.descGenerica1,
                e.cvcNombreComun.cveCatalogo,
                e.cvcNombreComun.descGenerica1,
                e.cvcTipoProducto.cveCatalogo,
                e.cvcTipoProducto.descGenerica1,
                e.cvePais.cvePais,
                e.cvePais.nombre,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo,
                e.ideTipoCertificadoMerc
            )
            FROM CatCombinacionSg e
            WHERE (:search IS NULL OR
                LOWER(CAST(e.id AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.cvcEspecie.cveCatalogo) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.cvcEspecie.descGenerica1) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.cvcFuncionZootecnica.cveCatalogo) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.cvcFuncionZootecnica.descGenerica1) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.cvcNombreComun.cveCatalogo) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.cvcNombreComun.descGenerica1) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.cvcTipoProducto.cveCatalogo) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.cvcTipoProducto.descGenerica1) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.cvePais.cvePais) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.cvePais.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.ideTipoCertificadoMerc) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatCombinacionSgDTO> search(@Param("search") String search,
                                     @Param("activo") Short activo,
                                     Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatCombinacionSgDTO(
                e.id,
                e.cvcEspecie.cveCatalogo,
                 e.cvcEspecie.descGenerica1,
                e.cvcFuncionZootecnica.cveCatalogo,
                CASE WHEN e.cvcFuncionZootecnica IS NOT NULL THEN e.cvcFuncionZootecnica.descGenerica1 ELSE NULL END,
                CASE WHEN e.cvcNombreComun IS NOT NULL THEN e.cvcNombreComun.cveCatalogo ELSE NULL END,
                CASE WHEN e.cvcNombreComun IS NOT NULL THEN e.cvcNombreComun.descGenerica1 ELSE NULL END,
                CASE WHEN e.cvcTipoProducto IS NOT NULL THEN e.cvcTipoProducto.cveCatalogo ELSE NULL END,
                CASE WHEN e.cvcTipoProducto IS NOT NULL THEN e.cvcTipoProducto.descGenerica1 ELSE NULL END,
                CASE WHEN e.cvePais IS NOT NULL THEN e.cvePais.cvePais ELSE NULL END,
                CASE WHEN e.cvePais IS NOT NULL THEN e.cvePais.nombre ELSE NULL END,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo,
                e.ideTipoCertificadoMerc
            )
            FROM CatCombinacionSg e
            WHERE e.id = :id
            """)
    Optional<CatCombinacionSgDTO> findByCombinacionSgDTO(@Param("id") Long id);


    @Query("""
    SELECT new com.example.vucem_catalogos_service.model.dto.SelectCombinacionReqDTO(
       d.cveCatalogo,
        tr.descripcion
    )
    FROM CatCatalogoD d, CatCatalogoDTr tr
    WHERE tr.cveCatalogo.cveCatalogo = d.cveCatalogo
    AND d.cveCatalogoH.cveCatalogoH = :id
    ORDER BY tr.descripcion ASC
""")
    List<SelectCombinacionReqDTO> listTipoCertificado(@Param("id")String id);



    @Query("""
    SELECT d.cveCatalogo
    FROM CatCatalogoD d
    WHERE d.cveCatalogoH.cveCatalogoH = :id
""")
    List<String> findCatalogoClaves(String id);



    @Query("""
SELECT new com.example.vucem_catalogos_service.model.dto.CombinacionReqUpdateTempDTO(
    d.cveCatalogo,
    tr.descripcion,
    d.cveCatalogo,
    tr.fecIniVigencia,
    tr.fecFinVigencia,
    tr.blnActivo
)
FROM CatCatalogoDTr tr
JOIN tr.cveCatalogo d
WHERE d.cveCatalogoH.cveCatalogoH = :catalogo
AND tr.cveLenguaje.cveLenguaje = 'es'
ORDER BY tr.descripcion
""")
    List<CombinacionReqUpdateTempDTO> findCatalogo(@Param("catalogo") String catalogo);
}
