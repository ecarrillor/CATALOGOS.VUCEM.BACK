package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.CatAprobCertSeResponseDTO;
import com.example.vucem_catalogos_service.model.dto.CatUnidadAdministrativaNameDTO;
import com.example.vucem_catalogos_service.model.entity.CatAprobCertSe;
import com.example.vucem_catalogos_service.model.entity.CatLaboratorioTtra;
import com.example.vucem_catalogos_service.model.entity.CatUnidadAdministrativa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatAprobCertSeRepository extends JpaRepository<CatAprobCertSe, Short> {

    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatAprobCertSeResponseDTO(
                a.id,
                u.nombre,
                CASE
                    WHEN a.ideTipoAprobCertSe = 'TIAPC.AC' THEN 'Acreditación'
                    WHEN a.ideTipoAprobCertSe = 'TIAPC.AP' THEN 'Aprobación'
                    ELSE a.ideTipoAprobCertSe
                END,
                a.numAprobCert,
                a.fecEmision,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.blnActivo
            )
            FROM CatAprobCertSe a
            JOIN a.cveUnidadAdministrativa u
            WHERE
                (
                    :search IS NULL OR
                    LOWER(u.nombre) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%')) OR
                    LOWER(a.numAprobCert) LIKE LOWER(CONCAT('%', CAST(:search AS string), '%'))
                )
                AND (
                    :activo IS NULL OR a.blnActivo = :activo
                )
            """)
    Page<CatAprobCertSeResponseDTO> search(
            @Param("search") String search,
            @Param("activo") Boolean activo,
            Pageable pageable
    );


    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatAprobCertSeResponseDTO(
                a.id,
                u.nombre,
                CASE
                    WHEN a.ideTipoAprobCertSe = 'TIAPC.AC' THEN 'Acreditación'
                    WHEN a.ideTipoAprobCertSe = 'TIAPC.AP' THEN 'Aprobación'
                    ELSE a.ideTipoAprobCertSe
                END,
                a.numAprobCert,
                a.fecEmision,
                a.fecIniVigencia,
                a.fecFinVigencia,
                a.blnActivo
            )
            FROM CatAprobCertSe a
            JOIN a.cveUnidadAdministrativa u
            WHERE a.id = :id
            """)
    CatAprobCertSeResponseDTO findByCatAprobeId(@Param("id") Short id);


    @Query("""
            SELECT new com.example.vucem_catalogos_service.model.dto.CatUnidadAdministrativaNameDTO(
                u.cveUnidadAdministrativa,
                u.nombre)
            FROM CatAprobCertSe a
            JOIN a.cveUnidadAdministrativa u
            """)
    List<CatUnidadAdministrativaNameDTO> findByName();

    @Query("""
            SELECT u
            FROM CatAprobCertSe a
            JOIN a.cveUnidadAdministrativa u
            WHERE a.blnActivo = true
            ORDER BY u.nombre asc 
            """)
    List<CatUnidadAdministrativa> listadoLaboratorio();


}
