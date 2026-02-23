package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatVidaSilvestreDTO;
import com.example.vucem_catalogos_service.model.entity.CatVidaSilvestre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICatVidaSilvestreRepository extends JpaRepository<CatVidaSilvestre, Integer> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatVidaSilvestreDTO(
                e.id,
                e.ideTipoVidaSilvestre,
                e.idEspecie.id,
                e.idEspecie.descEspecie,
                e.descNombreComun,
                e.descNombreCientifico,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo,
                e.ideClasifTaxonomica,
                e.funcionZootecnica
            )
            FROM CatVidaSilvestre e
            WHERE (:search IS NULL OR LOWER(e.descNombreComun) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
                OR LOWER(e.descNombreCientifico) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
                OR LOWER(e.ideTipoVidaSilvestre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')))
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatVidaSilvestreDTO> search(@Param("search") String search,
                                      @Param("activo") Boolean activo,
                                      Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatVidaSilvestreDTO(
                e.id,
                e.ideTipoVidaSilvestre,
                e.idEspecie.id,
                e.idEspecie.descEspecie,
                e.descNombreComun,
                e.descNombreCientifico,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo,
                e.ideClasifTaxonomica,
                e.funcionZootecnica
            )
            FROM CatVidaSilvestre e
            WHERE e.id = :id
            """)
    Optional<CatVidaSilvestreDTO> findByVidaSilvestreDTO(@Param("id") Integer id);
}
