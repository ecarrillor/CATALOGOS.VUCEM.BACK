package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
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
@Table(name = "cat_vigencia_servicio")
public class CatVigenciaServicio {

    @Id
    @Column(name = "id_vigencia_servicio", nullable = false)
    private Short idVigenciaServicio;

    @Size(max = 20)
    @Column(name = "num_vigencia", length = 20)
    private String numVigencia;

    @Size(max = 20)
    @Column(name = "ide_tipo_vigencia", length = 20)
    private String ideTipoVigencia;

    @Size(max = 20)
    @Column(name = "ide_tipo_servicio_ceror", length = 20)
    private String ideTipoServicioCeror;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bloque", referencedColumnName = "id")
    private CatTratadoAcuerdo idBloque;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cve_criterio_origen", referencedColumnName = "cve_criterio_origen")
    private CatCriterioOrigen cveCriterioOrigen;

    @Size(max = 3)
    @Column(name = "cve_pais", length = 3)
    private String cvePais;

    @Column(name = "id_tratado_acuerdo")
    private Short idTratadoAcuerdo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "cve_pais", referencedColumnName = "cve_pais", insertable = false, updatable = false),
        @JoinColumn(name = "id_tratado_acuerdo", referencedColumnName = "id_tratado_acuerdo", insertable = false, updatable = false)
    })
    private CatPaisTratadoAcuerdo catPaisTratadoAcuerdo;
}
