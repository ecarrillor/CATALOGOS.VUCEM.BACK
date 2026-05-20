package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatCombinacionSg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatCombinacionSgRepository extends JpaRepository<CatCombinacionSg, Long> {
}
