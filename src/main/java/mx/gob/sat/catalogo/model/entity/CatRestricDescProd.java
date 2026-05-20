package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "cat_restric_desc_prod")
public class CatRestricDescProd {

    @Id
    @Column(name = "id_restric_desc_prod", nullable = false)
    private Long idRestricDescProd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restriccion_ttra", referencedColumnName = "id_restriccion_ttra")
    private CatRestriccionTtra idRestriccionTtra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_descripcion_prod", referencedColumnName = "id")
    private CatDescripcionProd idDescripcionProd;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;
}
