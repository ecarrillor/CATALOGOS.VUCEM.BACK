package com.example.vucem_catalogos_service.model.dto.VucRepoFirmaGralSe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VucRepoFirmaGralSeRequestDTO {

    private String ideTipoFirma;

    private Long idTipoTramite;

    private String rfc;

    private String puesto;

    private LocalDate fecIniVigenia;

    private LocalDate fecFinVigenia;

    private Boolean blnActivo;

    private String password;

    /** Archivo .cer — se almacena como texto (base64/PEM) en la columna cert (TEXT) */
    private MultipartFile cert;

    /** Archivo .key — se almacena como texto (base64/PEM) en la columna key (TEXT) */
    private MultipartFile key;

    /** Imagen de facsímil (JPG/PNG, máx 600×600 px) */
    private MultipartFile facsimil;

    /** Imagen de sello (JPG/PNG, máx 600×600 px) */
    private MultipartFile sello;

    /** 0/1 — indica si el usuario eligió editar imágenes en modo update */
    private Integer editar;
}
