package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatCombinacionSgDTO;
import com.example.vucem_catalogos_service.model.entity.CatCombinacionSg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICatCombinacionSgRepository extends JpaRepository<CatCombinacionSg, Long> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatCombinacionSgDTO(
                e.id,
                CASE WHEN e.cvcEspecie IS NOT NULL THEN e.cvcEspecie.cveCatalogo ELSE NULL END,
                CASE WHEN e.cvcEspecie IS NOT NULL THEN e.cvcEspecie.descGenerica1 ELSE NULL END,
                CASE WHEN e.cvcFuncionZootecnica IS NOT NULL THEN e.cvcFuncionZootecnica.cveCatalogo ELSE NULL END,
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
            WHERE (:search IS NULL OR LOWER(e.ideTipoCertificadoMerc) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatCombinacionSgDTO> search(@Param("search") String search,
                                     @Param("activo") Short activo,
                                     Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatCombinacionSgDTO(
                e.id,
                CASE WHEN e.cvcEspecie IS NOT NULL THEN e.cvcEspecie.cveCatalogo ELSE NULL END,
                CASE WHEN e.cvcEspecie IS NOT NULL THEN e.cvcEspecie.descGenerica1 ELSE NULL END,
                CASE WHEN e.cvcFuncionZootecnica IS NOT NULL THEN e.cvcFuncionZootecnica.cveCatalogo ELSE NULL END,
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
}
