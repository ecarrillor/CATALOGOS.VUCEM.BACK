package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatTipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatTipoDocumentoRepository extends JpaRepository<CatTipoDocumento,Short>,
        JpaSpecificationExecutor<CatTipoDocumento> {

    List<CatTipoDocumento> findByBlnActivoTrue();
}
