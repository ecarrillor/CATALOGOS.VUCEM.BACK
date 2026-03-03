package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatEspecieService;
import com.example.vucem_catalogos_service.model.dto.CategoriaTextil.CatCategoriaTextilResponseDTO;
import com.example.vucem_catalogos_service.model.dto.Especie.CatEspecieRequestDTO;
import com.example.vucem_catalogos_service.model.dto.Especie.CatEspecieResponseDTO;
import com.example.vucem_catalogos_service.model.dto.LeyendaTexto.CatLeyendaTextoRequestDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatCategoriaTextil;
import com.example.vucem_catalogos_service.model.entity.CatEspecie;
import com.example.vucem_catalogos_service.persistence.repo.ICatEspecieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class CatEspecieServiceImpl implements ICatEspecieService {

    @Autowired
    private ICatEspecieRepository iCatEspecieRepository;

    @Override
    public PageResponseDTO<CatEspecieResponseDTO> listarEspecie(String search, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if (search != null && !search.isBlank()) {

            String s = search.trim().toLowerCase();

            if (s.equals("activo")) {
                activo = true;
            }
            else if (s.equals("inactivo")) {
                activo = false;
            }
            else {
                texto = "%" + search.trim().toLowerCase() + "%";
            }
        }

        Page<CatEspecieResponseDTO> page =
                iCatEspecieRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatEspecieResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatEspecieResponseDTO crearEspecie(CatEspecieRequestDTO dto) {
        return null;
    }

    @Override
    public CatEspecieResponseDTO findById(Integer id) {
        CatEspecie entity = iCatEspecieRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Registro no encontrado con id: " + id
                        )
                );

        return CatEspecieResponseDTO.builder()
                .numEspecie(entity.getId())
                .descripcionEspecie(entity.getDescEspecie())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }

    @Override
    public CatEspecieResponseDTO update(Long id, CatLeyendaTextoRequestDTO dto) {
        return null;
    }
}
