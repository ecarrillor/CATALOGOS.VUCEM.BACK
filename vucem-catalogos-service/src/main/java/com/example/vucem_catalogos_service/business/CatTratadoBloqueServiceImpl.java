package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatTratadoBloqueService;
import com.example.vucem_catalogos_service.model.dto.CatTratadoBloqueDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatTratadoBloque;
import com.example.vucem_catalogos_service.model.entity.CatTratadoBloqueId;
import com.example.vucem_catalogos_service.persistence.repo.ICatTratadoAcuerdoRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTratadoBloqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CatTratadoBloqueServiceImpl implements ICatTratadoBloqueService {

    @Autowired
    private ICatTratadoBloqueRepository catTratadoBloqueRepository;

    @Autowired
    private ICatTratadoAcuerdoRepository catTratadoAcuerdoRepository;

    @Override
    public PageResponseDTO<CatTratadoBloqueDTO> list(String search, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if ("activo".equalsIgnoreCase(search)) {
            activo = true;
        } else if ("inactivo".equalsIgnoreCase(search)) {
            activo = false;
        } else {
            texto = search;
        }

        Page<CatTratadoBloqueDTO> page = catTratadoBloqueRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatTratadoBloqueDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatTratadoBloqueDTO findById(Short idTratadoAcuerdo, Short idBloque) {
        return catTratadoBloqueRepository.findByTratadoBloqueDTO(idTratadoAcuerdo, idBloque)
                .orElseThrow(() -> new RuntimeException(
                        "CatTratadoBloque no encontrado con idTratadoAcuerdo: " + idTratadoAcuerdo
                                + " e idBloque: " + idBloque));
    }

    @Override
    public CatTratadoBloqueDTO create(CatTratadoBloqueDTO dto) {
        CatTratadoBloqueId id = new CatTratadoBloqueId();
        id.setIdTratadoAcuerdo(dto.getIdTratadoAcuerdo());
        id.setIdBloque(dto.getIdBloque());

        CatTratadoBloque entity = new CatTratadoBloque();
        entity.setId(id);
        entity.setIdTratadoAcuerdo(
                catTratadoAcuerdoRepository.findById(dto.getIdTratadoAcuerdo())
                        .orElseThrow(() -> new RuntimeException("CatTratadoAcuerdo no encontrado: " + dto.getIdTratadoAcuerdo()))
        );
        entity.setIdBloque(
                catTratadoAcuerdoRepository.findById(dto.getIdBloque())
                        .orElseThrow(() -> new RuntimeException("CatTratadoAcuerdo (bloque) no encontrado: " + dto.getIdBloque()))
        );
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatTratadoBloque saved = catTratadoBloqueRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatTratadoBloqueDTO update(Short idTratadoAcuerdo, Short idBloque, CatTratadoBloqueDTO dto) {
        CatTratadoBloqueId id = new CatTratadoBloqueId();
        id.setIdTratadoAcuerdo(idTratadoAcuerdo);
        id.setIdBloque(idBloque);

        CatTratadoBloque entity = catTratadoBloqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "CatTratadoBloque no encontrado con idTratadoAcuerdo: " + idTratadoAcuerdo
                                + " e idBloque: " + idBloque));

        if (dto.getFecIniVigencia() != null) {
            entity.setFecIniVigencia(dto.getFecIniVigencia());
        }
        if (dto.getFecFinVigencia() != null) {
            entity.setFecFinVigencia(dto.getFecFinVigencia());
        }
        if (dto.getBlnActivo() != null) {
            entity.setBlnActivo(dto.getBlnActivo());
        }

        CatTratadoBloque saved = catTratadoBloqueRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatTratadoBloqueDTO mapToDTO(CatTratadoBloque entity) {
        return CatTratadoBloqueDTO.builder()
                .idTratadoAcuerdo(entity.getId().getIdTratadoAcuerdo())
                .idBloque(entity.getId().getIdBloque())
                .nombreTratadoAcuerdo(entity.getIdTratadoAcuerdo() != null ? entity.getIdTratadoAcuerdo().getNombre() : null)
                .nombreBloque(entity.getIdBloque() != null ? entity.getIdBloque().getNombre() : null)
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
