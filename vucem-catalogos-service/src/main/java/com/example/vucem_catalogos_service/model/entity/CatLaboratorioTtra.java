package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "cat_laboratorio_ttra")
public class CatLaboratorioTtra {
    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;
    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;
    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;
    @Size(max = 20)
    @Column(name = "ide_tipo_laboratorio", length = 20)
    private String ideTipoLaboratorio;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipo_tramite", nullable = false)
    private CatTipoTramite idTipoTramite;
    @Size(max = 200)
    @NotNull
    @Column(name = "desc_laboratorio", nullable = false, length = 200)
    private String descLaboratorio;
    @Id
    @Column(name = "id_laboratorio_ttra", nullable = false)
    private Long id;
}
