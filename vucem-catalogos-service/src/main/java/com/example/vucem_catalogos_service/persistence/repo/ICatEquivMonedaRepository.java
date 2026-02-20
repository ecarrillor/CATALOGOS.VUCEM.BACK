package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatEquivMonedaDTO;
import com.example.vucem_catalogos_service.model.entity.CatEquivMoneda;
import com.example.vucem_catalogos_service.model.entity.CatMoneda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatEquivMonedaRepository extends JpaRepository<CatEquivMoneda, Integer> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatEquivMonedaDTO(
                e.id,
                mo.cveMoneda,
                mo.nombre,
                md.cveMoneda,
                md.nombre,
                e.valorConversion,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatEquivMoneda e
            JOIN e.cveMonedaOrigen mo
            JOIN e.cveMonedaDestino md
             WHERE
                (:search IS NULL OR
                    LOWER(mo.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(md.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
                )
                AND (
                    :activo IS NULL OR e.blnActivo = :activo
                )
            """)
    Page<CatEquivMonedaDTO> search(@Param("search") String search,
                                   @Param("activo") Boolean activo,
                                   Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatEquivMonedaDTO(
                e.id,
                mo.cveMoneda,
                mo.nombre,
                md.cveMoneda,
                md.nombre,
                e.valorConversion,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo
            )
            FROM CatEquivMoneda e
            JOIN e.cveMonedaOrigen mo
            JOIN e.cveMonedaDestino md
            WHERE e.id = :id
            """)
    CatEquivMonedaDTO findByEquivMonedaDTO(@Param("id") Integer id);


    @Query("""
            SELECT DISTINCT cd
            FROM CatEquivMoneda e
            JOIN e.cveMonedaDestino cd
            WHERE (
                   LOWER(cd.cveMoneda) LIKE LOWER(CONCAT('%', :term, '%'))
                OR LOWER(cd.nombre) LIKE LOWER(CONCAT('%', :term, '%'))
            )
            ORDER BY cd.cveMoneda
            """)
    List<CatMoneda> buscarMonedasDest(@Param("term") String term);


    @Query("""
            SELECT DISTINCT cd
            FROM CatEquivMoneda e
            JOIN e.cveMonedaOrigen cd
            WHERE (
                   LOWER(cd.cveMoneda) LIKE LOWER(CONCAT('%', :term, '%'))
                OR LOWER(cd.nombre) LIKE LOWER(CONCAT('%', :term, '%'))
            )
            ORDER BY cd.cveMoneda
            """)
    List<CatMoneda> buscarMonedasOrige(@Param("term") String term);
}
