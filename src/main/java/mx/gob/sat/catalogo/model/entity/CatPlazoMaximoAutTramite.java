package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "cat_plazo_maximo_aut_tramite")
public class CatPlazoMaximoAutTramite {

    @EmbeddedId
    private CatPlazoMaximoAutTramiteId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_tramite", referencedColumnName = "id", insertable = false, updatable = false)
    private CatTipoTramite idTipoTramite;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @Column(name = "plazo_anios")
    private Short plazoAnios;

    @Size(max = 20)
    @Column(name = "ide_plazo_meses", length = 20)
    private String idePlazoMeses;

    @Column(name = "bln_ilimitado")
    private Boolean blnIlimitado;

    @Size(max = 8)
    @Column(name = "plazo", length = 8)
    private String plazo;

    @Column(name = "bln_asignacion_fecha_fin")
    private Boolean blnAsignacionFechaFin;

    @Column(name = "bln_activo")
    private Boolean blnActivo;
}
