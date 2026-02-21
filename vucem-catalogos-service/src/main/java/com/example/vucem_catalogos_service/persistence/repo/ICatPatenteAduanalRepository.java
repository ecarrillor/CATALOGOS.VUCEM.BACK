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
            WHERE (:search IS NULL OR
                LOWER(e.cvePatenteAduanal) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.rfc)               LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                LOWER(e.ideEstPatenteAut)  LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
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
