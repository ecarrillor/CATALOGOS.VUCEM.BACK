package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.AduanaTramite.CatAduanaTramiteResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatAduanaTtra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatAduanaTtraRepository extends JpaRepository<CatAduanaTtra, Long>, JpaSpecificationExecutor<CatAduanaTtra> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.AduanaTramite.CatAduanaTramiteResponseDTO(
                a.id,
                u.cveAduana,
                u.nombre,
                b.descServicio,
                a.aliasAduana,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.blnActivo
            )
            FROM CatAduanaTtra a
            JOIN a.cveAduana u
            JOIN a.idTipoTramite b
            WHERE
                (
                    :search IS NULL OR
                    LOWER(u.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(u.cveAduana) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(a.aliasAduana) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) )
                AND (
                    :activo IS NULL OR a.blnActivo = :activo
                )
            """)
    Page<CatAduanaTramiteResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );
}
