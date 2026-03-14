package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.FraccionAranceSearchDTO;
import com.example.vucem_catalogos_service.model.entity.CatFraccionArancelaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICatFraccionArancelariaRepository extends JpaRepository<CatFraccionArancelaria, String> {

    @Query("""
                SELECT new com.example.vucem_catalogos_service.model.dto.FraccionAranceSearchDTO(
                    f.cveFraccion,
                    CONCAT(f.cveFraccion, '  ', f.descripcion)
                )
                FROM CatFraccionArancelaria f
                WHERE f.blnActivo = true
                AND (
                    LOWER(f.cveFraccion) LIKE LOWER(CONCAT('%', :term, '%'))
                    OR LOWER(f.descripcion) LIKE LOWER(CONCAT('%', :term, '%'))
                )
                ORDER BY f.cveFraccion
            """)
    List<FraccionAranceSearchDTO> listadoArancelariaById(String term);


    @Query("""
                SELECT new com.example.vucem_catalogos_service.model.dto.FraccionAranceSearchDTO(
                 c.cveFraccion,
                 CONCAT(c.cveFraccion, ' ', c.descripcion)
                 )
                 FROM CatFraccionArancelaria c
                 WHERE c.blnActivo = true
                 ORDER BY c.descripcion ASC
            """)
    List<FraccionAranceSearchDTO> listadoFraccionAr();
}
