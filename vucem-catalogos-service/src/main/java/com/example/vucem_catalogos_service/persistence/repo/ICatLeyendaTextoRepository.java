package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.LeyendaTexto.CatLeyendaTextoResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatLeyendaTexto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICatLeyendaTextoRepository extends JpaRepository<CatLeyendaTexto, Long> {


    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.LeyendaTexto.CatLeyendaTextoResponseDTO(
                a.id,
                a.ideTipoLeyendaTexto,
                a.numAnio,
                a.leyenda,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.blnActivo
            )
            FROM CatLeyendaTexto a
            WHERE
                            (
                                        :search IS NULL OR
                                        LOWER(a.ideTipoLeyendaTexto) LIKE :search OR
                                        STR(a.id) LIKE :search
                           )
                           AND
                           (
                               :activo IS NULL OR a.blnActivo = :activo
                           )
            """)
    Page<CatLeyendaTextoResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );
}
