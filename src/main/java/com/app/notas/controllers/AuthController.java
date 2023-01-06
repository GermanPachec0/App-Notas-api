package com.app.notas.controllers;

import com.app.notas.Security.JWTAuthResponseDTO;
import com.app.notas.Security.JwtTokenProvider;
import com.app.notas.dto.LoginDTO;
import com.app.notas.dto.RegistroDTO;
import com.app.notas.models.Rol;
import com.app.notas.models.Usuario;
import com.app.notas.repository.RolRepository;
import com.app.notas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")

public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UsuarioRepository customUserDetailsService;


    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //obtenemos el token del jwtProvider
        String token = jwtTokenProvider.generarToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponseDTO(token));
    }

    @PostMapping("/registrar")
    public  ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroDTO){
        if (usuarioRepository.existsByUsername(registroDTO.getUsername())){
            return  new ResponseEntity<>("Nombre de usuario ya existe",HttpStatus.BAD_REQUEST);
        }
        if (usuarioRepository.existsByEmail(registroDTO.getEmail())){
            return  new ResponseEntity<>("Email de usuario ya existe",HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(registroDTO.getNombre());
        usuario.setUsername(registroDTO.getUsername());
        usuario.setEmail(registroDTO.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));

        Rol roles = rolRepository.findByNombre("ROLE_USER").get();
        usuario.setRoles(Collections.singleton(roles));
        usuarioRepository.save(usuario);
        return new ResponseEntity<>("Usuario Registrado exitosamente",HttpStatus.OK);

    }

    @GetMapping("/actual-usuario")
    public Optional<Usuario> obtenerUsuarioActual(Principal principal){
        return customUserDetailsService.findByUsernameOrEmail(principal.getName(),principal.getName());
    }
}
