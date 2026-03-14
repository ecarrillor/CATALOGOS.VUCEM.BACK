package com.example.vucem_catalogos_service.persistence.repo;
import com.example.vucem_catalogos_service.model.dto.MotivoTramite.CatMotivoTramiteResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatMotivoTtra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatMotivoTramiteRepository extends JpaRepository<CatMotivoTtra, Long> {


    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.MotivoTramite.CatMotivoTramiteResponseDTO(
                a.id,
                b.id,
                b.descModalidad,
                a.ideTipoMotivoTtra,
                a.descMotivo,
                a.descContenidoMotivo,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.blnActivo
            )
            FROM CatMotivoTtra a
            JOIN a.idTipoTramite b
            WHERE
                            (
                                        :search IS NULL OR
                                        LOWER(CAST(a.id AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
                                        LOWER(CAST(b.id AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
                                        LOWER(b.descModalidad) LIKE LOWER(CONCAT('%', :search, '%')) OR
                                        LOWER(a.ideTipoMotivoTtra) LIKE LOWER(CONCAT('%', :search, '%')) OR
                                        LOWER(a.descMotivo) LIKE LOWER(CONCAT('%', :search, '%')) OR
                                        LOWER(a.descContenidoMotivo) LIKE LOWER(CONCAT('%', :search, '%')) OR
                                        LOWER(CAST(a.fecIniVigencia AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
                                        LOWER(CAST(a.fecFinVigencia AS string)) LIKE LOWER(CONCAT('%', :search, '%'))
                           )
                           AND
                           (
                               :activo IS NULL OR a.blnActivo = :activo
                           )
            """)
    Page<CatMotivoTramiteResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );
}
