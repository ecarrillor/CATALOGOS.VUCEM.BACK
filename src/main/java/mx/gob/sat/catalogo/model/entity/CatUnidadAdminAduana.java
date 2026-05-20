package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

/**
 * <b>Class:</b> CatUnidadAdminAduana.java <br>
 * <b>Description:</b>
 * <p>Entidad JPA que representa el catalogo de unidades administrativas de aduana.</p>
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
@Table(name = "cat_unidad_admin_aduana")
public class CatUnidadAdminAduana {

    @EmbeddedId
    private CatUnidadAdminAduanaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_unidad_administrativa", referencedColumnName = "cve_unidad_administrativa", insertable = false, updatable = false)
    private CatUnidadAdministrativa cveUnidadAdministrativa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_aduana", referencedColumnName = "cve_aduana", insertable = false, updatable = false)
    private CatAduana cveAduana;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;
}
