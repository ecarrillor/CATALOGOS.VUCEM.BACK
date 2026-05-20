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
 * <b>Class:</b> CatVidaSilvestre.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de vida silvestre.</p>
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
@Table(name = "cat_vida_silvestre")
public class CatVidaSilvestre {

    /** Identificador de vida silvestre. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer idVidaSilvestre;

    /** Identificador de tipo de vida silvestre. */
    @Size(max = 20)
    @Column(name = "ide_tipo_vida_silvestre", length = 20)
    private String ideTipoVidaSilvestre;

    /** Descripcion del nombre comun. */
    @Size(max = 256)
    @Column(name = "desc_nombre_comun", length = 256)
    private String descNombreComun;

    /** Descripcion del nombre cientifico. */
    @Size(max = 256)
    @Column(name = "desc_nombre_cientifico", length = 256)
    private String descNombreCientifico;

    /** Identificador de clasificacion taxonomica. */
    @Size(max = 20)
    @Column(name = "ide_clasif_taxonomica", length = 20)
    private String ideClasifTaxonomica;

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

    /** Funcion zootecnica. */
    @Size(max = 100)
    @Column(name = "funcion_zootecnica", length = 100)
    private String funcionZootecnica;

    /** Genero asociado. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genero", referencedColumnName = "id_genero")
    private CatGenero idGenero;

    /** Especie asociada. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especie", referencedColumnName = "id")
    private CatEspecie idEspecie;
}
