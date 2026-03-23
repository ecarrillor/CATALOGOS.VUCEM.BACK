package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.EnumeracionDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.VucRepoFirmaGralSe.VucRepoFirmaGralSeRequestDTO;
import com.example.vucem_catalogos_service.model.dto.VucRepoFirmaGralSe.VucRepoFirmaGralSeResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IVucRepoFirmaGralSeService {

    PageResponseDTO<VucRepoFirmaGralSeResponseDTO> listAll(String search, String tipoFirma, String sortBy, String sortDir, Pageable pageable);

    VucRepoFirmaGralSeResponseDTO findById(Integer id);

    VucRepoFirmaGralSeResponseDTO create(VucRepoFirmaGralSeRequestDTO dto);

    VucRepoFirmaGralSeResponseDTO update(Integer id, VucRepoFirmaGralSeRequestDTO dto);


    /** Tipos de firma (TIFR.*) para el dropdown principal. */
    List<EnumeracionDTO> listadoTiposFirma();

    /** Tipos de trámite activos para el combo del formulario. */
    List<ClasifProductoTraDTO> listadoTiposTramite();
}
