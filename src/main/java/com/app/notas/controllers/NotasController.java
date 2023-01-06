package com.app.notas.controllers;

import ch.qos.logback.classic.Logger;
import com.app.notas.models.Nota;
import com.app.notas.models.Usuario;
import com.app.notas.repository.NotasRepository;
import com.app.notas.repository.UsuarioRepository;
import com.app.notas.service.NotaRepositoryImp;
import com.app.notas.service.UsuarioRepositoryImp;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notas")
@CrossOrigin("*")
public class NotasController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    NotaRepositoryImp notaRepositoryImp;


    @GetMapping("/me")
    public ResponseEntity<List<Nota>> getNotasByUserId(Principal principal){
        Optional<Usuario> usuario=  usuarioRepository.findByUsernameOrEmail(principal.getName(),principal.getName());
        List<Nota> notas = notaRepositoryImp.findByUsuario(usuario.get().getIdUsuario());
        return new ResponseEntity<>(notas,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteNotaById(@PathVariable Long id,Principal principal){
        Optional<Usuario> usuario=  usuarioRepository.findByUsernameOrEmail(principal.getName(),principal.getName());
        if (usuario == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (id == null)
        {
            return new  ResponseEntity<>("Nota no encontrada",HttpStatus.NOT_FOUND);


        }
        if (usuario == null){

                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        notaRepositoryImp.deleteNotaById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Nota> updateNota(@RequestBody Nota nota,Principal principal){
        Optional<Usuario> usuario=  usuarioRepository.findByUsernameOrEmail(principal.getName(),principal.getName());
        if (usuario == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        nota.setIdUsuario(usuario.get().getIdUsuario());
        return new ResponseEntity<>(notaRepositoryImp.editarNota(nota),HttpStatus.ACCEPTED);
    }

    @PostMapping("/create")
    public  ResponseEntity<Nota> agregarNota(@RequestBody Nota nota , Principal principal){
        Optional<Usuario> usuario=  usuarioRepository.findByUsernameOrEmail(principal.getName(),principal.getName());
        if (usuario == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        nota.setIdUsuario(usuario.get().getIdUsuario());
        return  new ResponseEntity<>(notaRepositoryImp.agregarNota(nota),HttpStatus.CREATED);
    }

}
