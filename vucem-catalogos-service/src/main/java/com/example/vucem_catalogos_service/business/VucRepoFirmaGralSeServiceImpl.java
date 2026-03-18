package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.IVucRepoFirmaGralSeService;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.EnumeracionDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.VucRepoFirmaGralSe.VucRepoFirmaGralSeRequestDTO;
import com.example.vucem_catalogos_service.model.dto.VucRepoFirmaGralSe.VucRepoFirmaGralSeResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatTipoTramite;
import com.example.vucem_catalogos_service.model.entity.VucRepoFirmaGralSe;
import com.example.vucem_catalogos_service.persistence.repo.IVucRepoFirmaGralSeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Slf4j
@Service
@Transactional
public class VucRepoFirmaGralSeServiceImpl implements IVucRepoFirmaGralSeService {

    private static final String TIFR_MASIVA = "TIFR.TFMSV";
    private static final String TIFR_FACS   = "TIFR.TFFCS";
    private static final int    MAX_IMG_DIM  = 600;

    @Autowired
    private IVucRepoFirmaGralSeRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    // -------------------------------------------------------------------------
    // CRUD
    // -------------------------------------------------------------------------

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public PageResponseDTO<VucRepoFirmaGralSeResponseDTO> listAll(String search, String tipoFirma, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if (search != null && !search.isBlank()) {
            String s = search.trim().toLowerCase();
            if (s.equals("activo")) {
                activo = true;
            } else if (s.equals("inactivo")) {
                activo = false;
            } else {
                texto = "%" + s + "%";
            }
        }

        log.info("listAll_vuc_repo_firma search={} tipoFirma={} activo={}", texto, tipoFirma, activo);

        Page<VucRepoFirmaGralSeResponseDTO> page = repository.search(texto, tipoFirma, activo, pageable);

        return PageResponseDTO.<VucRepoFirmaGralSeResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public VucRepoFirmaGralSeResponseDTO findById(Integer id) {
        VucRepoFirmaGralSe entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Registro no encontrado con id: " + id));
        return toResponseDTO(entity);
    }

    @Override
    public VucRepoFirmaGralSeResponseDTO create(VucRepoFirmaGralSeRequestDTO dto) {
        validarReglasNegocio(dto, false, null);

        Integer maxId = repository.findMaxId();
        int nuevoId = (maxId == null ? 1 : maxId + 1);

        VucRepoFirmaGralSe entity = new VucRepoFirmaGralSe();
        entity.setId(nuevoId);
        mapear(entity, dto);
        entity.setFecCreacion(LocalDate.now());

        if (TIFR_MASIVA.equals(dto.getIdeTipoFirma()) && dto.getPassword() != null) {
            entity.setPassword(encrypt(dto.getPassword()));
        }

        if (entity.getPuesto() == null) {
            entity.setPuesto(" ");
        }

        try {
            VucRepoFirmaGralSe guardado = repository.save(entity);
            log.info("creado_vuc_repo_firma id={}", guardado.getId());
            return toResponseDTO(guardado);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe un registro con los mismos datos");
        }
    }

    @Override
    public VucRepoFirmaGralSeResponseDTO update(Integer id, VucRepoFirmaGralSeRequestDTO dto) {
        VucRepoFirmaGralSe entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Registro no encontrado con id: " + id));

        if (dto.getIdeTipoFirma()  != null) entity.setIdeTipoFirma(dto.getIdeTipoFirma());
        if (dto.getIdTipoTramite() != null)
            entity.setIdTipoTramite(entityManager.getReference(CatTipoTramite.class, dto.getIdTipoTramite()));
        if (dto.getRfc()           != null) entity.setRfc(dto.getRfc());
        if (dto.getPuesto()        != null) entity.setPuesto(dto.getPuesto());
        if (dto.getFecIniVigenia() != null) entity.setFecIniVigenia(dto.getFecIniVigenia());
        if (dto.getFecFinVigenia() != null) entity.setFecFinVigenia(dto.getFecFinVigenia());
        if (dto.getBlnActivo()     != null) entity.setBlnActivo(dto.getBlnActivo());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            entity.setPassword(encrypt(dto.getPassword()));
        }

        String certNuevo = toBase64(dto.getCert());
        String keyNuevo  = toBase64(dto.getKey());
        byte[] facsimilNuevo = toBytes(dto.getFacsimil());
        byte[] selloNuevo    = toBytes(dto.getSello());

        if (certNuevo    != null) entity.setCert(certNuevo);
        if (keyNuevo     != null) entity.setKey(keyNuevo);
        if (facsimilNuevo != null) entity.setFacsimil(facsimilNuevo);
        if (selloNuevo   != null) entity.setSello(selloNuevo);

        entity.setFecModificacion(LocalDate.now());
        log.info("actualizado_vuc_repo_firma id={}", id);
        return toResponseDTO(entity);
    }



    // -------------------------------------------------------------------------
    // Combos
    // -------------------------------------------------------------------------

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<EnumeracionDTO> listadoTiposFirma() {
        return repository.obtenerTiposFirma().stream()
                .map(p -> new EnumeracionDTO(p.getClave(), p.getDescripcion()))
                .toList();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ClasifProductoTraDTO> listadoTiposTramite() {
        return repository.obtenerTiposTramite();
    }

    // -------------------------------------------------------------------------
    // Privados — mapeo
    // -------------------------------------------------------------------------

    private void mapear(VucRepoFirmaGralSe entity, VucRepoFirmaGralSeRequestDTO dto) {
        entity.setIdeTipoFirma(dto.getIdeTipoFirma());
        entity.setIdTipoTramite(entityManager.getReference(CatTipoTramite.class, dto.getIdTipoTramite()));
        entity.setRfc(dto.getRfc());
        entity.setPuesto(dto.getPuesto());
        entity.setFecIniVigenia(dto.getFecIniVigenia());
        entity.setFecFinVigenia(dto.getFecFinVigenia());
        entity.setBlnActivo(dto.getBlnActivo());
        entity.setPassword(dto.getPassword());
        entity.setCert(toBase64(dto.getCert()));
        entity.setKey(toBase64(dto.getKey()));
        entity.setFacsimil(toBytes(dto.getFacsimil()));
        entity.setSello(toBytes(dto.getSello()));
    }

    private VucRepoFirmaGralSeResponseDTO toResponseDTO(VucRepoFirmaGralSe entity) {
        CatTipoTramite t = entity.getIdTipoTramite();
        return VucRepoFirmaGralSeResponseDTO.builder()
                .id(entity.getId())
                .ideTipoFirma(entity.getIdeTipoFirma())
                .idTipoTramite(t != null ? t.getId() : null)
                .descTipoTramite(t != null
                        ? (t.getDescModalidad() != null ? t.getDescModalidad() : t.getDescSubservicio())
                        : null)
                .rfc(entity.getRfc())
                .puesto(entity.getPuesto())
                .fecIniVigenia(entity.getFecIniVigenia())
                .fecFinVigenia(entity.getFecFinVigenia())
                .blnActivo(entity.getBlnActivo())
                .tieneCert(entity.getCert()     != null)
                .tieneKey(entity.getKey()       != null)
                //.tieneFacsimil(entity.getFacsimil())
                //.tieneSello(entity.getSello())
                .build();
    }

    /** Convierte MultipartFile a byte[] para campos binarios (facsimil, sello). */
    private byte[] toBytes(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;
        try {
            return file.getBytes();
        } catch (IOException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se pudo leer el archivo: " + file.getOriginalFilename());
        }
    }

    /** Convierte MultipartFile a String Base64 para campos TEXT (cert, key). */
    private String toBase64(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;
        try {
            return Base64.getEncoder().encodeToString(file.getBytes());
        } catch (IOException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se pudo leer el archivo: " + file.getOriginalFilename());
        }
    }

    // -------------------------------------------------------------------------
    // Privados — validaciones
    // -------------------------------------------------------------------------

    private void validarReglasNegocio(VucRepoFirmaGralSeRequestDTO dto,
                                      boolean esEdicion, Boolean esDiferente) {
        List<String> errores = new ArrayList<>();

        validarCamposRequeridosPorTipo(dto, esEdicion, esDiferente, errores);
        validarFechas(dto, esEdicion, errores);
        validarUnicidad(dto, errores);

        if (TIFR_MASIVA.equals(dto.getIdeTipoFirma())) {
            if (!esEdicion || Boolean.TRUE.equals(esDiferente)) {
                validarRfc(dto, errores);
            }
        } else if (TIFR_FACS.equals(dto.getIdeTipoFirma())) {
            boolean validarImagenes = !esEdicion
                    || Boolean.TRUE.equals(esDiferente)
                    || (dto.getEditar() != null && dto.getEditar() == 1)
                    || dto.getFacsimil() != null
                    || dto.getSello() != null;

            if (validarImagenes) {
                validarImagen(dto.getFacsimil(), "facsimil", errores);
                validarImagen(dto.getSello(), "sello", errores);
            }
        }

        if (!errores.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    String.join("; ", errores));
        }
    }

    private void validarCamposRequeridosPorTipo(VucRepoFirmaGralSeRequestDTO dto,
                                                boolean esEdicion, Boolean esDiferente,
                                                List<String> errores) {
        String tipo = dto.getIdeTipoFirma();
        if (tipo == null) return;

        boolean validarCredenciales = !esEdicion || Boolean.TRUE.equals(esDiferente);
        boolean validarImagenes     = !esEdicion || Boolean.TRUE.equals(esDiferente)
                || (dto.getEditar() != null && dto.getEditar() == 1);

        if (TIFR_MASIVA.equals(tipo) && validarCredenciales) {
            if (dto.getRfc()      == null || dto.getRfc().isBlank())      errores.add("rfc es requerido para " + tipo);
            if (dto.getCert()     == null || dto.getCert().isEmpty())     errores.add("cert (.cer) es requerido para " + tipo);
            if (dto.getKey()      == null || dto.getKey().isEmpty())      errores.add("key (.key) es requerido para " + tipo);
            if (dto.getPassword() == null || dto.getPassword().isBlank()) errores.add("password es requerido para " + tipo);
        } else if (TIFR_FACS.equals(tipo) && validarImagenes) {
            if (dto.getFacsimil() == null || dto.getFacsimil().isEmpty())
                errores.add("facsimil es requerido para " + tipo);
            if (dto.getSello() == null || dto.getSello().isEmpty())
                errores.add("sello es requerido para " + tipo);
        }
    }

    private void validarFechas(VucRepoFirmaGralSeRequestDTO dto, boolean esEdicion, List<String> errores) {
        LocalDate hoy = LocalDate.now();

        if (!esEdicion && dto.getFecIniVigenia() != null && dto.getFecIniVigenia().isBefore(hoy)) {
            errores.add("fecIniVigenia no puede ser anterior a la fecha actual");
        }

        if (dto.getFecIniVigenia() != null && dto.getFecFinVigenia() != null
                && !dto.getFecFinVigenia().isAfter(dto.getFecIniVigenia())) {
            errores.add("fecFinVigenia debe ser posterior a fecIniVigenia");
        }
    }

    private void validarUnicidad(VucRepoFirmaGralSeRequestDTO dto, List<String> errores) {
        if (dto.getIdeTipoFirma() == null || dto.getIdTipoTramite() == null || dto.getBlnActivo() == null) return;

        List<VucRepoFirmaGralSe> existentes = repository.findByTipoFirmaAndTipoTramiteAndActivo(
                dto.getIdeTipoFirma(), dto.getIdTipoTramite(), dto.getBlnActivo());

        if (!existentes.isEmpty()) {
            String estado = Boolean.TRUE.equals(dto.getBlnActivo()) ? "Activo" : "Inactivo";
            errores.add(String.format(
                    "Ya existe un registro %s con ideTipoFirma=%s e idTipoTramite=%s",
                    estado, dto.getIdeTipoFirma(), dto.getIdTipoTramite()));
        }
    }

    private void validarImagen(MultipartFile file, String campo, List<String> errores) {
        if (file == null || file.isEmpty()) return;

        String contentType = file.getContentType();
        if (contentType == null
                || (!contentType.equals("image/jpeg")
                &&  !contentType.equals("image/jpg")
                &&  !contentType.equals("image/png"))) {
            errores.add(campo + ": solo se permiten imágenes JPG o PNG");
            return;
        }

        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                errores.add(campo + ": el archivo no es una imagen válida");
                return;
            }
            if (image.getWidth() > MAX_IMG_DIM || image.getHeight() > MAX_IMG_DIM) {
                errores.add(campo + ": las dimensiones no pueden superar " + MAX_IMG_DIM + "x" + MAX_IMG_DIM + " px");
            }
        } catch (IOException ex) {
            errores.add(campo + ": no se pudo leer la imagen");
        }
    }

    private void validarRfc(VucRepoFirmaGralSeRequestDTO dto, List<String> errores) {
        if (dto.getRfc() == null) return;
        if (!dto.getRfc().matches("^([A-Z\\&\\.\\u00D1\\-\\_\\+\\s]{4})\\d{6}([A-Z\\w]{3})$")) {
            errores.add("rfc: formato inválido");
        }
        // TODO: integrar validación FIEL real (BouncyCastle / SAT SDK)
        log.warn("validarRfc: TODO — integrar validación FIEL real. Se omite por ahora.");
    }

    // -------------------------------------------------------------------------
    // Cifrado (TODO: reemplazar con implementación real)
    // -------------------------------------------------------------------------

    private String encrypt(String password) {
        // TODO: implementar cifrado AES real
        log.warn("encrypt: TODO — implementar cifrado real. Guardando en texto plano temporalmente.");
        return password;
    }

    private String decrypt(String encryptedPassword) {
        // TODO: implementar descifrado AES real
        return encryptedPassword;
    }
}
