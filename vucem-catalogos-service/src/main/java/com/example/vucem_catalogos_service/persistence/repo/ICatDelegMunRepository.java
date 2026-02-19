package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatDelegMunDTO;
import com.example.vucem_catalogos_service.model.entity.CatDelegMun;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatDelegMunRepository extends JpaRepository<CatDelegMun, String> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatDelegMunDTO(
                cat.cveDelegMun,
                cat.nombre,
                cat.fecCaptura,
                cat.fecFinVigencia,
                cat.satMunicipality,
                cat.fecIniVigencia,
                cat.blnActivo
            )
            FROM CatDelegMun cat
            WHERE
                (
                    :search IS NULL OR
                    LOWER(cat.cveDelegMun) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(cat.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
                )
                AND (
                    :activo IS NULL OR cat.blnActivo = :activo
                )
            """)
    Page<CatDelegMunDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatDelegMunDTO(
                cat.cveDelegMun,
                cat.nombre,
                cat.fecCaptura,
                cat.fecFinVigencia,
                cat.satMunicipality,
                cat.fecIniVigencia,
                cat.blnActivo
            )
            FROM CatDelegMun cat
            WHERE cat.cveDelegMun = :cveDelegMun
            """)
    CatDelegMunDTO findByDelegMunDTO(@Param("cveDelegMun") String cveDelegMun);
}
