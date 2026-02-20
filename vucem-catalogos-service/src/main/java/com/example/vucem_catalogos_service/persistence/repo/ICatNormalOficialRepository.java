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
            WHERE (:search IS NULL OR
                LOWER(e.claveNorma) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.descNorma)  LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
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
