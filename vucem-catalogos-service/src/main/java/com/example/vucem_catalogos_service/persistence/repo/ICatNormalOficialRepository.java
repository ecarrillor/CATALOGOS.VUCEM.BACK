package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatNormalOficialDTO;
import com.example.vucem_catalogos_service.model.entity.CatNormalOficial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatNormalOficialRepository extends JpaRepository<CatNormalOficial, Integer> {

    @Query(value = """
            SELECT new com.example.vucem_catalogos_service.model.dto.CatNormalOficialDTO(
                e.id, e.claveNorma, e.fecIniVigencia, e.fecFinVigencia, e.blnActivo,
                e.descNorma, e.fecPublicacion, e.fecEntradaVigor, e.ideClasifNorma,
                p.cvePais, p.nombre,
                r.id,
                e.blnLoteEstructurado
            )
            FROM CatNormalOficial e
            LEFT JOIN e.cvePais p
            LEFT JOIN e.idNormaOficialR r
            WHERE (:search IS NULL OR
                LOWER(CAST(e.id AS string)) LIKE :search OR
                LOWER(e.claveNorma) LIKE :search OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search OR
                LOWER(e.descNorma) LIKE :search OR
                LOWER(CAST(e.fecPublicacion AS string)) LIKE :search OR
                LOWER(CAST(e.fecEntradaVigor AS string)) LIKE :search OR
                LOWER(e.ideClasifNorma) LIKE :search OR
                LOWER(p.cvePais) LIKE :search OR
                LOWER(p.nombre) LIKE :search OR
                LOWER(CAST(r.id AS string)) LIKE :search
            )
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """,
            countQuery = """
            SELECT COUNT(e)
            FROM CatNormalOficial e
            LEFT JOIN e.cvePais p
            LEFT JOIN e.idNormaOficialR r
            WHERE (:search IS NULL OR
                LOWER(CAST(e.id AS string)) LIKE :search OR
                LOWER(e.claveNorma) LIKE :search OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search OR
                LOWER(e.descNorma) LIKE :search OR
                LOWER(CAST(e.fecPublicacion AS string)) LIKE :search OR
                LOWER(CAST(e.fecEntradaVigor AS string)) LIKE :search OR
                LOWER(e.ideClasifNorma) LIKE :search OR
                LOWER(p.cvePais) LIKE :search OR
                LOWER(p.nombre) LIKE :search OR
                LOWER(CAST(r.id AS string)) LIKE :search
            )
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatNormalOficialDTO> search(@Param("search") String search,
                                     @Param("activo") Boolean activo,
                                     Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatNormalOficialDTO(
                e.id, e.claveNorma, e.fecIniVigencia, e.fecFinVigencia, e.blnActivo,
                e.descNorma, e.fecPublicacion, e.fecEntradaVigor, e.ideClasifNorma,
                p.cvePais, p.nombre,
                r.id,
                e.blnLoteEstructurado
            )
            FROM CatNormalOficial e
            LEFT JOIN e.cvePais p
            LEFT JOIN e.idNormaOficialR r
            WHERE e.id = :id
            """)
    CatNormalOficialDTO findByNormalOficialDTO(@Param("id") Integer id);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatNormalOficialDTO(
                e.id, e.claveNorma, e.fecIniVigencia, e.fecFinVigencia, e.blnActivo,
                e.descNorma, e.fecPublicacion, e.fecEntradaVigor, e.ideClasifNorma,
                p.cvePais, p.nombre,
                r.id,
                e.blnLoteEstructurado
            )
            FROM CatNormalOficial e
            LEFT JOIN e.cvePais p
            LEFT JOIN e.idNormaOficialR r
            WHERE e.blnActivo = true
            """)
    List<CatNormalOficialDTO> findAllActivos();
}
