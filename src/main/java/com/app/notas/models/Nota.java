package com.app.notas.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "nota")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnota")
    @Getter @Setter
    private  Long idNota;

    @Getter @Setter
    private  String titulo;

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

    @Getter @Setter
    private  Boolean terminada;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario",insertable = false,updatable = false)
    private  Usuario usuario;


}
