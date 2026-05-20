package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatTarifa;
import mx.gob.sat.catalogo.model.entity.CatTarifaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatTarifaRepository extends JpaRepository<CatTarifa, CatTarifaId> {
}
