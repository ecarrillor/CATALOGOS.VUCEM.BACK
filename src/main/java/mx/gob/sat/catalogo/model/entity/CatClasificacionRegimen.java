package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

/**
 * Entidad para el catalogo de clasificacion de regimen.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Getter
@Setter
@Entity
@Table(name = "cat_clasificacion_regimen")
public class CatClasificacionRegimen {

    @EmbeddedId
    private CatClasificacionRegimenId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_regimen", referencedColumnName = "cve_regimen", insertable = false, updatable = false)
    private CatRegimen cveRegimen;

    @Size(max = 120)
    @Column(name = "nombre", length = 120)
    private String nombre;

    @Size(max = 3)
    @Column(name = "cod_regimen", length = 3)
    private String codRegimen;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;
}
