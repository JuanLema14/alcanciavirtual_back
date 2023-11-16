package com.alcanciavirtual.alcanciavirtual_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alcanciavirtual.alcanciavirtual_back.model.Usuario;
import com.alcanciavirtual.alcanciavirtual_back.repository.UsuarioRepositorio;

@RestController
@RequestMapping("/${alcancia_dev.prefix}/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @PutMapping("/actualizarvalorahorrar")
    public ResponseEntity<Usuario> actualizarValorAAhorrar(@RequestParam Integer nuevoValorAAhorrar) {
        System.out.println("---------> Actualizar valor a ahorrar controller");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() != null) {
            String username = authentication.getName();
            Usuario usuarioAutenticado = usuarioRepositorio.findOneByEmail(username).orElse(null);

            if (usuarioAutenticado != null) {
                if (nuevoValorAAhorrar < 0) {
                    return ResponseEntity.badRequest().build();
                }

                usuarioAutenticado.setValoraAhorrar(nuevoValorAAhorrar);
                Usuario usuarioActualizado = usuarioRepositorio.save(usuarioAutenticado);
                return ResponseEntity.ok(usuarioActualizado);
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/valorahorrar")
    public ResponseEntity<Integer> getValorAAhorrar() {
        System.out.println("---------> Consultar valor a ahorrar Controller");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() != null) {
            String username = authentication.getName();
            Usuario usuarioAutenticado = usuarioRepositorio.findOneByEmail(username).orElse(null);

            if (usuarioAutenticado != null) {
                Integer valorAAhorrar = usuarioAutenticado.getValoraAhorrar();

                int valorFinal = (valorAAhorrar != null) ? valorAAhorrar : 0;

                return ResponseEntity.ok(valorFinal);
            }
        }

        return ResponseEntity.badRequest().build();
    }

}
