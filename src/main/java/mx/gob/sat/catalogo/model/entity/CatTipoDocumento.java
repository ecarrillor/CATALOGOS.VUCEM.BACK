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
 * <b>Class:</b> CatTipoDocumento.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de tipos de documento.</p>
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
@Table(name = "cat_tipo_documento")
public class CatTipoDocumento {

    /** Identificador del tipo de documento. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Short idTipoDocumento;

    /** Nombre del tipo de documento. */
    @Size(max = 2000)
    @Column(name = "nombre", length = 2000)
    private String nombre;

    /** Fecha de captura. */
    @Column(name = "fec_captura")
    private Instant fecCaptura;

    /** Fecha de fin de vigencia. */
    @Column(name = "fec_fin_vigencia")
    private Instant fecFinVigencia;

    /** Fecha de inicio de vigencia. */
    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private Instant fecIniVigencia;

    /** Indicador de activo. */
    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    /** Identificador de rango de resolucion de imagen. */
    @Size(max = 20)
    @Column(name = "ide_rango_resolucion_imagen", length = 20)
    private String ideRangoResolucionImagen;

    /** Tamanio maximo. */
    @Column(name = "tamanio_maximo")
    private Short tamanioMaximo;
}
