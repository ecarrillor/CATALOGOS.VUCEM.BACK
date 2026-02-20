package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.DictamenTramite.CatDictamenTramiteResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatDictamenTramite;
import com.example.vucem_catalogos_service.model.entity.CatDictamenTramiteId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ICatDictamenTramiteRepository extends JpaRepository<CatDictamenTramite, CatDictamenTramiteId> {
    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.DictamenTramite.CatDictamenTramiteResponseDTO(
                tt.id,        
                tt.descServicio,
                td.id,
                td.nombre,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.blnActivo
            )
            FROM CatDictamenTramite a
            JOIN a.tipoTramite tt
            JOIN a.tipoDictamen td
            WHERE
            (
                :search IS NULL OR
                    LOWER(tt.descServicio) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(td.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
            AND
            (
                :activo IS NULL OR a.blnActivo = :activo
            )
                        )
            """)
    Page<CatDictamenTramiteResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );
}
