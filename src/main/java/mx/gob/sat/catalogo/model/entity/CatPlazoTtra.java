package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "cat_plazo_ttra")
public class CatPlazoTtra {

    @EmbeddedId
    private CatPlazoTtraId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_tramite", referencedColumnName = "id", insertable = false, updatable = false)
    private CatTipoTramite idTipoTramite;

    @Column(name = "fec_ini_vigencia")
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @Column(name = "bln_activo")
    private Boolean blnActivo;
}
