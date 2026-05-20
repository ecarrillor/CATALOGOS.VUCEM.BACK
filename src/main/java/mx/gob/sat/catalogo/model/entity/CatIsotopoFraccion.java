package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "cat_isotopo_fraccion")
public class CatIsotopoFraccion {

    @Id
    @Column(name = "id_isotopo", nullable = false)
    private Short idIsotopo;

    @Size(max = 120)
    @Column(name = "nombre", length = 120)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_fraccion", referencedColumnName = "cve_fraccion")
    private CatFraccionArancelaria cveFraccion;

    @Column(name = "fec_ini_vigencia")
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @Column(name = "bln_activo")
    private Boolean blnActivo;
}
