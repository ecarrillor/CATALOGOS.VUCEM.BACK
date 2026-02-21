package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.LeyendaTexto.CatLeyendaTextoRequestDTO;
import com.example.vucem_catalogos_service.model.dto.LeyendaTexto.CatLeyendaTextoResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatLeyendaTextoService {
    PageResponseDTO<CatLeyendaTextoResponseDTO> listarLeyendaTexto(String search, Pageable pageable);

    CatLeyendaTextoResponseDTO crearLeyendaTexto(CatLeyendaTextoRequestDTO dto);

    CatLeyendaTextoResponseDTO findById(Long id);

    CatLeyendaTextoResponseDTO update(Long id, CatLeyendaTextoRequestDTO dto);
}
