package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatActividadEconomicaSat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatActividadEconomicaSatRepository extends JpaRepository<CatActividadEconomicaSat, Long> {
}
