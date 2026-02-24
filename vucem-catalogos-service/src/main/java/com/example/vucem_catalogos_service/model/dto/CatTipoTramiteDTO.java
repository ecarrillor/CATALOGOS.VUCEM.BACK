package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatTipoTramiteDTO {
    private Long id;
    private String cveServicio;
    private String descServicio;
    private String cveSubservicio;
    private String descSubservicio;
    private String cveModalidad;
    private String descModalidad;
    private String cveFlujo;
    private String descFlujo;
    private Short nivelServicio;
    private Long idDependencia;
    private String nombreDependencia;
    private String nomServAxway;
    private String nomMensajeAxway;
    private String urlAxway;
    private LocalDate fecCaptura;
    private LocalDate fecFinVigencia;
    private String nombre;
    private Boolean blnReplicaInfo;
    private Boolean blnAutomatico;
    private LocalDate fecIniVigencia;
    private Boolean blnActivo;
    private Boolean blnAsignacion;
    private Short cveModulo;
}
