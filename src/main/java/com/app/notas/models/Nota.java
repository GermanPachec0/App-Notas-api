package com.app.notas.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "nota")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnota")
    @Getter @Setter
    private  Long idNota;

    @NotNull
    @Getter @Setter
    private  String titulo;

    @NotNull
    @Getter @Setter
    private  String cuerpo;


    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.DATE)
    @Getter @Setter
    private Date fechaActualizacion;

    @Column(name = "fecha_finalizacion")
    @Temporal(TemporalType.DATE)
    @Getter @Setter
    private  Date fechaFinalizacion;

    @NotNull
    @Getter @Setter
    private  Boolean terminada;

    @Getter @Setter
    @Column(name = "id_usuario")
    private  Long idUsuario;


}
