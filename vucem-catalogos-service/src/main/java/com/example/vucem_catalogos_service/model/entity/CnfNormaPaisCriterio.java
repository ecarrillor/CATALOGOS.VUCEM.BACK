package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "cnf_norma_pais_criterio")
public class CnfNormaPaisCriterio {
    @Id
    @Column(name = "id_norma_pais_criterio", nullable = false)
    private Integer id;

    @JoinColumns({
            @JoinColumn(name = "fec_fin_vigencia",
                    referencedColumnName = "fec_fin_vigencia"),
            @JoinColumn(name = "cve_pais",
                    referencedColumnName = "cve_pais"),
            @JoinColumn(name = "id_tratado_acuerdo",
                    referencedColumnName = "id_tratado_acuerdo")})
    @ManyToOne(fetch = FetchType.LAZY)
    private CatPaisTratadoAcuerdo catPaisTratadoAcuerdo;

    @Size(max = 100)
    @Column(name = "desc_norma_origen", length = 100)
    private String descNormaOrigen;

    @Size(max = 1000)
    @Column(name = "primer_parrafo", length = 1000)
    private String primerParrafo;

    @Size(max = 1000)
    @Column(name = "segundo_parrafo", length = 1000)
    private String segundoParrafo;

    @Size(max = 1000)
    @Column(name = "tercer_parrafo", length = 1000)
    private String tercerParrafo;

    @Size(max = 2000)
    @Column(name = "cuarto_parrafo", length = 2000)
    private String cuartoParrafo;

    @Size(max = 2000)
    @Column(name = "cuarto_parrafo_exp_autorizado", length = 2000)
    private String cuartoParrafoExpAutorizado;

    @Size(max = 500)
    @Column(name = "asunto", length = 500)
    private String asunto;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Short blnActivo;

    @Column(name = "bln_sentido_calificacion")
    private Short blnSentidoCalificacion;


}