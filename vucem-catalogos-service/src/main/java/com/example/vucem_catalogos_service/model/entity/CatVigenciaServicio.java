package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "cat_vigencia_servicio")
public class CatVigenciaServicio {
    @Id
    @Column(name = "id_vigencia_servicio", nullable = false)
    private Short id;

    @Size(max = 20)
    @Column(name = "num_vigencia", length = 20)
    private String numVigencia;

    @Size(max = 20)
    @Column(name = "ide_tipo_vigencia", length = 20)
    private String ideTipoVigencia;

    @Size(max = 20)
    @Column(name = "ide_tipo_servicio_ceror", length = 20)
    private String ideTipoServicioCeror;

    @JoinColumns({
            @JoinColumn(name = "fec_fin_vigencia",
                    referencedColumnName = "fec_fin_vigencia"),
            @JoinColumn(name = "cve_pais",
                    referencedColumnName = "cve_pais"),
            @JoinColumn(name = "id_tratado_acuerdo",
                    referencedColumnName = "id_tratado_acuerdo")})
    @ManyToOne(fetch = FetchType.LAZY)
    private CatPaisTratadoAcuerdo catPaisTratadoAcuerdo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bloque", referencedColumnName = "id_tratado_acuerdo")
    private CatTratadoAcuerdo idBloque;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;


}