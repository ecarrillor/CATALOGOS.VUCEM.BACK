package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
 * <b>Class:</b> CatAduanaTtra.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA para el catalogo de aduanas ttra.</p>
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
@Table(name = "cat_aduana_ttra")
public class CatAduanaTtra {

    /** Identificador de la aduana ttra (PK sin autoincremento). */
    @Id
    @Column(name = "id_aduana_ttra", nullable = false)
    private Long idAduanaTtra;

    /** Alias de la aduana. */
    @NotNull
    @Size(max = 100)
    @Column(name = "alias_aduana", nullable = false, length = 100)
    private String aliasAduana;

    /** Fecha de captura del registro. */
    @Column(name = "fec_captura")
    private LocalDate fecCaptura;

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

    /** Aduana asociada. */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cve_aduana", nullable = false, referencedColumnName = "cve_aduana")
    private CatAduana cveAduana;

    /** Tipo de tramite asociado. */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipo_tramite", nullable = false, referencedColumnName = "id_tipo_tramite")
    private CatTipoTramite idTipoTramite;
}
