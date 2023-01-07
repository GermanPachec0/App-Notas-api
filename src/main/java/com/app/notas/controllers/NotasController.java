package com.app.notas.controllers;

import com.app.notas.models.Nota;
import com.app.notas.models.Usuario;
import com.app.notas.repository.UsuarioRepository;
import com.app.notas.service.NotaRepositoryImp;

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
    public ResponseEntity<List<Nota>> getNotasByUserId(@RequestParam(value = "pageNo",defaultValue = "0",required = false) int numeroDePagina,
                                                       @RequestParam(value = "pageSize",defaultValue = "6") int medidaDePagina,
                                                       Principal principal){
        Optional<Usuario> usuario=  usuarioRepository.findByUsernameOrEmail(principal.getName(),principal.getName());
        List<Nota> notas = notaRepositoryImp.findByUsuario(usuario.get().getIdUsuario(),numeroDePagina,medidaDePagina);
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

    @GetMapping("/{id}")
    public  ResponseEntity<Optional<Nota>> getNotaById(@PathVariable Long id,Principal principal){
        Optional<Usuario> usuario=  usuarioRepository.findByUsernameOrEmail(principal.getName(),principal.getName());
        Nota nota = new Nota();
        nota.setIdNota(id);
        if (usuario == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if(notaRepositoryImp.getNotaByIdAndIdUsuario(nota,usuario).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(notaRepositoryImp.getNotaByIdAndIdUsuario(nota,usuario),HttpStatus.OK);
    }

}
