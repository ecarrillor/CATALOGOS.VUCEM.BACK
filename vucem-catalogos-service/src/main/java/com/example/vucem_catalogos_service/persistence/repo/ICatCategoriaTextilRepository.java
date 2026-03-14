package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CategoriaTextil.CatCategoriaTextilResponseDTO;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatCategoriaTextil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatCategoriaTextilRepository extends JpaRepository<CatCategoriaTextil, Long>, JpaSpecificationExecutor<CatCategoriaTextil> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CategoriaTextil.CatCategoriaTextilResponseDTO(
                a.id,
                a.descripcion,
                a.codCategoriaTextil,
                a.factConversion,
                b.descripcion,
                c.descripcion,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.blnActivo
            )
            FROM CatCategoriaTextil a
            JOIN a.cveUnidadMedida b
            JOIN a.cveUnidadMedidaEquivalente c
            WHERE
                            (
                                        :search IS NULL OR
                                        LOWER(CAST(a.id AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
                                        LOWER(a.descripcion) LIKE LOWER(CONCAT('%', :search, '%')) OR
                                        LOWER(a.codCategoriaTextil) LIKE LOWER(CONCAT('%', :search, '%')) OR
                                        LOWER(CAST(a.factConversion AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
                                        LOWER(b.descripcion) LIKE LOWER(CONCAT('%', :search, '%')) OR
                                        LOWER(c.descripcion) LIKE LOWER(CONCAT('%', :search, '%')) OR
                                        LOWER(CAST(a.fecIniVigencia AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
                                        LOWER(CAST(a.fecFinVigencia AS string)) LIKE LOWER(CONCAT('%', :search, '%'))
                           )
                           AND
                           (
                               :activo IS NULL OR a.blnActivo = :activo
                           )
            """)
    Page<CatCategoriaTextilResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );

    List<CatCategoriaTextil> findAllByBlnActivoTrueOrderByDescripcionAsc();

    @Query("""
   SELECT DISTINCT new com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO(
    x.id,
    x.descripcion
)
FROM CatFraccionTtra y
JOIN y.idCategoriaTextil x
WHERE y.idTipoTramite.id = :id
AND x.blnActivo = true
ORDER BY x.descripcion ASC
""")
    List<ClasifProductoTraDTO> selectCategoriaTextil(@Param("id") Long id);}
