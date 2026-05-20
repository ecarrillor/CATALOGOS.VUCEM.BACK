package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

/**
 * Entidad para el catalogo de tratado bloque pais.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Getter
@Setter
@Entity
@Table(name = "cat_tratado_bloque_pais")
public class CatTratadoBloquePai {

    @EmbeddedId
    private CatTratadoBloquePaiId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_pais", referencedColumnName = "cve_pais", insertable = false, updatable = false)
    private CatPais pais;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tratado_acuerdo", referencedColumnName = "id", insertable = false, updatable = false)
    private CatTratadoAcuerdo tratadoAcuerdo;

    @Column(name = "fec_captura")
    private LocalDate fecCaptura;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @Column(name = "bln_envio_electronico")
    private Boolean blnEnvioElectronico;

    @Column(name = "bln_muestra_certificado")
    private Boolean blnMuestraCertificado;
}
