package com.app.notas.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private  Long id;

    @Column(length = 60)
    @Getter @Setter
    private  String nombre;

    public Rol() {
        super();
    }
}
