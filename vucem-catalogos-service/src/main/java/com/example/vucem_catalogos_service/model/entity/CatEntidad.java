package com.example.vucem_catalogos_service.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
@Entity
@Table(name = "cat_entidad")
public class CatEntidad {
    @Id
    @Size(max = 6)
    @Column(name = "cve_entidad", nullable = false, length = 6)
    private String cveEntidad;

    @Size(max = 120)
    @Column(name = "nombre", length = 120)
    private String nombre;

    @Column(name = "fec_captura")
    private Instant fecCaptura;

    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    @Size(max = 2)
    @Column(name = "cod_entidad_idc", length = 2)
    private String codEntidadIdc;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_pais")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private CatPais cvePais;

    @JsonProperty("cvePais")
    public String getCvePaisCodigo() {
        return cvePais != null ? cvePais.getCvePais() : null;
    }

}
