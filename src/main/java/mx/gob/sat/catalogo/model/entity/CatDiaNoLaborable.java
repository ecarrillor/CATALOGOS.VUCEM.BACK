package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
@Table(name = "cat_dia_no_laborable")
public class CatDiaNoLaborable {

    @EmbeddedId
    private CatDiaNoLaborableId id;

    @ManyToOne
    @JoinColumn(name = "cve_calendario", referencedColumnName = "cve_calendario", insertable = false, updatable = false)
    private CatCalendario cveCalendario;

    @Size(max = 256)
    @Column(name = "descripcion", length = 256)
    private String descripcion;

    @Column(name = "fec_ini_vigencia")
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @Column(name = "bln_activo")
    private Boolean blnActivo;
}
