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

import java.time.Instant;

/**
 * <b>Class:</b> CatEsquemaRegla8.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de esquemas de regla 8.</p>
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
@Table(name = "cat_esquema_regla8")
public class CatEsquemaRegla8 {

    /** Identificador del esquema de regla 8. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Short idEsquemaRegla8;

    /** Nombre del esquema de regla 8. */
    @Size(max = 2000)
    @Column(name = "nombre", length = 2000)
    private String nombre;

    /** Descripcion del requisito. */
    @Column(name = "desc_requisito", columnDefinition = "TEXT")
    private String descRequisito;

    /** Fecha de inicio de vigencia. */
    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;
}
