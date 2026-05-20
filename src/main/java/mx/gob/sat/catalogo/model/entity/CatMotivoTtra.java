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

@Getter
@Setter
@Entity
@Table(name = "cat_motivo_ttra")
public class CatMotivoTtra {

    @Id
    @Column(name = "id_motivo_ttra", nullable = false)
    private Long idMotivoTtra;

    @Size(max = 20)
    @Column(name = "ide_tipo_motivo_ttra", length = 20)
    private String ideTipoMotivoTtra;

    @Size(max = 250)
    @Column(name = "desc_motivo", length = 250)
    private String descMotivo;

    @Size(max = 1000)
    @Column(name = "desc_contenido_motivo", length = 1000)
    private String descContenidoMotivo;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_tramite", referencedColumnName = "id_tipo_tramite")
    private CatTipoTramite idTipoTramite;
}
