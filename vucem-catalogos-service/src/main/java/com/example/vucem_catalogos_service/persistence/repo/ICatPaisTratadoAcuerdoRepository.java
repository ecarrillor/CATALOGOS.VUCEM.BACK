package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatPaisRestriccionTtra;
import com.example.vucem_catalogos_service.model.entity.CatPaisTratadoAcuerdo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICatPaisTratadoAcuerdoRepository extends JpaRepository<CatPaisTratadoAcuerdo, CatPaisRestriccionTtra> {

    @Query("SELECT e FROM CatPaisTratadoAcuerdo e WHERE e.id.cvePais = :cvePais AND e.id.idTratadoAcuerdo = :idTratadoAcuerdo")
    Optional<CatPaisTratadoAcuerdo> findByCvePaisAndIdTratadoAcuerdo(
            @Param("cvePais") String cvePais,
            @Param("idTratadoAcuerdo") Short idTratadoAcuerdo);
}
