package com.app.notas.dto;

import lombok.Getter;
import lombok.Setter;

public class LoginDTO {
    @Getter @Setter
    private  String usernameOrEmail;

    @Getter @Setter
    private  String password;


}
