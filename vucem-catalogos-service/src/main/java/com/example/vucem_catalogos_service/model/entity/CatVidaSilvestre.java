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
@Table(name = "cat_vida_silvestre")
public class CatVidaSilvestre {
    @Id
    @Column(name = "id_vida_silvestre", nullable = false)
    private Integer id;

    @Size(max = 20)
    @Column(name = "ide_tipo_vida_silvestre", length = 20)
    private String ideTipoVidaSilvestre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genero")
    private CatGenero idGenero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especie")
    private CatEspecie idEspecie;

    @Size(max = 256)
    @Column(name = "desc_nombre_comun", length = 256)
    private String descNombreComun;

    @Size(max = 256)
    @Column(name = "desc_nombre_cientifico", length = 256)
    private String descNombreCientifico;

    @Size(max = 20)
    @Column(name = "ide_clasif_taxonomica", length = 20)
    private String ideClasifTaxonomica;

    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Size(max = 100)
    @Column(name = "funcion_zootecnica", length = 100)
    private String funcionZootecnica;


}
