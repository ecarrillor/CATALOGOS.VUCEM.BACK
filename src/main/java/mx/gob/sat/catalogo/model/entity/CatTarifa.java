package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "cat_tarifa")
public class CatTarifa {

    @EmbeddedId
    private CatTarifaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_tramite", referencedColumnName = "id", insertable = false, updatable = false)
    private CatTipoTramite idTipoTramite;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @Column(name = "tarifa", precision = 19, scale = 2)
    private BigDecimal tarifa;

    @Column(name = "bln_activo")
    private Boolean blnActivo;
}
