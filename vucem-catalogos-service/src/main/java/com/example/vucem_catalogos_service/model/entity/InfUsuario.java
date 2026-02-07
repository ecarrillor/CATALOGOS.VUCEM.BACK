package com.example.vucem_catalogos_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "inf_usuario")
public class InfUsuario {
    @Id
    @Size(max = 32)
    @Column(name = "cve_usuario", nullable = false, length = 32)
    private String cveUsuario;

    @Size(max = 64)
    @NotNull
    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "bln_activo")
    private Boolean blnActivo;

    @Column(name = "bln_conectado")
    private Short blnConectado;

    @Column(name = "ultimo_acceso")
    private Long ultimoAcceso;

    @Column(name = "alive_signal")
    private Long aliveSignal;

    @Size(max = 32)
    @Column(name = "token_confirmacion", length = 32)
    private String tokenConfirmacion;


}