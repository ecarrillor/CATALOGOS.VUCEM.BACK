package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.FundamentoDictamen.CatFundamentoDictamenResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatFundamentoDictamen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatFundamentoDictamanRepository extends JpaRepository<CatFundamentoDictamen, Long>,
        JpaSpecificationExecutor<CatFundamentoDictamen> {


    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.FundamentoDictamen.CatFundamentoDictamenResponseDTO(
                a.id,
                a.descripcion,
                a.blnSentidoFundamento,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.blnActivo
            )
            FROM CatFundamentoDictamen a
           WHERE (
                      :search IS NULL OR
                      LOWER(a.descripcion) LIKE :search OR
                      STR(a.id) LIKE :search
                )
                           AND
                           (
                               :activo IS NULL OR a.blnActivo = :activo
                           )
            """)
    Page<CatFundamentoDictamenResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );
}
