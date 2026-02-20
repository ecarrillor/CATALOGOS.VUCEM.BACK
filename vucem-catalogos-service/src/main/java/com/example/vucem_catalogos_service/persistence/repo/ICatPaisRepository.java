package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatMuncipioDTO;
import com.example.vucem_catalogos_service.model.dto.CatPaisDTO;
import com.example.vucem_catalogos_service.model.dto.CatPaisSaveDTO;
import com.example.vucem_catalogos_service.model.entity.CatPais;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatPaisRepository extends JpaRepository<CatPais, String> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatPaisDTO(
                cat.cvePais,
                cat.nombre,
                mon.nombre,
                cat.cvePaisWco,
                cat.fecFinVigencia,
                cat.fecIniVigencia,
                cat.blnActivo
            )
            FROM CatPais cat
            LEFT JOIN cat.cveMoneda mon
            WHERE
                (
                    :search IS NULL OR
                    LOWER(cat.cvePais) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(cat.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(mon.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(cat.nombreAlterno) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
                )
                AND (
                    :activo IS NULL OR cat.blnActivo = :activo
                )
            """)
    Page<CatPaisDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatPaisSaveDTO(
                cat.cvePais,
                cat.nombre,
                mon.cveMoneda,
                cat.cvePaisWco,
                cat.fecFinVigencia,
                cat.fecIniVigencia,
                cat.blnActivo
            )
            FROM CatPais cat
            LEFT JOIN cat.cveMoneda mon
            WHERE cat.cvePais = :cvePais
            """)
    CatPaisSaveDTO findByPaisDTO(@Param("cvePais") String cvePais);


    @Query("""
                SELECT new com.example.vucem_catalogos_service.model.dto.CatMuncipioDTO(
                    dm.cveDelegMun,
                    dm.nombre
                )
                FROM CatDelegMun dm
                JOIN dm.cveEntidad en
                JOIN en.cvePais pais
                WHERE pais.cvePais = :cvePais
                  AND en.cveEntidad = :cveEntidad
                  AND dm.blnActivo = true
            """)
    List<CatMuncipioDTO> findMunicipios(@Param("cvePais") String cvePais,
                                        @Param("cveEntidad") String cveEntidad);
}
