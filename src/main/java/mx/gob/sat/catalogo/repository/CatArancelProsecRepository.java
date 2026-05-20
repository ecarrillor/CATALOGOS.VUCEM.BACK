package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatArancelProsec;
import mx.gob.sat.catalogo.model.entity.CatArancelProsecId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatArancelProsecRepository extends JpaRepository<CatArancelProsec, CatArancelProsecId> {
}
