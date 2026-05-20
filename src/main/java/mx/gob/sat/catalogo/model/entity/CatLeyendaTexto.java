package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatLeyendaTexto.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de leyendas de texto.</p>
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
@Table(name = "cat_leyenda_texto")
public class CatLeyendaTexto {

    /** Identificador de la leyenda de texto. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idLeyendaTexto;

    /** Identificador de tipo de leyenda de texto. */
    @NotNull
    @Size(max = 20)
    @Column(name = "ide_tipo_leyenda_texto", nullable = false, length = 20)
    private String ideTipoLeyendaTexto;

    /** Numero de anio. */
    @Column(name = "num_anio")
    private Short numAnio;

    /** Leyenda. */
    @NotNull
    @Size(max = 2000)
    @Column(name = "leyenda", nullable = false, length = 2000)
    private String leyenda;

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
}
