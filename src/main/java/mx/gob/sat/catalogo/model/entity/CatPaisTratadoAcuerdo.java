package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

/**
 * Entidad para el catalogo de pais tratado acuerdo.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Getter
@Setter
@Entity
@Table(name = "cat_pais_tratado_acuerdo")
public class CatPaisTratadoAcuerdo {

    @EmbeddedId
    private CatPaisTratadoAcuerdoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_pais", referencedColumnName = "cve_pais", insertable = false, updatable = false)
    private CatPais cvePais;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tratado_acuerdo", referencedColumnName = "id", insertable = false, updatable = false)
    private CatTratadoAcuerdo idTratadoAcuerdo;

    @Column(name = "fec_captura")
    private LocalDate fecCaptura;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @NotNull
    @Column(name = "fec_fin_vigencia", nullable = false)
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Column(name = "bln_envio_electronico")
    private Boolean blnEnvioElectronico;
}
