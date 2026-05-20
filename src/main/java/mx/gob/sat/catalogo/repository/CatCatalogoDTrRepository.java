package mx.gob.sat.catalogo.repository;
import mx.gob.sat.catalogo.model.entity.CatCatalogoDTr;
import mx.gob.sat.catalogo.model.entity.CatCatalogoDTrId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatCatalogoDTrRepository extends JpaRepository<CatCatalogoDTr, CatCatalogoDTrId> {
}
