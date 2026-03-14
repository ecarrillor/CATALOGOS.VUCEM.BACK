package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.entity.CatClasifProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClasificacionProductoRepository extends JpaRepository<CatClasifProducto, Long>,
        JpaSpecificationExecutor<CatClasifProducto> {

    @Query("""
                    SELECT DISTINCT new com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO(
                        cat.idClasifProduct,
                        cat.nombre
                    )
                    FROM CatClasifProducto cat
                    JOIN cat.idTipoTramite tr
                    WHERE tr.id = :idTipoTramite
            """)
    List<ClasifProductoTraDTO> listadoClasificacionProducto(@Param("idTipoTramite") Long idTipoTramite);



}
