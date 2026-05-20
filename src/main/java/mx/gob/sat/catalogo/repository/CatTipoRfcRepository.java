package mx.gob.sat.catalogo.repository;
import mx.gob.sat.catalogo.model.entity.CatTipoRfc;
import mx.gob.sat.catalogo.model.entity.CatTipoRfcId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatTipoRfcRepository extends JpaRepository<CatTipoRfc, CatTipoRfcId> {
}
