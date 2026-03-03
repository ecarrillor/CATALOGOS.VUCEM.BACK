package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.Especie.CatEspecieResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatEspecie;
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
                           AND
                           (
                               :activo IS NULL OR a.blnActivo = :activo
                           )
            """)
    Page<CatEspecieResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
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
}
