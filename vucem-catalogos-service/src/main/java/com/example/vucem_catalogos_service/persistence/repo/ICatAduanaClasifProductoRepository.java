package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.AduanaClasifProducto.CatAduanaClasifProdResponseDTO;
import com.example.vucem_catalogos_service.model.dto.CatAprobCertSeResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatAduanaClasifProd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatAduanaClasifProductoRepository extends JpaRepository<CatAduanaClasifProd, Long>, JpaSpecificationExecutor<CatAduanaClasifProd> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.AduanaClasifProducto.CatAduanaClasifProdResponseDTO(
                a.id,
                u.cveAduana,
                u.nombre,
                b.idClasifProduct,
                b.nombre,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.blnActivo
            )
            FROM CatAduanaClasifProd a
            JOIN a.aduana u
            JOIN a.idClasifProducto b
            WHERE
                (
                    :search IS NULL OR
                    LOWER(u.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(u.cveAduana) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(b.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) )
                AND (
                    :activo IS NULL OR a.blnActivo = :activo
                )
            """)
    Page<CatAduanaClasifProdResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.AduanaClasifProducto.CatAduanaClasifProdResponseDTO(
                a.id,
                u.cveAduana,
                u.nombre,
                b.idClasifProduct,
                b.nombre,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.blnActivo
            )
            FROM CatAduanaClasifProd a
            JOIN a.aduana u
            JOIN a.idClasifProducto b
            WHERE a.id = :id
            """)
    CatAduanaClasifProdResponseDTO findByIdAduanaClasif(@Param("id") Long id);
}
