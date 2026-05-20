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
@Table(name = "cat_laboratorio_ttra")
public class CatLaboratorioTtra {

    @Id
    @Column(name = "id_laboratorio_ttra", nullable = false)
    private Long idLaboratorioTtra;

    @Size(max = 200)
    @NotNull
    @Column(name = "desc_laboratorio", nullable = false, length = 200)
    private String descLaboratorio;

    @Size(max = 20)
    @Column(name = "ide_tipo_laboratorio", length = 20)
    private String ideTipoLaboratorio;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipo_tramite", nullable = false, referencedColumnName = "id_tipo_tramite")
    private CatTipoTramite idTipoTramite;
}
