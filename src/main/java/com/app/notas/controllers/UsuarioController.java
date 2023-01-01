package com.app.notas.controllers;

import com.app.notas.models.Usuario;
import com.app.notas.repository.UsuarioRepository;
import com.app.notas.service.UsuarioRepositoryImp;
import com.app.notas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @GetMapping("")
    public ResponseEntity<List<Usuario>> getAll(){
        return new ResponseEntity<>(usuarioService.getAll(), HttpStatus.OK);
    }

}
