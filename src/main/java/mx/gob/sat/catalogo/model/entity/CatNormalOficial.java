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
 * <b>Class:</b> CatNormalOficial.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de normas oficiales.</p>
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
@Table(name = "cat_normal_oficial")
public class CatNormalOficial {

    /** Identificador de la norma oficial. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer idNormalOficial;

    /** Clave de la norma. */
    @NotNull
    @Size(max = 30)
    @Column(name = "clave_norma", nullable = false, length = 30)
    private String claveNorma;

    /** Fecha de inicio de vigencia. */
    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    /** Descripcion de la norma. */
    @Size(max = 300)
    @Column(name = "desc_norma", length = 300)
    private String descNorma;

    /** Fecha de publicacion. */
    @Column(name = "fec_publicacion")
    private LocalDate fecPublicacion;

    /** Fecha de entrada en vigor. */
    @Column(name = "fec_entrada_vigor")
    private LocalDate fecEntradaVigor;

    /** Identificador de clasificacion de norma. */
    @Size(max = 20)
    @Column(name = "ide_clasif_norma", length = 20)
    private String ideClasifNorma;

    /** Indicador de lote estructurado. */
    @Column(name = "bln_lote_estructurado")
    private Boolean blnLoteEstructurado;

    /** Pais asociado. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_pais", referencedColumnName = "cve_pais")
    private CatPais cvePais;

    /** Norma oficial relacionada (auto-referencia). */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_norma_oficial_r", referencedColumnName = "id")
    private CatNormalOficial idNormaOficialR;
}
