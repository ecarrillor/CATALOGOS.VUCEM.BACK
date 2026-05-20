package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

/**
 * Entidad para el catalogo de partida fraccion.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Getter
@Setter
@Entity
@Table(name = "cat_partida_fraccion")
public class CatPartidaFraccion {

    @EmbeddedId
    private CatPartidaFraccionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_capitulo_fraccion", referencedColumnName = "cve_capitulo_fraccion", insertable = false, updatable = false)
    private CatCapituloFraccion cveCapituloFraccion;

    @Size(max = 1000)
    @Column(name = "nombre", length = 1000)
    private String nombre;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;
}
