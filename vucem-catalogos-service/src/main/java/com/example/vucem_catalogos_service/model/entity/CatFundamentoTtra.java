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
@Table(name = "cat_fundamento_ttra")
public class CatFundamentoTtra {
    @Id
    @Column(name = "id_fundamento_ttra", nullable = false)
    private Short id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_tramite")
    private CatTipoTramite idTipoTramite;

    @Size(max = 20)
    @Column(name = "ide_tipo_fundamento_ttra", length = 20)
    private String ideTipoFundamentoTtra;

    @Size(max = 512)
    @Column(name = "desc_fundamento", length = 512)
    private String descFundamento;

    @Size(max = 2000)
    @Column(name = "desc_contenido_fundamento", length = 2000)
    private String descContenidoFundamento;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}