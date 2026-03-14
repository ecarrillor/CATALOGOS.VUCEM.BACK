package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "cat_uso_espec_mercancia_ttra")
public class CatUsoEspecMercanciaTtra {
    @Id
    @Column(name = "id_uso_espec_mercancia_ttra", nullable = false)
    private Short id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_tramite")
    private CatTipoTramite idTipoTramite;

    @Size(max = 250)
    @Column(name = "desc_uso_esp_mercancia", length = 250)
    private String descUsoEspMercancia;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Column(name = "bln_req_registro_sanitario")
    private Boolean blnReqRegistroSanitario;


}