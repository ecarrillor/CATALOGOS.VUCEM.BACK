package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.Especie.CatEspecieResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatEspecie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatEspecieRepository extends JpaRepository<CatEspecie, Integer> {

    List<CatEspecie> findByBlnActivoTrue();

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.Especie.CatEspecieResponseDTO(
                a.id,
                a.descEspecie,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.blnActivo
            )
            FROM CatEspecie a
            WHERE
                            (
                                        :search IS NULL OR
                                        LOWER(a.descEspecie) LIKE :search OR
                                        STR(a.id) LIKE :search
                           )
                           AND
                           (
                               :activo IS NULL OR a.blnActivo = :activo
                           )
            """)
    Page<CatEspecieResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );
}
