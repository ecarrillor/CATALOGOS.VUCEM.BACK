package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatVidaSilvestreDTO;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.entity.CatVidaSilvestre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICatVidaSilvestreRepository extends JpaRepository<CatVidaSilvestre, Integer> {

    @Query("""
SELECT new com.example.vucem_catalogos_service.model.dto.CatVidaSilvestreDTO(
        e.id,
        e.ideTipoVidaSilvestre,
        gen.descGenero,
        especie.descEspecie,
        e.descNombreComun,
        e.descNombreCientifico,
        e.ideClasifTaxonomica,
        e.fecIniVigencia,
        e.fecFinVigencia,
        e.blnActivo
)
FROM CatVidaSilvestre e
LEFT JOIN e.idEspecie especie
LEFT JOIN e.idGenero gen
WHERE
(
    :search IS NULL OR
    LOWER(CAST(e.id AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
    LOWER(e.ideTipoVidaSilvestre) LIKE LOWER(CONCAT('%', :search, '%')) OR
    LOWER(gen.descGenero) LIKE LOWER(CONCAT('%', :search, '%')) OR
    LOWER(especie.descEspecie) LIKE LOWER(CONCAT('%', :search, '%')) OR
    LOWER(e.descNombreComun) LIKE LOWER(CONCAT('%', :search, '%')) OR
    LOWER(e.descNombreCientifico) LIKE LOWER(CONCAT('%', :search, '%')) OR
    LOWER(e.ideClasifTaxonomica) LIKE LOWER(CONCAT('%', :search, '%')) OR
    LOWER(CAST(e.fecIniVigencia AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
    LOWER(CAST(e.fecFinVigencia AS string)) LIKE LOWER(CONCAT('%', :search, '%'))
)
AND (:activo IS NULL OR e.blnActivo = :activo)
AND (
       :tipo IS NULL OR
       (
           (:tipo IN (220101,220201,221601) AND e.ideTipoVidaSilvestre = 'TIVS.SGIZ') OR
           (:tipo IN (220102,220402) AND e.ideTipoVidaSilvestre = 'TIVS.SGF') OR
           (:tipo IN (220202,221602) AND e.ideTipoVidaSilvestre = 'TIVS.SGFC') OR
           (:tipo IN (230101,230201,230202,250101,250102,250103) AND e.ideTipoVidaSilvestre = 'TIVS.SEM') OR
           (:tipo IN (230901,230903) AND e.ideTipoVidaSilvestre = 'TIVS.SEMVS') OR
           (:tipo IN (230902,230903) AND e.ideTipoVidaSilvestre = 'TIVS.SEMCI')
       )
)
ORDER BY e.id ASC
""")
    Page<CatVidaSilvestreDTO> search(@Param("search") String search,
                                      @Param("tipo") Long tipo,
                                      @Param("activo") Boolean activo,
                                      Pageable pageable);

    @Query("""
    SELECT new com.example.vucem_catalogos_service.model.dto.CatVidaSilvestreDTO(
        e.id,
        e.ideTipoVidaSilvestre,
        gen.descGenero,
        especie.descEspecie,
        e.descNombreComun,
        e.descNombreCientifico,
        e.ideClasifTaxonomica,
        e.fecIniVigencia,
        e.fecFinVigencia,
        e.blnActivo
    )
    FROM CatVidaSilvestre e
    LEFT JOIN e.idEspecie especie
    LEFT JOIN e.idGenero gen
    WHERE e.id = :id
""")
    Optional<CatVidaSilvestreDTO> findByVidaSilvestreDTO(@Param("id") Integer id);

    boolean existsByDescNombreComunAndDescNombreCientifico(String descNombreComun, String descNombreCientifico);

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
    WHERE vs.ideTipoVidaSilvestre IS NOT NULL
      AND vs.idEspecie IS NOT NULL
      AND (
        (vs.ideTipoVidaSilvestre = 'TIVS.SGIZ' AND tt.id IN (220101,220201,221601)) OR
        (vs.ideTipoVidaSilvestre = 'TIVS.SGF'  AND tt.id IN (220102,220402)) OR
        (vs.ideTipoVidaSilvestre = 'TIVS.SGFC' AND tt.id IN (220202,221602)) OR
        (vs.ideTipoVidaSilvestre = 'TIVS.SEM'  AND tt.id IN (230101,230201,230202,250101,250102,250103)) OR
        (vs.ideTipoVidaSilvestre = 'TIVS.SEMVS' AND tt.id IN (230901,230903)) OR
        (vs.ideTipoVidaSilvestre = 'TIVS.SEMCI' AND tt.id IN (230902,230903))
      )
)
ORDER BY 2 ASC
""")
    List<ClasifProductoTraDTO> listadoTipoTramite();

    @Query("SELECT COALESCE(MAX(v.id),0) + 1 FROM CatVidaSilvestre v")
    Integer getNextId();
}
