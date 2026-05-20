package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatRestricDescProd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRestricDescProdRepository extends JpaRepository<CatRestricDescProd, Long> {
}
