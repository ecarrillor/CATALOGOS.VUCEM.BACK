package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatPlazoMaximoAutTramiteDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.time.LocalDate;

public interface ICatPlazoMaximoAutTramiteService {
    PageResponseDTO<CatPlazoMaximoAutTramiteDTO> list(String search, Pageable pageable);
    CatPlazoMaximoAutTramiteDTO findById(Long idTipoTramite, LocalDate fecIniVigencia);
    CatPlazoMaximoAutTramiteDTO create(CatPlazoMaximoAutTramiteDTO dto);
    CatPlazoMaximoAutTramiteDTO update(Long idTipoTramite, LocalDate fecIniVigencia, CatPlazoMaximoAutTramiteDTO dto);
}
