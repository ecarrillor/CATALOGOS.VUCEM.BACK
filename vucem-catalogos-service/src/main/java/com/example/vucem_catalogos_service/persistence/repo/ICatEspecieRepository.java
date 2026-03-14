package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.Especie.CatEspecieResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatEspecie;
import com.example.vucem_catalogos_service.model.entity.CatVidaSilvestre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatEspecieRepository extends JpaRepository<CatEspecie, Integer> {

    List<CatEspecie> findByBlnActivoTrue();

    @Query("""
    SELECT new com.example.vucem_catalogos_service.model.dto.Especie.CatEspecieResponseDTO(
        a.id,
        a.descEspecie,
        a.fecIniVigencia,
        a.fecFinVigencia,
        a.blnActivo
    )
    FROM CatEspecie a
    WHERE
        (
            :search IS NULL OR
            LOWER(a.descEspecie) LIKE :search OR
            STR(a.id) LIKE :search
        )
        AND (:activo IS NULL OR a.blnActivo = :activo)
        AND (
            :tipo IS NULL OR
            EXISTS (
                SELECT 1
                FROM CatVidaSilvestre vs
                WHERE vs.idEspecie.id = a.id
                AND (
                    (:tipo IN (220101L,220201L,221601L) AND vs.ideTipoVidaSilvestre = 'TIVS.SGIZ') OR
                    (:tipo IN (220102L,220402L) AND vs.ideTipoVidaSilvestre = 'TIVS.SGF') OR
                    (:tipo IN (220202L,221602L) AND vs.ideTipoVidaSilvestre = 'TIVS.SGFC') OR
                    (:tipo IN (230101L,230201L,230202L,250101L,250102L,250103L) AND vs.ideTipoVidaSilvestre = 'TIVS.SEM') OR
                    (:tipo IN (230901L,230903L) AND vs.ideTipoVidaSilvestre = 'TIVS.SEMVS') OR
                    (:tipo IN (230902L,230903L) AND vs.ideTipoVidaSilvestre = 'TIVS.SEMCI')
                )
            )
        )
         ORDER BY a.id DESC
""")
    Page<CatEspecieResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            @Param("tipo") Long tipo,
            Pageable pageable
    );



    @Query("""
    SELECT DISTINCT new com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO(
    tt.id,
    CONCAT(
        CAST(tt.id AS string),
        ' ',
        COALESCE(tt.descModalidad, tt.descSubservicio)
    )
)
FROM CatTipoTramite tt
WHERE tt.blnActivo = true
AND EXISTS (
    SELECT 1
    FROM CatVidaSilvestre vs
    WHERE vs.idEspecie IS NOT NULL
    AND (
        (vs.ideTipoVidaSilvestre = 'TIVS.SGIZ' AND tt.id = 220101) OR
        (vs.ideTipoVidaSilvestre = 'TIVS.SGIZ' AND tt.id = 220201) OR
        (vs.ideTipoVidaSilvestre = 'TIVS.SGIZ' AND tt.id = 221601) OR

        (vs.ideTipoVidaSilvestre = 'TIVS.SGF'  AND tt.id = 220102) OR
        (vs.ideTipoVidaSilvestre = 'TIVS.SGF'  AND tt.id = 220402) OR

        (vs.ideTipoVidaSilvestre = 'TIVS.SGFC' AND tt.id = 220202) OR
        (vs.ideTipoVidaSilvestre = 'TIVS.SGFC' AND tt.id = 221602) OR

        (vs.ideTipoVidaSilvestre = 'TIVS.SEM'  AND tt.id = 230101) OR
        (vs.ideTipoVidaSilvestre = 'TIVS.SEM'  AND tt.id = 230201) OR
        (vs.ideTipoVidaSilvestre = 'TIVS.SEM'  AND tt.id = 230202) OR
        (vs.ideTipoVidaSilvestre = 'TIVS.SEM'  AND tt.id = 250101) OR
        (vs.ideTipoVidaSilvestre = 'TIVS.SEM'  AND tt.id = 250102) OR
        (vs.ideTipoVidaSilvestre = 'TIVS.SEM'  AND tt.id = 250103) OR

        (vs.ideTipoVidaSilvestre = 'TIVS.SEMVS' AND tt.id = 230901) OR
        (vs.ideTipoVidaSilvestre = 'TIVS.SEMVS' AND tt.id = 230903) OR

        (vs.ideTipoVidaSilvestre = 'TIVS.SEMCI' AND tt.id = 230902) OR
        (vs.ideTipoVidaSilvestre = 'TIVS.SEMCI' AND tt.id = 230903)
    )
)
ORDER BY 2 ASC
""")
    List<ClasifProductoTraDTO> listadoTipoTramite();


    @Query("""
SELECT e.id
FROM CatVidaSilvestre e
ORDER BY e.id DESC
""")
    ClasifProductoTraDTO lastEspecie();

    CatEspecie findTopByOrderByIdDesc();
}
