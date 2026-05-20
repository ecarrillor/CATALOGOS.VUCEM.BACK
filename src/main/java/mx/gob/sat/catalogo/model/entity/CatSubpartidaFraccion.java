package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

/**
 * Entidad para el catalogo de subpartida fraccion.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Getter
@Setter
@Entity
@Table(name = "cat_subpartida_fraccion")
public class CatSubpartidaFraccion {

    @EmbeddedId
    private CatSubpartidaFraccionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "cve_capitulo_fraccion", referencedColumnName = "cve_capitulo_fraccion", insertable = false, updatable = false),
        @JoinColumn(name = "cve_partida_fraccion", referencedColumnName = "cve_partida_fraccion", insertable = false, updatable = false)
    })
    private CatPartidaFraccion catPartidaFraccion;

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
