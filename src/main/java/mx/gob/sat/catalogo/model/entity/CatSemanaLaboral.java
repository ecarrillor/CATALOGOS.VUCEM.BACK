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

/**
 * <b>Class:</b> CatSemanaLaboral.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de semanas laborales.</p>
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
@Table(name = "cat_semana_laboral")
public class CatSemanaLaboral {

    /** Identificador de la semana laboral. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer idSemanaLaboral;

    /** Descripcion de la semana laboral. */
    @NotNull
    @Size(max = 100)
    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    /** Indicador de activo. */
    @Column(name = "bln_activo")
    private Boolean blnActivo;
}
