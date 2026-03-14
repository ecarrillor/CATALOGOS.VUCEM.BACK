package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.LeyendaTexto.CatLeyendaTextoResponseDTO;
import com.example.vucem_catalogos_service.model.dto.Parametro.CatParametroResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatParametro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatParametroRepository extends JpaRepository<CatParametro, String> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.Parametro.CatParametroResponseDTO(
                a.cveParametro,
                a.descripcion,
                a.valor,
                b.id,
                b.nombre,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.blnActivo
            )
            FROM CatParametro a
            JOIN a.idDependencia b
            WHERE
                            (
                                        :search IS NULL OR
                                        LOWER(a.cveParametro) LIKE LOWER(CONCAT('%', :search, '%')) OR
                                        LOWER(a.descripcion) LIKE LOWER(CONCAT('%', :search, '%')) OR
                                        LOWER(a.valor) LIKE LOWER(CONCAT('%', :search, '%')) OR
                                        LOWER(CAST(b.id AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
                                        LOWER(b.nombre) LIKE LOWER(CONCAT('%', :search, '%')) OR
                                        LOWER(CAST(a.fecIniVigencia AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
                                        LOWER(CAST(a.fecFinVigencia AS string)) LIKE LOWER(CONCAT('%', :search, '%'))
                           )
                           AND
                           (
                               :activo IS NULL OR a.blnActivo = :activo
                           )
            """)
    Page<CatParametroResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );
}
