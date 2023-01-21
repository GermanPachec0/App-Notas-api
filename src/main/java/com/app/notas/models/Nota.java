package com.app.notas.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "nota")
@Data
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnota")
    @Getter @Setter
    private  Long idNota;

    @NotNull
    @NotBlank
    @Getter @Setter
    private  String titulo;

    @NotNull
    @NotBlank
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

    @Getter @Setter
    @Column(name = "id_usuario")
    private  Long idUsuario;


    @Override
    public String toString() {
        String notaTerminada;
        if (terminada){
            notaTerminada = "Tarea  Terminada";
        }else {
            notaTerminada = "Tarea No Terminada";
        }

        return "Nota : " +
                "Titulo" + titulo + '\'' +
                ", Descripcion '" + cuerpo + '\'' +
                ", fechaActualizacion=" + fechaActualizacion +
                ", fechaFinalizacion=" + fechaFinalizacion +
                ", terminada=" + notaTerminada  +
                '}';
    }
}
