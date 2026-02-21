package com.example.vucem_catalogos_service.persistence.repo;

import com.example.vucem_catalogos_service.model.entity.CatLaboratorioTtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ICatLaboratorioTtraRepository extends JpaRepository<CatLaboratorioTtra, Long>, JpaSpecificationExecutor<CatLaboratorioTtra> {

}
