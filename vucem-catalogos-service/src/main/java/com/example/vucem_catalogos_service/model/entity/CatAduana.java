package com.example.vucem_catalogos_service.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
@Entity
@Table(name = "cat_aduana")
public class CatAduana {
    @Id
    @Size(max = 3)
    @Column(name = "cve_aduana", nullable = false, length = 3)
    private String cveAduana;

    @Size(max = 120)
    @Column(name = "nombre", length = 120)
    private String nombre;

    @Column(name = "fec_captura")
    private Instant fecCaptura;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo")
    private Boolean blnActivo;

    @Size(max = 30)
    @Column(name = "correo_electronico", length = 30)
    private String correoElectronico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_tipo_aduana", referencedColumnName = "cve_tipo_aduana")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CatTipoAduana tipoAduana;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_entidad", referencedColumnName = "cve_entidad")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CatEntidad entidad;

}
