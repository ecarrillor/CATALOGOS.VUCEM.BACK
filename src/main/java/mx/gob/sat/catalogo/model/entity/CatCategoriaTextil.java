package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatCategoriaTextil.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de categorias textiles.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Entidad
 */
@Getter
@Setter
@Entity
@Table(name = "cat_categoria_textil")
public class CatCategoriaTextil {

    /** Identificador de la categoria textil. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idCategoriaTextil;

    /** Descripcion de la categoria textil. */
    @Size(max = 120)
    @Column(name = "descripcion", length = 120)
    private String descripcion;

    /** Codigo de la categoria textil. */
    @Size(max = 20)
    @Column(name = "cod_categoria_textil", length = 20)
    private String codCategoriaTextil;

    /** Indicador NPA. */
    @Column(name = "bln_npa")
    private Boolean blnNpa;

    /** Factor de conversion. */
    @Column(name = "fact_conversion")
    private Double factConversion;

    /** Fecha de captura. */
    @Column(name = "fec_captura")
    private LocalDate fecCaptura;

    /** Fecha de inicio de vigencia. */
    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    /** Fecha de actualizacion. */
    @Column(name = "fec_actualizacion")
    private LocalDate fecActualizacion;

    /** Indicador de activo. */
    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    /** Unidad de medida principal. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_unidad_medida", referencedColumnName = "cve_unidad_medida")
    private CatUnidadMedida cveUnidadMedida;

    /** Unidad de medida equivalente. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_unidad_medida_equivalente", referencedColumnName = "cve_unidad_medida")
    private CatUnidadMedida cveUnidadMedidaEquivalente;
}
