package com.alcanciavirtual.alcanciavirtual_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alcanciavirtual.alcanciavirtual_back.model.RegistroRequest;
import com.alcanciavirtual.alcanciavirtual_back.model.ResponseData;
import com.alcanciavirtual.alcanciavirtual_back.model.Usuario;
import com.alcanciavirtual.alcanciavirtual_back.repository.UsuarioRepositorio;
import com.alcanciavirtual.alcanciavirtual_back.security.UserDetailServiceImpl;

import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/${alcancia_dev.prefix}/auth")
public class AuthControler {

    @Value("${alcancia_dev.prefix}")
    private String prefix;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<?> signUp(@RequestBody RegistroRequest registroRequest) {

        try {
            Usuario nuevoUsuario = userDetailServiceImpl.signUp(registroRequest.getNombre(),
                    registroRequest.getEmail(), registroRequest.getPassword());
            ResponseData responseData = new ResponseData("OK", "Usuario registrado exitosamente.",
                    nuevoUsuario.getEmail(),
                    nuevoUsuario.getNombre());
            return ResponseEntity.ok(responseData);
        } catch (RuntimeException e) {
            ResponseData responseData = new ResponseData("NOK", e.getMessage(), null, null);
            return ResponseEntity.ok(responseData);
        }
    }

    @GetMapping("/fetch")
    public ResponseEntity<?> fetch() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Usuario usuarioRefresh = usuarioRepositorio.findOneByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + username + " no existe."));

       ResponseData responseData = new ResponseData("OK", "Usuario relogueado exitosamente.",
                    usuarioRefresh.getEmail(),
                    usuarioRefresh.getNombre());
            return ResponseEntity.ok(responseData);
    }
}
