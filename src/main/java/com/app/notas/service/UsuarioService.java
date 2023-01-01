package com.app.notas.service;

import com.app.notas.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> getAll();

    Usuario findUserByEmail(String email);
}
