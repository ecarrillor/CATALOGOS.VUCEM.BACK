package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatArancelProsecDTO;
import com.example.vucem_catalogos_service.model.entity.CatArancelProsec;
import com.example.vucem_catalogos_service.model.entity.CatArancelProsecId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatArancelProsecRepository extends JpaRepository<CatArancelProsec, CatArancelProsecId> {


    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatArancelProsecDTO(
                cat.cveFraccion.cveFraccion,
                sec.cveSectorProsec,
                sec.nombre,
                cat.tasa,
                cat.fecIniVigencia,
                cat.fecFinVigencia,
                cat.blnActivo
            )
            FROM CatArancelProsec cat
            JOIN cat.cveSectorProsec sec
            WHERE
                (
                    :search IS NULL OR
                    LOWER(cat.cveFraccion.cveFraccion) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(sec.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
                )
                AND (
                    :activo IS NULL OR cat.blnActivo = :activo
                )
            """)
    Page<CatArancelProsecDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable);


    @Query("""
              SELECT new com.example.vucem_catalogos_service.model.dto.CatArancelProsecDTO(
                cat.cveFraccion.cveFraccion,
                sec.cveSectorProsec,
                sec.nombre,
                cat.tasa,
                cat.fecIniVigencia,
                cat.fecFinVigencia,
                cat.blnActivo
            )
            FROM CatArancelProsec cat
            JOIN cat.cveSectorProsec sec
            WHERE cat.cveFraccion.cveFraccion = :id and sec.cveSectorProsec = :cveSectorProsec
            """)
    CatArancelProsecDTO findByCatAracel(@Param("id") String id, @Param("cveSectorProsec") String cveSectorProsec);
}
