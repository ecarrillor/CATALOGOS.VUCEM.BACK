package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatMotivoTtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatMotivoTtraRepository extends JpaRepository<CatMotivoTtra, Long> {
}
