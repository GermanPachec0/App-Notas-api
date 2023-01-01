package com.app.notas.Security;

import lombok.Getter;
import lombok.Setter;

public class JWTAuthResponseDTO {
    @Getter @Setter
    private String tokenDeAcceso;
    @Getter @Setter
    private String tipoDeToken = "Bearer";

    public JWTAuthResponseDTO(String tokenDeAcceso, String tipoDeToken) {
        this.tokenDeAcceso = tokenDeAcceso;
        this.tipoDeToken = tipoDeToken;
    }

    public JWTAuthResponseDTO(String tokenDeAcceso) {
        this.tokenDeAcceso = tokenDeAcceso;
    }
}
