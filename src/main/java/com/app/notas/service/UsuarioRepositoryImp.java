package com.app.notas.service;

import com.app.notas.models.Usuario;
import com.app.notas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioRepositoryImp implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;


    @Override
    public List<Usuario> getAll() {
        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
        return usuarios;

    }

    @Override
    public Usuario findUserByEmail(String email) {
        return usuarioRepository.findUserByEmail(email);
    }


}
