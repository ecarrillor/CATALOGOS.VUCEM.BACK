package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatFraccionArancelaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatFraccionArancelariaRepository extends JpaRepository<CatFraccionArancelaria, String> {
}
