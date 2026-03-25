package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatPatenteAduanalDTO;
import com.example.vucem_catalogos_service.model.entity.CatPatenteAduanal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatPatenteAduanalRepository extends JpaRepository<CatPatenteAduanal, String> {

    @Query(value = """
            SELECT new com.example.vucem_catalogos_service.model.dto.CatPatenteAduanalDTO(
                e.cvePatenteAduanal,
                e.rfc,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo,
                e.ideEstPatenteAut
            )
            FROM CatPatenteAduanal e
            WHERE (:search IS NULL OR
                LOWER(e.cvePatenteAduanal) LIKE :search OR
                LOWER(e.rfc) LIKE :search OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search OR
                LOWER(e.ideEstPatenteAut) LIKE :search
            )
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """,
            countQuery = """
            SELECT COUNT(e)
            FROM CatPatenteAduanal e
            WHERE (:search IS NULL OR
                LOWER(e.cvePatenteAduanal) LIKE :search OR
                LOWER(e.rfc) LIKE :search OR
                LOWER(CAST(e.fecIniVigencia AS string)) LIKE :search OR
                LOWER(CAST(e.fecFinVigencia AS string)) LIKE :search OR
                LOWER(e.ideEstPatenteAut) LIKE :search
            )
            AND (:activo IS NULL OR e.blnActivo = :activo)
            """)
    Page<CatPatenteAduanalDTO> search(@Param("search") String search,
                                      @Param("activo") Boolean activo,
                                      Pageable pageable);

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatPatenteAduanalDTO(
                e.cvePatenteAduanal,
                e.rfc,
                e.fecIniVigencia,
                e.fecFinVigencia,
                e.blnActivo,
                e.ideEstPatenteAut
            )
            FROM CatPatenteAduanal e
            WHERE e.cvePatenteAduanal = :cvePatenteAduanal
            """)
    CatPatenteAduanalDTO findByPatenteAduanalDTO(@Param("cvePatenteAduanal") String cvePatenteAduanal);
}
