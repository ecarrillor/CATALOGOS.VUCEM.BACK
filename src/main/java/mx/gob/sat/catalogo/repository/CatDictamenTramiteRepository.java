package mx.gob.sat.catalogo.repository;

import mx.gob.sat.catalogo.model.entity.CatDictamenTramite;
import mx.gob.sat.catalogo.model.entity.CatDictamenTramiteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatDictamenTramiteRepository extends JpaRepository<CatDictamenTramite, CatDictamenTramiteId> {
}
