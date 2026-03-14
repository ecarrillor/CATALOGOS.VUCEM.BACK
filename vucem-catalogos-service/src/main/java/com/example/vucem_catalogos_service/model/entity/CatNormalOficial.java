package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "cat_normal_oficial")
public class CatNormalOficial {
    @Id
    @Column(name = "id_norma_oficial", nullable = false)
    private Integer id;

    @Size(max = 30)
    @NotNull
    @Column(name = "clave_norma", nullable = false, length = 30)
    private String claveNorma;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Size(max = 300)
    @Column(name = "desc_norma", length = 300)
    private String descNorma;

    @Column(name = "fec_publicacion")
    private LocalDate fecPublicacion;

    @Column(name = "fec_entrada_vigor")
    private LocalDate fecEntradaVigor;

    @Size(max = 20)
    @Column(name = "ide_clasif_norma", length = 20)
    private String ideClasifNorma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_pais", referencedColumnName = "cve_pais")
    private CatPais cvePais;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_norma_oficial_r", referencedColumnName = "id_norma_oficial")
    private CatNormalOficial idNormaOficialR;

    @Column(name = "bln_lote_estructurado")
    private Boolean blnLoteEstructurado;


}
