package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatEquivalenciaAelc;
import mx.gob.sat.catalogo.model.entity.CatEquivalenciaAelcId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatEquivalenciaAelcRepository extends JpaRepository<CatEquivalenciaAelc, CatEquivalenciaAelcId> {
}
