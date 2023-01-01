package com.app.notas.dto;

import lombok.Getter;
import lombok.Setter;

public class RegistroDTO {
    @Getter @Setter
    private  String nombre;

    @Getter @Setter
    private  String username;

    @Getter @Setter
    private  String email;

    @Getter @Setter
    private  String password;
}
