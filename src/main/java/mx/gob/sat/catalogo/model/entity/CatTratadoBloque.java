package mx.gob.sat.catalogo.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

/**
 * Entidad para el catalogo de tratado bloque.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Getter
@Setter
@Entity
@Table(name = "cat_tratado_bloque")
public class CatTratadoBloque {

    @EmbeddedId
    private CatTratadoBloqueId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tratado_acuerdo", referencedColumnName = "id", insertable = false, updatable = false)
    private CatTratadoAcuerdo idTratadoAcuerdo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bloque", referencedColumnName = "id", insertable = false, updatable = false)
    private CatTratadoAcuerdo idBloque;

    @NotNull
    @Column(name = "fec_ini_vigencia", nullable = false)
    private LocalDate fecIniVigencia;

    @Column(name = "fec_fin_vigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @Column(name = "bln_activo", nullable = false)
    private Boolean blnActivo;
}
