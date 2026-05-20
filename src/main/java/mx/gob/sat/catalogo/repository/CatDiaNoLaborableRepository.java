package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatDiaNoLaborable;
import mx.gob.sat.catalogo.model.entity.CatDiaNoLaborableId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatDiaNoLaborableRepository extends JpaRepository<CatDiaNoLaborable, CatDiaNoLaborableId> {
}
