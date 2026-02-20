package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatAprobCertService;
import com.example.vucem_catalogos_service.model.dto.CatAprobCertSeRequestDTO;
import com.example.vucem_catalogos_service.model.dto.CatAprobCertSeResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatAprobCertSe;
import com.example.vucem_catalogos_service.model.entity.CatLaboratorioTtra;
import com.example.vucem_catalogos_service.persistence.repo.ICatAprobCertSeRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatLaboratorioTtraRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatUnidadAdministrativaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CatAprobCertServiceImpl implements ICatAprobCertService {
    @Autowired
    private ICatAprobCertSeRepository catAprobCertSeRepository;

    @Autowired
    private ICatUnidadAdministrativaRepository catUnidadAdministrativaRepository;

    @Autowired
    private ICatLaboratorioTtraRepository iCatLaboratorioTtraRepository;

    public PageResponseDTO<CatAprobCertSeResponseDTO> list(String search, Pageable pageable) {

        Boolean activo = null;
        String texto = null;

        if (search != null && !search.isBlank()) {

            String s = search.trim().toLowerCase();
            if (s.equals("activo")) {
                activo = true;
            } else if (s.equals("inactivo")) {
                activo = false;
            } else {
                texto = search;
            }
        }

        Page<CatAprobCertSeResponseDTO> page =
                catAprobCertSeRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatAprobCertSeResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatAprobCertSeResponseDTO findById(Short id) {

        return catAprobCertSeRepository.findByCatAprobeId(id);
    }

    @Override
    public CatAprobCertSeResponseDTO create(CatAprobCertSeRequestDTO dto) {
        CatAprobCertSe entity = new CatAprobCertSe();

        entity.setId(dto.getIdAprobCert());

        entity.setCveUnidadAdministrativa(
                catUnidadAdministrativaRepository
                        .findById(dto.getCveUnidadAdministrativaId())
                        .orElseThrow(() -> new RuntimeException("Laboratorio no encontrado"))
        );

        entity.setIdeTipoAprobCertSe(mapTipoToCodigo(dto.getTipoAprobacion()));

        entity.setNumAprobCert(dto.getNumAprobCert());
        entity.setFecEmision(dto.getFecEmision());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatAprobCertSe saved = catAprobCertSeRepository.save(entity);

        return mapToResponse(saved);
    }

    @Override
    public CatAprobCertSeResponseDTO update(Short id, CatAprobCertSeRequestDTO dto) {
        CatAprobCertSe entity = catAprobCertSeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
        System.out.println(
                "xxxx"
        );
        entity.setCveUnidadAdministrativa(
                catUnidadAdministrativaRepository
                        .findById(dto.getCveUnidadAdministrativaId())
                        .orElseThrow(() -> new RuntimeException("Laboratorio no encontrado"))
        );

        System.out.println(
                "mamooo"
        );
        entity.setIdeTipoAprobCertSe(
                mapTipoToCodigo(dto.getTipoAprobacion())
        );

        entity.setNumAprobCert(dto.getNumAprobCert());
        entity.setFecEmision(dto.getFecEmision());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatAprobCertSe updated = catAprobCertSeRepository.save(entity);

        return mapToResponse(updated);
    }

    @Override
    public List<SelectDTO> listadoLaboratorio() {
        List<CatLaboratorioTtra> productos = iCatLaboratorioTtraRepository.findAll();
        List<SelectDTO> resultado = new ArrayList<>();

        for (CatLaboratorioTtra producto : productos) {
            SelectDTO dto = new SelectDTO();
            dto.setId(producto.getId());
            dto.setNombre(producto.getDescLaboratorio());
            resultado.add(dto);
        }
        return resultado;
    }


    private CatAprobCertSeResponseDTO mapToResponse(CatAprobCertSe e) {

        String tipoTexto =
                "TIAPC.AC".equals(e.getIdeTipoAprobCertSe()) ? "Acreditación" :
                        "TIAPC.AP".equals(e.getIdeTipoAprobCertSe()) ? "Aprobación" :
                                e.getIdeTipoAprobCertSe();

        return new CatAprobCertSeResponseDTO(
                e.getId(),
                e.getCveUnidadAdministrativa().getNombre(),
                tipoTexto,
                e.getNumAprobCert(),
                e.getFecEmision(),
                e.getFecIniVigencia(),
                e.getFecFinVigencia(),
                e.getBlnActivo()
        );
    }


    private String mapTipoToCodigo(String tipo) {

        if (tipo == null) {
            throw new RuntimeException("Tipo de aprobación es obligatorio");
        }

        String t = tipo.trim().toLowerCase();

        if (t.equals("acreditación") || t.equals("acreditacion")) {
            return "TIAPC.AC";
        }

        if (t.equals("aprobación") || t.equals("aprobacion")) {
            return "TIAPC.AP";
        }

        throw new RuntimeException("Tipo de aprobación inválido: " + tipo);
    }


}
